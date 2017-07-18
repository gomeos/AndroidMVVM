package com.gomeos.mvvm.viewmodel;

/**
 * Created by liuyuxuan on 16/8/19.
 */
public interface Visitor<T> {
    void visit(T data);
}
