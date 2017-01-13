package com.analytics.report.entity.response.ya.data.direct;

public class AdvertCost {
    Double advertCost;
    int advertClick;
    double advertClickOneCost;

    public AdvertCost() {
    }

    public AdvertCost(Double advertCost, int advertClick) {
        this.advertCost = advertCost;
        this.advertClick = advertClick;
        this.advertClickOneCost = advertCost/advertClick;
    }

    public Double getAdvertCost() {
        return advertCost;
    }

    public void setAdvertCost(Double advertCost) {
        this.advertCost = advertCost;
    }

    public int getAdvertClick() {
        return advertClick;
    }

    public void setAdvertClick(int advertClick) {
        this.advertClick = advertClick;
    }

    public double getAdvertClickOneCost() {
        return advertClickOneCost;
    }

    public void setAdvertClickOneCost(double advertClickOneCost) {
        this.advertClickOneCost = advertClickOneCost;
    }

    @Override
    public String toString() {
        return "AdvertCost{" +
                "advertCost=" + advertCost +
                ", advertClick=" + advertClick +
                ", advertClickOneCost=" + advertClickOneCost +
                '}';
    }
}
