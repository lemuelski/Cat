package com.missions.sup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.missions.sup.R;
import com.missions.sup.utils.CircleTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2/14/2016.
 */
public class AdapterFragmentHome extends RecyclerView.Adapter<AdapterFragmentHome.ViewHolder> {

    private final List<String> list;
    private final OnLogoPressed onLogoPressed;
    private Context context;
    private boolean shouldShowLogo;

    public interface OnLogoPressed {
        void onLogoPressed(int position);
    }


    public AdapterFragmentHome(List<String> list, OnLogoPressed onLogoPressed) {
        this(list, onLogoPressed, true);
    }


    public AdapterFragmentHome(List<String> list, OnLogoPressed onLogoPressed, boolean shouldShowLogo) {
        this.list = list;
        this.onLogoPressed = onLogoPressed;
        this.shouldShowLogo = shouldShowLogo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_ad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (shouldShowLogo){
        Glide.with(context).load(R.drawable.logo_mcdo).transform(new CircleTransform(context)).into(holder.ivLogoEst);
            holder.ivLogoEst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLogoPressed != null) {
                        onLogoPressed.onLogoPressed(position);
                    }
                }
            });
        }else {
            holder.ivLogoEst.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_logo_est)
        ImageView ivLogoEst;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
