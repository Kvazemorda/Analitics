package com.analytics.entity.report;

public class Week {
    private String dayWeek;
    private double dayWeekVisited;
    private double dayWeekConversation;

    public Week() {
    }

    public Week(String dayWeek, double dayWeekVisited, double dayWeekConversation) {
        this.dayWeek = dayWeek;
        this.dayWeekVisited = dayWeekVisited;
        this.dayWeekConversation = dayWeekConversation;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public double getDayWeekVisited() {
        return dayWeekVisited;
    }

    public void setDayWeekVisited(double dayWeekVisited) {
        this.dayWeekVisited = dayWeekVisited;
    }

    public double getDayWeekConversation() {
        return dayWeekConversation;
    }

    public void setDayWeekConversation(double dayWeekConversation) {
        this.dayWeekConversation = dayWeekConversation;
    }

    @Override
    public String toString() {
        return "Week{" +
                "dayWeek='" + dayWeek + '\'' +
                ", dayWeekVisited=" + dayWeekVisited +
                ", dayWeekConversation=" + dayWeekConversation +
                '}';
    }
}
