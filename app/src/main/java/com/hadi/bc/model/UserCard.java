package com.hadi.bc.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserCard implements Parcelable {

    public static final Parcelable.Creator<UserCard> CREATOR = new Parcelable.Creator<UserCard>() {
        @Override
        public UserCard createFromParcel(Parcel source) {
            return new UserCard(source);
        }

        @Override
        public UserCard[] newArray(int size) {
            return new UserCard[size];
        }
    };
    private String cardAddress;
    private String cardEmail;
    private String cardNameHolder;
    private String cardOccupation;
    private String cardPhoneNumber;
    private String cardSlogan;
    private String cardTitle;
    private String cardWebsite;
    private String url;

    public UserCard(String cardAddress, String cardEmail, String cardNameHolder, String cardOccupation,
                    String cardPhoneNumber, String cardSlogan, String cardTitle, String cardWebsite) {
        this.cardAddress = cardAddress;
        this.cardEmail = cardEmail;
        this.cardNameHolder = cardNameHolder;
        this.cardOccupation = cardOccupation;
        this.cardPhoneNumber = cardPhoneNumber;
        this.cardSlogan = cardSlogan;
        this.cardTitle = cardTitle;
        this.cardWebsite = cardWebsite;
    }

    public UserCard() {
    }

    protected UserCard(Parcel in) {
        this.cardAddress = in.readString();
        this.cardEmail = in.readString();
        this.cardNameHolder = in.readString();
        this.cardOccupation = in.readString();
        this.cardPhoneNumber = in.readString();
        this.cardSlogan = in.readString();
        this.cardTitle = in.readString();
        this.cardWebsite = in.readString();
        this.url = in.readString();
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public String getCardEmail() {
        return cardEmail;
    }

    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail;
    }

    public String getCardNameHolder() {
        return cardNameHolder;
    }

    public void setCardNameHolder(String cardNameHolder) {
        this.cardNameHolder = cardNameHolder;
    }

    public String getCardOccupation() {
        return cardOccupation;
    }

    public void setCardOccupation(String cardOccupation) {
        this.cardOccupation = cardOccupation;
    }

    public String getCardPhoneNumber() {
        return cardPhoneNumber;
    }

    public void setCardPhoneNumber(String cardPhoneNumber) {
        this.cardPhoneNumber = cardPhoneNumber;
    }

    public String getCardSlogan() {
        return cardSlogan;
    }

    public void setCardSlogan(String cardSlogan) {
        this.cardSlogan = cardSlogan;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardWebsite() {
        return cardWebsite;
    }

    public void setCardWebsite(String cardWebsite) {
        this.cardWebsite = cardWebsite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardAddress);
        dest.writeString(this.cardEmail);
        dest.writeString(this.cardNameHolder);
        dest.writeString(this.cardOccupation);
        dest.writeString(this.cardPhoneNumber);
        dest.writeString(this.cardSlogan);
        dest.writeString(this.cardTitle);
        dest.writeString(this.cardWebsite);
        dest.writeString(this.url);
    }
}
