package com.missions.sup.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.bumptech.glide.Glide;
import com.missions.sup.R;
import com.missions.sup.adapters.AdapterViewpagers;
import com.missions.sup.fragments.FragmentAds;
import com.missions.sup.utils.CircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2/21/2016.
 */
public class RestaurantActivity extends AppCompatActivity {

    @Bind(R.id.iv_logo_est)
    ImageView ivLogoEst;

    private TabLayout tabLayout;
    private AdapterViewpagers adapterViewPager;
    private ViewPager viewPagerAds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);
        ButterKnife.bind(this);

        adapterViewPager = new AdapterViewpagers(getSupportFragmentManager());
        initViews();
        setUpTab();
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tl_resto_tab);
        viewPagerAds = (ViewPager) findViewById(R.id.vp_ads);
        Glide.with(this).load(R.drawable.logo_mcdo).transform(new CircleTransform(this)).into(ivLogoEst);
    }

    private void setUpTab() {
        adapterViewPager.addFrag("Advertisements", new FragmentAds());
        adapterViewPager.addFrag("Reviews", new Fragment());
        viewPagerAds.setAdapter(adapterViewPager);
        tabLayout.setupWithViewPager(viewPagerAds);
    }
}
