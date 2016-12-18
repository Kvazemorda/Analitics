package com.analytics.entity.report;

public class MaleOrFemale {
    private String maleOrFemale;
    private double maleOrFemaleVisited;
    private double maleOrFemaleConversationPer;

    public MaleOrFemale() {
    }

    public MaleOrFemale(String maleOrFemale, double maleOrFemaleVisited, double maleOrFemaleConversationPer) {
        this.maleOrFemale = maleOrFemale;
        this.maleOrFemaleVisited = maleOrFemaleVisited;
        this.maleOrFemaleConversationPer = maleOrFemaleConversationPer;
    }

    public String getMaleOrFemale() {
        return maleOrFemale;
    }

    public void setMaleOrFemale(String maleOrFemale) {
        this.maleOrFemale = maleOrFemale;
    }

    public double getMaleOrFemaleVisited() {
        return maleOrFemaleVisited;
    }

    public void setMaleOrFemaleVisited(double maleOrFemaleVisited) {
        this.maleOrFemaleVisited = maleOrFemaleVisited;
    }

    public double getMaleOrFemaleConversationPer() {
        return maleOrFemaleConversationPer;
    }

    public void setMaleOrFemaleConversationPer(double maleOrFemaleConversationPer) {
        this.maleOrFemaleConversationPer = maleOrFemaleConversationPer;
    }
}
