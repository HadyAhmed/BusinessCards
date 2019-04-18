package com.hadi.bc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hadi.bc.R;
import com.hadi.bc.model.WelcomeItem;

import java.util.List;

public class MainViewPagerAdapter extends PagerAdapter {

    private List<WelcomeItem> welcomeItems;

    public MainViewPagerAdapter(List<WelcomeItem> welcomeItems) {
        this.welcomeItems = welcomeItems;
    }

    @Override
    public int getCount() {
        if (welcomeItems == null) {
            return 0;
        } else {
            return welcomeItems.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_item, container, false);

        WelcomeItem welcomeItem = welcomeItems.get(position);

        TextView welcomeHeaderTv = rootView.findViewById(R.id.welcomeHeader);
        TextView welcomeBodyTv = rootView.findViewById(R.id.welcomeBody);

        welcomeHeaderTv.setText(welcomeItem.getWelcomeHeader());
        welcomeBodyTv.setText(welcomeItem.getWelcomeBody());

        container.addView(rootView);

        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}

