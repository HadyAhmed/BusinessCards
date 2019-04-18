package com.hadi.bc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardItem implements Parcelable {
    private String cardName;
    private String cardNameHolder;
    private String cardOccupation;
    private String cardPhoneNumber;
    private String cardEmail;
    private String cardAddress;
    private String cardWebsite;
    private String cardSlogan;
    private String cardImageDesignUrl;

    public CardItem(String cardName, String cardNameHolder, String cardOccupation,
                    String cardPhoneNumber, String cardEmail, String cardAddress, String cardWebsite, String cardSlogan, String cardImageDesignUrl) {
        this.cardName = cardName;
        this.cardNameHolder = cardNameHolder;
        this.cardOccupation = cardOccupation;
        this.cardPhoneNumber = cardPhoneNumber;
        this.cardEmail = cardEmail;
        this.cardAddress = cardAddress;
        this.cardWebsite = cardWebsite;
        this.cardSlogan = cardSlogan;
        this.cardImageDesignUrl = cardImageDesignUrl;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNameHolder(String cardNameHolder) {
        this.cardNameHolder = cardNameHolder;
    }

    public void setCardOccupation(String cardOccupation) {
        this.cardOccupation = cardOccupation;
    }

    public void setCardPhoneNumber(String cardPhoneNumber) {
        this.cardPhoneNumber = cardPhoneNumber;
    }

    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public void setCardWebsite(String cardWebsite) {
        this.cardWebsite = cardWebsite;
    }

    public void setCardSlogan(String cardSlogan) {
        this.cardSlogan = cardSlogan;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNameHolder() {
        return cardNameHolder;
    }

    public String getCardOccupation() {
        return cardOccupation;
    }

    public String getCardPhoneNumber() {
        return cardPhoneNumber;
    }

    public String getCardEmail() {
        return cardEmail;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public String getCardWebsite() {
        return cardWebsite;
    }

    public String getCardSlogan() {
        return cardSlogan;
    }

    public void setCardImageDesignUrl(String cardImageDesignUrl) {
        this.cardImageDesignUrl = cardImageDesignUrl;
    }

    public String getCardImageDesignUrl() {
        return cardImageDesignUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardName);
        dest.writeString(this.cardNameHolder);
        dest.writeString(this.cardOccupation);
        dest.writeString(this.cardPhoneNumber);
        dest.writeString(this.cardEmail);
        dest.writeString(this.cardAddress);
        dest.writeString(this.cardWebsite);
        dest.writeString(this.cardSlogan);
    }

    protected CardItem(Parcel in) {
        this.cardName = in.readString();
        this.cardNameHolder = in.readString();
        this.cardOccupation = in.readString();
        this.cardPhoneNumber = in.readString();
        this.cardEmail = in.readString();
        this.cardAddress = in.readString();
        this.cardWebsite = in.readString();
        this.cardSlogan = in.readString();
    }

    public static final Parcelable.Creator<CardItem> CREATOR = new Parcelable.Creator<CardItem>() {
        @Override
        public CardItem createFromParcel(Parcel source) {
            return new CardItem(source);
        }

        @Override
        public CardItem[] newArray(int size) {
            return new CardItem[size];
        }
    };

    @Override
    public String toString() {
        return "CardItem{" +
                "\ncardName='" + cardName + '\'' +
                "\ncardNameHolder='" + cardNameHolder + '\'' +
                "\ncardOccupation='" + cardOccupation + '\'' +
                "\ncardPhoneNumber='" + cardPhoneNumber + '\'' +
                "\ncardEmail='" + cardEmail + '\'' +
                "\ncardAddress='" + cardAddress + '\'' +
                "\ncardWebsite='" + cardWebsite + '\'' +
                "\ncardSlogan='" + cardSlogan + '\'' +
                "\ncardImageDesignUrl='" + cardImageDesignUrl + '\'' +
                '}';
    }
}
