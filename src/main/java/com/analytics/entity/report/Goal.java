package com.analytics.entity.report;

public class Goal {
    private String goal;
    private double quality;

    public Goal() {
    }

    public Goal(String goal, double quality) {
        this.goal = goal;
        this.quality = quality;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
