package com.missions.sup.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missions.sup.R;

import butterknife.ButterKnife;

/**
 * Created by User on 2/14/2016.
 */
public class FragmentOptionTag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
