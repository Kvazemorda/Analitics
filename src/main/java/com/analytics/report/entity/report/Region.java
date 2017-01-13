package com.analytics.report.entity.report;

public class Region {
    private String regionName;
    private double regionQuality;
    private double regionConversation;

    public Region() {
    }

    public Region(String regionName, double regionQuality, double regionConversation) {
        this.regionName = regionName;
        this.regionQuality = regionQuality;
        this.regionConversation = regionConversation;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public double getRegionQuality() {
        return regionQuality;
    }

    public void setRegionQuality(double regionQuality) {
        this.regionQuality = regionQuality;
    }

    public double getRegionConversation() {
        return regionConversation;
    }

    public void setRegionConversation(double regionConversation) {
        this.regionConversation = regionConversation;
    }
}
