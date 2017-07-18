package com.mx.demo.viewmodel;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.gomeos.mvvm.utils.SubscriberResult;
import com.gomeos.mvvm.viewmodel.LifecycleViewModel;
import com.mx.demo.event.MessageEvent;
import com.mx.demo.model.MyUseCase;
import com.mx.demo.viewmodel.viewbean.MyItemViewBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhulianggang on 2017/7/11.
 */

public class MyViewModel extends LifecycleViewModel {
    private MyUseCase myUseCase;
    private List<MyItemViewBean> items = new LinkedList<>();


    public boolean isRefreshing() {
        return refreshing;
    }

    private boolean refreshing = false;

    public List<MyItemViewBean> getItems() {
        return items;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        myUseCase = obtainUseCase(MyUseCase.class);
        getData();
    }

    private void getData() {
        myUseCase.getData(new SubscriberResult<List<MyItemViewBean>>() {
            @Override
            public void onSuccess(List<MyItemViewBean> myItemViewBeen) {
                items.addAll(myItemViewBeen);
                notifyChange();
            }

            @Override
            public void onError(int errorCode, String msg) {

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    //SwipeRefreshLayout 控件里的绑定，对应SwipeRefreshLayout里的setOnRefreshListener方法
    //refreshing 则对应SwipeRefreshLayout控件里的setRefreshing的方法，对应setXXXX.
    public SwipeRefreshLayout.OnRefreshListener getRefreshListener(){
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(),"onRefresh",Toast.LENGTH_LONG).show();
                refreshing = false;
                notifyChange();
            }
        };
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(final MessageEvent messageEvent) {
        Toast.makeText(getContext(),"message from itemviewmodel:"+messageEvent.getMessage(),Toast.LENGTH_LONG).show();
    }

}
