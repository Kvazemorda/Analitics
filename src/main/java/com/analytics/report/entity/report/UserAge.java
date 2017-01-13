package com.analytics.report.entity.report;

public class UserAge {
    private String rangeAgeUser;
    private double userVisited;
    private double userConversation;

    public UserAge() {
    }

    public UserAge(String rangeAgeUser, double userVisited, double userConversation) {
        this.rangeAgeUser = rangeAgeUser;
        this.userVisited = userVisited;
        this.userConversation = userConversation;
    }

    public String getRangeAgeUser() {
        return rangeAgeUser;
    }

    public void setRangeAgeUser(String rangeAgeUser) {
        this.rangeAgeUser = rangeAgeUser;
    }

    public double getUserVisited() {
        return userVisited;
    }

    public void setUserVisited(double userVisited) {
        this.userVisited = userVisited;
    }

    public double getUserConversation() {
        return userConversation;
    }

    public void setUserConversation(double userConversation) {
        this.userConversation = userConversation;
    }
}
