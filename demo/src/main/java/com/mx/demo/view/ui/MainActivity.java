package com.mx.demo.view.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mx.demo.R;
import com.gomeos.mvvm.view.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragment_content, mainFragment);
        fragmentTransaction.commit();
    }
}
