package com.missions.sup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/9/2016.
 */
public class AdapterViewpagers extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> fragmentTitle;

    public AdapterViewpagers(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentTitle = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    public void addFrag(String title, Fragment fragment){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }
}
