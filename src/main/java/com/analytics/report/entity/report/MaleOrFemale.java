package com.analytics.report.entity.report;

public class MaleOrFemale {
    private String maleOrFemale;
    private double maleOrFemaleVisited;
    private double maleOrFemaleConversation;

    public MaleOrFemale() {
    }

    public MaleOrFemale(String maleOrFemale, double maleOrFemaleVisited, double maleOrFemaleConversation) {
        this.maleOrFemale = maleOrFemale;
        this.maleOrFemaleVisited = maleOrFemaleVisited;
        this.maleOrFemaleConversation = maleOrFemaleConversation;
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

    public double getMaleOrFemaleConversation() {
        return maleOrFemaleConversation;
    }

    public void setMaleOrFemaleConversation(double maleOrFemaleConversation) {
        this.maleOrFemaleConversation = maleOrFemaleConversation;
    }
}
