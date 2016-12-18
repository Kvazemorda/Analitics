package com.analytics.entity.report;

public class Spend {
    private double wasSpend;
    private double wasSpendNotReachGoal;
    private double costConversation;
    private double effectConversationPer;

    public Spend() {
    }

    public double getWasSpend() {
        return wasSpend;
    }

    public void setWasSpend(double wasSpend) {
        this.wasSpend = wasSpend;
    }

    public double getWasSpendNotReachGoal() {
        return wasSpendNotReachGoal;
    }

    public void setWasSpendNotReachGoal(double wasSpendNotReachGoal) {
        this.wasSpendNotReachGoal = wasSpendNotReachGoal;
    }

    public double getCostConversation() {
        return costConversation;
    }

    public void setCostConversation(double costConversation) {
        this.costConversation = costConversation;
    }

    public double getEffectConversationPer() {
        return effectConversationPer;
    }

    public void setEffectConversationPer(double effectConversationPer) {
        this.effectConversationPer = effectConversationPer;
    }
}
