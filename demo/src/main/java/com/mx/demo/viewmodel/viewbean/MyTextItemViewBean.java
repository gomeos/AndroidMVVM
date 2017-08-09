package com.mx.demo.viewmodel.viewbean;

/**
 * Created by chenbaocheng on 16/8/14.
 */
public class MyTextItemViewBean extends ItemViewBean {
    private String text = null;
    private boolean isUpperCase = false;

    public String getText() {
        return text;
    }

    public MyTextItemViewBean(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUpperCase() {
        return isUpperCase;
    }

    public void setUpperCase(boolean upperCase) {
        isUpperCase = upperCase;
    }
}
