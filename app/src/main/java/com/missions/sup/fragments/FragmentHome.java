package com.missions.sup.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.missions.sup.R;
import com.missions.sup.activities.RestaurantActivity;
import com.missions.sup.adapters.AdapterFragmentHome;
import com.missions.sup.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2/14/2016.
 */
public class FragmentHome extends CustomFragment {

    private final int DURATION = 300;
    private final int OPTION_HOLDER_SIZE = 245;

    private RecyclerView recyclerView;

    @Bind(R.id.ll_option_holder)
    View llOptionHolder;

    @Bind(R.id.iv_optionTrigger)
    ImageView ivOptionTrigger;

    private float recyclerViewOrignalX, llOptionHolderOriginalX;

    private boolean isOptionsShown;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> dummy = new ArrayList<>();
        dummy.add("");
        dummy.add("");
        dummy.add("");
        dummy.add("");
        recyclerView.setAdapter(new AdapterFragmentHome(dummy, new AdapterFragmentHome.OnLogoPressed() {
            @Override
            public void onLogoPressed(int position) {
                showRestaurantPage();
            }
        }));

        llOptionHolder.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                llOptionHolderOriginalX = llOptionHolder.getX();
                llOptionHolder.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerViewOrignalX = recyclerView.getX();
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        setupOptionsView();

        return view;
    }

    private void showRestaurantPage() {
        Intent intent = new Intent(getActivity(), RestaurantActivity.class);
        getActivity().startActivity(intent);
    }

    private void setupOptionsView() {
        getChildFragmentManager().beginTransaction().add(R.id.ll_option_layout_holder, new FragmentOptionTag()).commit();
    }

    @OnClick(R.id.iv_optionTrigger)
    public final void onOptionTriggerClicked() {
        if (isOptionsShown) {
            hideOptions();
        } else {
            showOptions();
        }
    }

    private void showOptions() {
        isOptionsShown = true;
        ObjectAnimator slideLeftRecyclerView = ObjectAnimator.ofFloat(recyclerView, "X", recyclerView.getX(), recyclerView.getX() + Utils.dpToPx(OPTION_HOLDER_SIZE, getActivity()));
        slideLeftRecyclerView.setDuration(DURATION);

        ObjectAnimator slideLeftOptionHolder = ObjectAnimator.ofFloat(llOptionHolder, "X", llOptionHolder.getX(), llOptionHolder.getX() + Utils.dpToPx(OPTION_HOLDER_SIZE, getActivity()));
        slideLeftOptionHolder.setDuration(DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(slideLeftOptionHolder, slideLeftRecyclerView);
        animatorSet.start();
    }

    private void hideOptions() {
        isOptionsShown = false;
        ObjectAnimator slideLeftRecyclerView = ObjectAnimator.ofFloat(recyclerView, "X", recyclerView.getX(), recyclerViewOrignalX);
        slideLeftRecyclerView.setDuration(DURATION);

        ObjectAnimator slideLeftOptionHolder = ObjectAnimator.ofFloat(llOptionHolder, "X", llOptionHolder.getX(), llOptionHolderOriginalX);
        slideLeftOptionHolder.setDuration(DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(slideLeftOptionHolder, slideLeftRecyclerView);
        animatorSet.start();
    }


    @Override
    public boolean onBackPressed() {
        if (isOptionsShown) {
            hideOptions();
            return true;
        }
        return false;
    }
}
