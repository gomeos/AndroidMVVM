package com.mx.demo.event;

import com.gomeos.mvvm.event.BroadcastEvent;

/**
 * Created by zhulianggang on 2017/7/17.
 */

public class MessageEvent extends BroadcastEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
