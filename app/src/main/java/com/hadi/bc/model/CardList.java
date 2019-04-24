package com.hadi.bc.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CardList {
    private String cardTitle;

    public CardList() {
    }

    public CardList(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }
}
