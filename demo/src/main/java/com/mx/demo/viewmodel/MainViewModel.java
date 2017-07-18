package com.mx.demo.viewmodel;

import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;

import com.gomeos.activitystarter.ActivityResultCallback;
import com.gomeos.mvvm.viewmodel.LifecycleViewModel;
import com.gomeos.mvvm.viewmodel.command.OnClickCommand;
import com.gomeos.mvvm.viewmodel.proxy.DialogProxy;
import com.mx.demo.view.ui.PulltoRefreshRecyclerViewActivity;
import com.mx.demo.view.ui.RecyclerViewActivity;
import com.mx.demo.view.ui.SecondActivity;
import com.mx.demo.viewmodel.viewbean.ItemViewBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhulianggang on 2017/7/10.
 */

public class MainViewModel extends LifecycleViewModel {

    private List<ItemViewBean> items = new LinkedList<>();
    //viemodel里通过代理使用dialog
    DialogProxy dialogProxy;

    @Bindable
    public List<ItemViewBean> getItems() {
        return items;
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }


    @Override
    protected void onAttachedToView() {
        super.onAttachedToView();
        Log.d("MainViewModel", "onAttachedToView>>");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainViewModel", "onStart>>");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainViewModel", "onRestart>>");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainViewModel", "onResume>>");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainViewModel", "onPause>>");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainViewModel", "onStop>>");
    }

    @Override
    protected void onDetachedFromView() {
        super.onDetachedFromView();
        Log.d("MainViewModel", "onDetachedFromView>>");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("MainViewModel", "onWindowFocusChanged>>");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("viewModel", "test");
        super.onSaveInstanceState(outState);
        Log.d("MainViewModel", "onSaveInstanceState>>" + outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("MainViewModel", "onRestoreInstanceState>>" + savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean userVisibleHint) {
        super.setUserVisibleHint(userVisibleHint);
        Log.d("MainViewModel", "setUserVisibleHint>>");
    }



    public OnClickCommand getOnClickSecondActivity() {
        return new OnClickCommand() {
            @Override
            public void execute(int viewId) {
                startActivityForResult(new Intent(getContext(), SecondActivity.class), new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        Log.d("", "=resultCode = " + resultCode);
                    }
                });
            }
        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public OnClickCommand getOnClickRecyclerView() {
        return new OnClickCommand() {
            @Override
            public void execute(int viewId) {
                Intent intent = new Intent(getContext(), RecyclerViewActivity.class);
                startActivity(intent);
            }
        };
    }

    public OnClickCommand getOnShowDialog() {
        return new OnClickCommand() {
            @Override
            public void execute(int viewId) {
                if (dialogProxy != null) {
                    dialogProxy.show();
                }
            }
        };
    }


    public OnClickCommand getOnGotoRecyclerview() {
        return new OnClickCommand() {
            @Override
            public void execute(int viewId) {
                Intent intent = new Intent(getContext(), PulltoRefreshRecyclerViewActivity.class);
                startActivity(intent);
            }
        };
    }

    public void setDialogProxy(DialogProxy dialogProxy) {
        this.dialogProxy = dialogProxy;
    }
}
