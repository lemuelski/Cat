package com.missions.sup.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.missions.sup.R;
import com.missions.sup.fragments.CustomFragment;
import com.missions.sup.fragments.FragmentHome;

public class MainActivity extends AppCompatActivity {

    private final int HOME = 0;
    private final int FAVE = 1;
    private final int SEARCH = 2;
    private final int USER = 3;

    private TabLayout tabLayout;
    private int currentFragmentIndex = HOME;

    private CustomFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.activity_main_tab_layout);
        initTabs();
        setShownFragment();
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(getMainTabView(R.drawable.icon_home_64, "Home")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getMainTabView(R.drawable.icon_fave_64, "Favorite")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getMainTabView(R.drawable.icon_search_64, "Search")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getMainTabView(R.drawable.icon_user_64, "User")));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setShownFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (currentFragmentIndex){
            case HOME:
                currentFragment = new FragmentHome();
                break;
        }
        ft.add(R.id.fl_main_fragment_holder, currentFragment).commit();
    }

    @Override
    public void onBackPressed() {
        //Fragment handled onbackpressed
        if(currentFragment.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }

    private View getMainTabView(int icon_id, String tabTitle) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.item_tab_layout, null);
        Glide.with(this).load(icon_id).into((ImageView) tabView.findViewById(R.id.iv_icon_tab));
        ((TextView) tabView.findViewById(R.id.tab_title)).setText(tabTitle);
        return tabView;
    }
}
