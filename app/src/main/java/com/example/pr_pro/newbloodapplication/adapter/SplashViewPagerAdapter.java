package com.example.pr_pro.newbloodapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SplashViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> fragmentListTitle=new ArrayList<>();


    public SplashViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentListTitle.get (position);
    }
    public void  addFragment(Fragment fragment,String Title){

        fragmentList.add(fragment);
        fragmentListTitle.add(Title);
    };
}


