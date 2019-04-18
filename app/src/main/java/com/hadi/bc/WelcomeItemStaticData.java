package com.hadi.bc;

import com.hadi.bc.model.WelcomeItem;

import java.util.ArrayList;
import java.util.List;

public abstract class WelcomeItemStaticData {
    private WelcomeItemStaticData() {

    }

    private static final List<WelcomeItem> WELCOME_ITEMS;

    static {
        WELCOME_ITEMS = new ArrayList<>();
        addWelcomeItem(new WelcomeItem("Welcome to business card designer app", "Business Card Designer"));
        addWelcomeItem(new WelcomeItem("Get inspired many ideas", "Free Ideas"));
        addWelcomeItem(new WelcomeItem("Sync your data with your google account so you won't lost your cards never", "Keep Sync"));
    }

    private static void addWelcomeItem(WelcomeItem welcomeItem) {
        WELCOME_ITEMS.add(welcomeItem);
    }

    public static List<WelcomeItem> getWelcomeItems() {
        return WELCOME_ITEMS;
    }
}

