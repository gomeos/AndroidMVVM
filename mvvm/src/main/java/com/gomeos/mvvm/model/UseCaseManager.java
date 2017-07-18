package com.gomeos.mvvm.model;

import android.content.Context;

import com.gomeos.mvvm.utils.ObjectUtils;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.gomeos.mvvm.utils.CheckUtils.checkArgument;

public class UseCaseManager {
    private Set<Class<? extends UseCase>> useCaseTypes;
    private Map<String, UseCase> useCaseRefs;
    private Context context;

    public UseCaseManager(Context context) {
        this.context = context;
        useCaseTypes = new HashSet<>();
        useCaseRefs = new HashMap<>();
    }

    public synchronized void register(Class<? extends UseCase> useCaseClass) {
        if (useCaseTypes.contains(useCaseClass)) {
            return;
        }
        useCaseTypes.add(useCaseClass);
    }

    public synchronized <T extends UseCase> T obtainUseCase(Class<T> useCaseClass, UseCaseHolder useCaseHolder) {
        checkArgument(useCaseTypes.contains(useCaseClass));
        T useCase = (T) useCaseRefs.get(useCaseClass.getName());
        if (null == useCase) {
            useCase = ObjectUtils.newInstance(useCaseClass);
            useCase.setContext(context);
            useCaseRefs.put(useCaseClass.getName(), useCase);
        }
        if (!useCase.isOpen()) {
            useCase.open();
        }
        useCase.addUseCaseHolder(useCaseHolder);
        return useCase;
    }

    public final void onUseCaseHolderDestroy(UseCaseHolder holder) {
        for (UseCase useCase : useCaseRefs.values()) {
            useCase.onHolderDestroy(holder);
        }
    }


}
