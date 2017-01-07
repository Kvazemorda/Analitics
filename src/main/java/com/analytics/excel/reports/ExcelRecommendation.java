package com.analytics.excel.reports;

public class ExcelRecommendation {
    private String deviceRecommendation;
    private String ageUserLowConversationRecommendation;
    private String ageUserHighConversationRecommendation;
    private String maleOrFemaleRecommendation;
    private String regionRecommendation;
    private String weekRecommendation;
    private String dayRecommendation;
    private String keyWordNoConversationRecommendation;
    private String keyWordExpensiveRecommendation;
    private String keyWordCheapRecommendation;
    private String searchOrContext;

    public ExcelRecommendation() {
    }

    public String getDeviceRecommendation() {
        return deviceRecommendation;
    }

    public void setDeviceRecommendation(String deviceRecommendation) {
        this.deviceRecommendation = deviceRecommendation;
    }

    public String getMaleOrFemaleRecommendation() {
        return maleOrFemaleRecommendation;
    }

    public void setMaleOrFemaleRecommendation(String maleOrFemaleRecommendation) {
        this.maleOrFemaleRecommendation = maleOrFemaleRecommendation;
    }

    public String getRegionRecommendation() {
        return regionRecommendation;
    }

    public void setRegionRecommendation(String regionRecommendation) {
        this.regionRecommendation = regionRecommendation;
    }

    public String getWeekRecommendation() {
        return weekRecommendation;
    }

    public void setWeekRecommendation(String weekRecommendation) {
        this.weekRecommendation = weekRecommendation;
    }

    public String getDayRecommendation() {
        return dayRecommendation;
    }

    public void setDayRecommendation(String dayRecommendation) {
        this.dayRecommendation = dayRecommendation;
    }

    public String getKeyWordNoConversationRecommendation() {
        return keyWordNoConversationRecommendation;
    }

    public void setKeyWordNoConversationRecommendation(String keyWordNoConversationRecommendation) {
        this.keyWordNoConversationRecommendation = keyWordNoConversationRecommendation;
    }

    public String getKeyWordExpensiveRecommendation() {
        return keyWordExpensiveRecommendation;
    }

    public void setKeyWordExpensiveRecommendation(String keyWordExpensiveRecommendation) {
        this.keyWordExpensiveRecommendation = keyWordExpensiveRecommendation;
    }

    public String getKeyWordCheapRecommendation() {
        return keyWordCheapRecommendation;
    }

    public void setKeyWordCheapRecommendation(String keyWordCheapRecommendation) {
        this.keyWordCheapRecommendation = keyWordCheapRecommendation;
    }

    public String getAgeUserLowConversationRecommendation() {
        return ageUserLowConversationRecommendation;
    }

    public void setAgeUserLowConversationRecommendation(String ageUserLowConversationRecommendation) {
        this.ageUserLowConversationRecommendation = ageUserLowConversationRecommendation;
    }

    public String getAgeUserHighConversationRecommendation() {
        return ageUserHighConversationRecommendation;
    }

    public void setAgeUserHighConversationRecommendation(String ageUserHighConversationRecommendation) {
        this.ageUserHighConversationRecommendation = ageUserHighConversationRecommendation;
    }

    public String getSearchOrContext() {
        return searchOrContext;
    }

    public void setSearchOrContext(String searchOrContext) {
        this.searchOrContext = searchOrContext;
    }

    @Override
    public String toString() {
        return "ExcelRecommendation{" +
                "deviceRecommendation='" + deviceRecommendation + '\'' +
                ", ageUserLowConversationRecommendation='" + ageUserLowConversationRecommendation + '\'' +
                ", ageUserHighConversationRecommendation='" + ageUserHighConversationRecommendation + '\'' +
                ", maleOrFemaleRecommendation='" + maleOrFemaleRecommendation + '\'' +
                ", regionRecommendation='" + regionRecommendation + '\'' +
                ", weekRecommendation='" + weekRecommendation + '\'' +
                ", dayRecommendation='" + dayRecommendation + '\'' +
                ", keyWordNoConversationRecommendation='" + keyWordNoConversationRecommendation + '\'' +
                ", keyWordExpensiveRecommendation='" + keyWordExpensiveRecommendation + '\'' +
                ", keyWordCheapRecommendation='" + keyWordCheapRecommendation + '\'' +
                ", searchOrContext='" + searchOrContext + '\'' +
                '}';
    }
}
