package com.hadi.bc.model;

public class WelcomeItem {
    private String welcomeHeader, welcomeBody;

    public WelcomeItem(String welcomeBody, String welcomeHeader) {
        this.welcomeHeader = welcomeHeader;
        this.welcomeBody = welcomeBody;
    }

    public String getWelcomeHeader() {
        return welcomeHeader;
    }

    public String getWelcomeBody() {
        return welcomeBody;
    }
}

