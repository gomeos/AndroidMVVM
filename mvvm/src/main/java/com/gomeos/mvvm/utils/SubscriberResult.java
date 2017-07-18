package com.gomeos.mvvm.utils;

import rx.Subscriber;

/**
 * Created by liuyuxuan on 16/6/28.
 */
public abstract class SubscriberResult<Success> extends Subscriber<Success> {

    public SubscriberResult() {
        super();
        captor(this);
    }

    public SubscriberResult(Subscriber<?> subscriber) {
        super(subscriber);
        captor(this);
    }

    protected SubscriberResult(Subscriber<?> subscriber, boolean shareSubscriptions) {
        super(subscriber, shareSubscriptions);
        captor(this);
    }

    private void captor(SubscriberResult<Success> value) {

    }


    @Deprecated
    @Override
    public final void onCompleted() {

    }

    @Deprecated
    @Override
    public final void onError(Throwable e) {

    }

    @Deprecated
    @Override
    public final void onNext(Success success) {

    }

    public abstract void onSuccess(Success success);

    public abstract void onError(int errorCode, String msg);

    public abstract void onFailure(Throwable e);

}
