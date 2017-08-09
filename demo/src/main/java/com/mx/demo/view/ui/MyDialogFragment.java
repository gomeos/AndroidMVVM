package com.mx.demo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mx.demo.R;
import com.gomeos.mvvm.view.ui.BaseDialogFragment;

/**
 * Created by zhulianggang on 16/9/21.
 * 介绍使用继承BaseDialogFragment的dialogfragment与viewmodel的代理使用
 */

public class MyDialogFragment extends BaseDialogFragment {
    private Button btnOk;
    private Button btnCancel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, 0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.gm_custom_dialog, container);
        btnCancel = (Button) view.findViewById(R.id.negativeButton);
        btnOk = (Button) view.findViewById(R.id.positiveButton);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onOkClick();
                }
                dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onCancelClick();
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public interface OnBtnClickListener {
        public void onOkClick();

        public void onCancelClick();
    }

    private OnBtnClickListener mOnBtnClickListener;

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        mOnBtnClickListener = onBtnClickListener;
    }


}
