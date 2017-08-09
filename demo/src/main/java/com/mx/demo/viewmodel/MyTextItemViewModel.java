package com.mx.demo.viewmodel;

import com.gomeos.mvvm.viewmodel.RecyclerItemViewModel;
import com.gomeos.mvvm.viewmodel.command.OnClickCommand;
import com.mx.demo.viewmodel.viewbean.ItemViewBean;
import com.mx.demo.viewmodel.viewbean.MyTextItemViewBean;

/**
 * Created by zhulianggang on 2017/8/9.
 */

public class MyTextItemViewModel extends RecyclerItemViewModel<ItemViewBean> {

    private MyTextItemViewBean  myTextItemViewBean ;
    private String itemName;

    @Override
    protected void onItemChange(ItemViewBean oldItem, ItemViewBean item) {
        myTextItemViewBean = (MyTextItemViewBean) item;
        itemName = myTextItemViewBean.getText();
        notifyChange();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public OnClickCommand getClickCommand() {
        return new OnClickCommand() {
            @Override
            public void execute(int viewId) {
//                Toast.makeText(getContext(), "click item name=" + itemName, Toast.LENGTH_LONG).show();
                //通过事件与外边viewmodel进行通信

            }
        };
    }

}
