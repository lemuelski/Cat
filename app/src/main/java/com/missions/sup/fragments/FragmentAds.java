package com.missions.sup.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missions.sup.R;
import com.missions.sup.adapters.AdapterFragmentHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/9/2016.
 */
public class FragmentAds extends Fragment {

    private RecyclerView rvAds;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads, container, false);

        rvAds = (RecyclerView)view.findViewById(R.id.rv_ads);
        rvAds.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> dummy = new ArrayList<>();
        dummy.add("");
        dummy.add("");
        dummy.add("");
        dummy.add("");
        rvAds.setAdapter(new AdapterFragmentHome(dummy, new AdapterFragmentHome.OnLogoPressed() {
            @Override
            public void onLogoPressed(int position) {
            }
        }, false));

        return view;
    }
}
