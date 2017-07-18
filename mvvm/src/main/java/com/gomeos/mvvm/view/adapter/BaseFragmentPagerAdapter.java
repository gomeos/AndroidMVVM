package com.gomeos.mvvm.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.gomeos.mvvm.utils.CheckUtils;
import com.gomeos.mvvm.view.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyuxuan on 16/6/3.
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragmentList;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addBaseFragment(@NonNull BaseFragment baseFragment) {
        CheckUtils.checkNotNull(baseFragment);
        fragmentList.add(baseFragment);
    }

    public void addBaseFragments(@NonNull List<BaseFragment> baseFragments) {
        CheckUtils.checkNotNull(baseFragments);
        fragmentList.addAll(baseFragments);

    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
