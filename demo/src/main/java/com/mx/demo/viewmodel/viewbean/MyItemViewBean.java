package com.mx.demo.viewmodel.viewbean;

/**
 * Created by zhulianggang on 2017/7/11.
 */

public class MyItemViewBean extends ItemViewBean {
    private String itemName;

    public MyItemViewBean(String itemName) {
        this.itemName = itemName;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
