package com.hadi.bc;

import android.content.Context;

import com.hadi.bc.model.WelcomeItem;

import java.util.ArrayList;
import java.util.List;

public class WelcomeBannerData {
    private List<WelcomeItem> welcomeItemList = new ArrayList<>();


    private WelcomeBannerData() {
    }

    public WelcomeBannerData(Context context) {
        addWelcomeItem(new WelcomeItem(context.getString(R.string.first_banner_body), context.getString(R.string.first_banner_header)));
        addWelcomeItem(new WelcomeItem(context.getString(R.string.second_banner_body), context.getString(R.string.second_banner_header)));
        addWelcomeItem(new WelcomeItem(context.getString(R.string.third_banner_body), context.getString(R.string.third_banner_header)));
    }

    private void addWelcomeItem(WelcomeItem welcomeItem) {
        welcomeItemList.add(welcomeItem);
    }

    public List<WelcomeItem> getWelcomeItemList() {
        return welcomeItemList;
    }
}

