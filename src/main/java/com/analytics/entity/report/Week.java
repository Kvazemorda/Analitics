package com.analytics.entity.report;

public class Week implements Comparable {
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


    @Override
    public int compareTo(Object o) {
        Week week = (Week) o;

        if(this.dayWeek.equals("Понедельник")){
            return 1;
        }
        if(this.dayWeek.equals("Вторник") && !week.dayWeek.equals("Понедельник")){
            return 1;
        }
        if(this.dayWeek.equals("Среда") && (!week.dayWeek.equals("Понедельник") || !week.dayWeek.equals("Вторник"))){
            return 1;
        }
        if(this.dayWeek.equals("Четверг") && (!week.dayWeek.equals("Понедельник") || !week.dayWeek.equals("Вторник") || !week.dayWeek.equals("Среда"))){
            return 1;
        }
        if(this.dayWeek.equals("Пятница") && (!week.dayWeek.equals("Понедельник") || !week.dayWeek.equals("Вторник") || !week.dayWeek.equals("Среда") || !week.dayWeek.equals("Четверг"))){
            return 1;
        }
        if(this.dayWeek.equals("Суббота") && (!week.dayWeek.equals("Понедельник") || !week.dayWeek.equals("Вторник") || !week.dayWeek.equals("Среда") || !week.dayWeek.equals("Четверг") || !week.dayWeek.equals("Пятница"))){
            return 1;
        }
        if(this.dayWeek.equals("Воскресенье")){
            return -1;
        }
        return 1;
    }
}
