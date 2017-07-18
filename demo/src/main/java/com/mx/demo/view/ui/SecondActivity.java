package com.mx.demo.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mx.demo.R;
import com.gomeos.mvvm.view.ui.BaseActivity;
import com.orhanobut.logger.Logger;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.result_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.result_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "received");
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("","=requestCode="+requestCode+", resultCode="+resultCode);

    }

    public  void finish(){
        super.finish();
        Logger.t("result").d(getClass().getName() + "finish");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.t("result").d(getClass().getName()+"destory="+isFinishing());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
