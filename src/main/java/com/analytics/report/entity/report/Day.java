package com.analytics.report.entity.report;

public class Day {
    String hoursOfDay;
    int hoursOfDayVisited;
    int hoursOfDayConversation;

    public Day() {
    }

    public Day(String hoursOfDay, int hoursOfDayVisited, int hoursOfDayConversation) {
        this.hoursOfDay = hoursOfDay;
        this.hoursOfDayVisited = hoursOfDayVisited;
        this.hoursOfDayConversation = hoursOfDayConversation;
    }

    public String getHoursOfDay() {
        return hoursOfDay;
    }

    public void setHoursOfDay(String hoursOfDay) {
        this.hoursOfDay = hoursOfDay;
    }

    public int getHoursOfDayVisited() {
        return hoursOfDayVisited;
    }

    public void setHoursOfDayVisited(int hoursOfDayVisited) {
        this.hoursOfDayVisited = hoursOfDayVisited;
    }

    public int getHoursOfDayConversation() {
        return hoursOfDayConversation;
    }

    public void setHoursOfDayConversation(int hoursOfDayConversation) {
        this.hoursOfDayConversation = hoursOfDayConversation;
    }
}
