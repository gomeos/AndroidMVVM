package com.gomeos.mvvm.event;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by liuyuxuan on 16/5/13.
 */
public class EventProxy {

    private static EventProxy enventProxy;

    static EventBus eventBus;

    public static EventProxy getDefault() {

        if (null == enventProxy) {

            synchronized (EventProxy.class) {

                if (null == enventProxy) {

                    enventProxy = new EventProxy();
                }
            }
        }
        return enventProxy;
    }

    private EventProxy() {
        eventBus = EventBus.getDefault();
    }


    public static void clearCaches() {

        eventBus.clearCaches();

    }

    public void register(Object subscriber) {

        try {
            eventBus.register(subscriber);
        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    public void unregister(Object subscriber) {

        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }

    }

    public void post(Object event) {

        if (eventBus.hasSubscriberForEvent(event.getClass())) {
            eventBus.post(event);
        }
    }

    public void cancelEventDelivery(Object event) {
        eventBus.cancelEventDelivery(event);

    }

    public void postSticky(Object event) {
        eventBus.postSticky(event);
    }

    public <T> T getStickyEvent(Class<T> eventType) {

        return eventBus.getStickyEvent(eventType);

    }

    public <T> T removeStickyEvent(Class<T> eventType) {

        return eventBus.removeStickyEvent(eventType);
    }

    public boolean removeStickyEvent(Object event) {

        return eventBus.removeStickyEvent(event);
    }

    public boolean isRegistered(Object subscriber) {

        return eventBus.isRegistered(subscriber);
    }

    public void removeAllStickyEvents() {
        eventBus.removeAllStickyEvents();
    }

    public boolean hasSubscriberForEvent(Class<?> eventClass) {
        return eventBus.hasSubscriberForEvent(eventClass);
    }

}
