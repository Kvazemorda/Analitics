package com.analytics.entity.report;

public class Funnel {
    private int qualityShowAdvert;
    private int qualityClick;
    private Double spendAll;
    private Double effecientConversation;
    private int qualityGetGoals;
    private Double cpaCost;

    public Funnel() {
    }

    public int getQualityShowAdvert() {
        return qualityShowAdvert;
    }

    public void setQualityShowAdvert(int qualityShowAdvert) {
        this.qualityShowAdvert = qualityShowAdvert;
    }

    public int getQualityClick() {
        return qualityClick;
    }

    public void setQualityClick(int qualityClick) {
        this.qualityClick = qualityClick;
    }

    public Double getSpendAll() {
        return spendAll;
    }

    public void setSpendAll(Double spendAll) {
        this.spendAll = spendAll;
    }

    public Double getEffecientConversation() {
        return effecientConversation;
    }

    public void setEffecientConversation(Double effecientConversation) {
        this.effecientConversation = effecientConversation;
    }

    public int getQualityGetGoals() {
        return qualityGetGoals;
    }

    public void setQualityGetGoals(int qualityGetGoals) {
        this.qualityGetGoals = qualityGetGoals;
    }

    public Double getCpaCost() {
        return cpaCost;
    }

    public void setCpaCost(Double cpaCost) {
        this.cpaCost = cpaCost;
    }
}
