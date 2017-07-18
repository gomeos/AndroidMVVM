package com.mx.demo.model;

import com.gomeos.mvvm.utils.SubscriberResult;
import com.gomeos.mvvm.event.EventProxy;
import com.gomeos.mvvm.model.UseCase;
import com.mx.demo.viewmodel.viewbean.MyItemViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhulianggang on 2017/7/11.
 *
 */

public class MyUseCase extends UseCase {

    @Override
    protected void onOpen() {
        //事件通讯初始化
        EventProxy.getDefault().register(this);

    }

    @Override
    protected void onClose() {
        //释放和清理
        EventProxy.getDefault().unregister(this);
    }




    public void getData(final SubscriberResult<List<MyItemViewBean>> subscriber){
        List<MyItemViewBean>  data = new ArrayList<>();
        data.add(new MyItemViewBean("A"));
        data.add(new MyItemViewBean("B"));
        data.add(new MyItemViewBean("C"));
        data.add(new MyItemViewBean("D"));
        data.add(new MyItemViewBean("E"));
        data.add(new MyItemViewBean("F"));
        data.add(new MyItemViewBean("G"));
        data.add(new MyItemViewBean("H"));
        data.add(new MyItemViewBean("I"));
        subscriber.onSuccess(data);

    }
}
