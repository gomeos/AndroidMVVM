package com.gomeos.mvvm.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gomeos.mvvm.utils.CheckUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyuxuan on 16/4/20.
 */
public class ViewModelManager {
    private static final String BUNDLE_KEY_ACTIVITY_RESULT_RECEIVERS = "framework_activity_result_receivers";
    private final List<LifecycleViewModel> lifecycleViewModelList;
    private Bundle savedInstanceState;
    private LifecycleState lifecycleState = LifecycleState.Init;
    private List<Visitor<LifecycleViewModel>> visitors = new LinkedList<>();
    @SuppressLint("UseSparseArrays") // Request codes are too large to fit in a SparseArray
    private HashMap<Integer, String> activityResultReceivers = new HashMap<>();

    public ViewModelManager() {
        lifecycleViewModelList = new LinkedList<>();
    }

    public void setSavedInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    public void addViewModel(LifecycleViewModel lifecycle) {
        CheckUtils.checkNotNull(lifecycle);

        if (lifecycleViewModelList.contains(lifecycle)) {
            return;
        }
        if (lifecycle.getTag() != null && findViewModelByTag(lifecycle.getTag()) != null) {
            throw new RuntimeException("Duplicated view model tag: " + lifecycle.getTag());
        }
        lifecycleViewModelList.add(lifecycle);

        for (Visitor<LifecycleViewModel> visitor : visitors) {
            lifecycle.accept(visitor);
        }
    }

    public void removeViewModel(LifecycleViewModel lifecycle) {
        CheckUtils.checkNotNull(lifecycle);
        lifecycleViewModelList.remove(lifecycle);
    }

    public void setUserVisibleHint(final boolean isVisibleToUser) {
        Visitor<LifecycleViewModel> onUserVisibleHintVisitor =
                new Visitor<LifecycleViewModel>() {
                    @Override
                    public void visit(LifecycleViewModel data) {
                        data.setUserVisibleHint(isVisibleToUser);
                    }
                };
        visitors.add(onUserVisibleHintVisitor);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onUserVisibleHintVisitor);
        }
    }

    public void onWindowFocusChanged(final boolean hasFocus) {
        Visitor<LifecycleViewModel> onWindowFocusChanged =
                new Visitor<LifecycleViewModel>() {
                    @Override
                    public void visit(LifecycleViewModel data) {
                        data.onWindowFocusChanged(hasFocus);
                    }
                };
        visitors.add(onWindowFocusChanged);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onWindowFocusChanged);
        }
    }

    public void create() {
        if (savedInstanceState != null) {
            activityResultReceivers = (HashMap<Integer, String>) savedInstanceState
                    .getSerializable(BUNDLE_KEY_ACTIVITY_RESULT_RECEIVERS);
        }
 //因为viewpager中使用fragment会循环使用 所以无法检查此时的状态
//        CheckUtils.checkState(lifecycleState == LifecycleState.Init);
        Visitor<LifecycleViewModel> onCreateVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                Bundle bundle = savedInstanceState == null ? null : savedInstanceState.getBundle(vm.getTag());
                vm.create(bundle);
            }
        };
        visitors.add(onCreateVisitor);
        lifecycleState = LifecycleState.Created;
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onCreateVisitor);
        }
    }

    public LifecycleViewModel findViewModelByTag(String tag) {
        CheckUtils.checkNotNull(tag);
        for (LifecycleViewModel viewModel : lifecycleViewModelList) {
            if (tag.equals(viewModel.getTag())) {
                return viewModel;
            }
        }
        return null;
    }

    public void registerActivityResultReceiver(int requestCode, String viewModelId) {
        activityResultReceivers.put(requestCode, viewModelId);
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        Visitor<LifecycleViewModel> visitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                String tag = activityResultReceivers.get(requestCode);
                if (tag != null && tag.equals(vm.getTag())) {
                    activityResultReceivers.remove(requestCode);
                    vm.executeActivityResult(requestCode, resultCode, intent);
                }
            }
        };
        visitors.add(visitor);

        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(visitor);
        }
    }

    public void start() {
        CheckUtils.checkState(lifecycleState == LifecycleState.Stopped
                || lifecycleState == LifecycleState.Created
                || lifecycleState == LifecycleState.Restarted);
        Visitor<LifecycleViewModel> onStartVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                vm.start();
            }
        };
        visitors.add(onStartVisitor);
        lifecycleState = LifecycleState.Started;
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onStartVisitor);
        }
    }

    public void restart() {
        Visitor<LifecycleViewModel> onRestartVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                vm.restart();
            }
        };
        visitors.add(onRestartVisitor);
        lifecycleState = LifecycleState.Restarted;
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onRestartVisitor);
        }
    }

    public void resume() {
        CheckUtils.checkState(lifecycleState == LifecycleState.Started
                || lifecycleState == LifecycleState.Paused);
        lifecycleState = LifecycleState.Resumed;
        Visitor<LifecycleViewModel> onResumeVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                vm.resume();
            }
        };
        visitors.add(onResumeVisitor);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onResumeVisitor);
        }
    }

    public void pause() {
        CheckUtils.checkState(lifecycleState == LifecycleState.Resumed);
        lifecycleState = LifecycleState.Paused;
        Visitor<LifecycleViewModel> onPauseVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                vm.pause();
            }
        };
        visitors.add(onPauseVisitor);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onPauseVisitor);
        }
    }

    public void stop() {
        CheckUtils.checkState(lifecycleState == LifecycleState.Started
                || lifecycleState == LifecycleState.Paused);
        lifecycleState = LifecycleState.Stopped;
        Visitor<LifecycleViewModel> onStopVisitor = new Visitor<LifecycleViewModel>() {
            @Override
            public void visit(LifecycleViewModel vm) {
                vm.stop();
            }
        };
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onStopVisitor);
        }
        visitors.clear();
    }

    public void saveInstanceState(final Bundle savedInstanceState) {
        Visitor<LifecycleViewModel> onSaveInstanceState =
                new Visitor<LifecycleViewModel>() {
                    @Override
                    public void visit(LifecycleViewModel data) {
                        Bundle bundle = new Bundle();
                        data.onSaveInstanceState(bundle);
                        savedInstanceState.putBundle(data.getTag(), bundle);
                    }
                };
        visitors.add(onSaveInstanceState);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onSaveInstanceState);
        }
        savedInstanceState.putSerializable(BUNDLE_KEY_ACTIVITY_RESULT_RECEIVERS, activityResultReceivers);
    }

    public void onAttachContext(Context context) {
        for (LifecycleViewModel lifecycle : lifecycleViewModelList) {
            lifecycle.setContext(context);
        }
    }

    public void restoreInstanceState(final Bundle savedInstanceState) {
        Visitor<LifecycleViewModel> onRestoreInstanceState =
                new Visitor<LifecycleViewModel>() {
                    @Override
                    public void visit(LifecycleViewModel data) {
                        Bundle saveBundle = savedInstanceState == null ? null : savedInstanceState.getBundle(data.getTag());
                        data.onRestoreInstanceState(saveBundle);
                    }
                };
        visitors.add(onRestoreInstanceState);
        for (Lifecycle lifecycle : lifecycleViewModelList) {
            lifecycle.accept(onRestoreInstanceState);
        }
    }

}
