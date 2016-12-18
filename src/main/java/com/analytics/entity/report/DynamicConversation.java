package com.analytics.entity.report;

import java.util.Date;

public class DynamicConversation implements Comparable{
    private int visited;
    private int countConversation;
    private Date date;
    private double visitedDiv;


    public DynamicConversation() {
    }

    public DynamicConversation(int visited, int countConversation, Date date, double visitedDiv) {
        this.visited = visited;
        this.countConversation = countConversation;
        this.date = date;
        this.visitedDiv = visitedDiv;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public int getCountConversation() {
        return countConversation;
    }

    public void setCountConversation(int countConversation) {
        this.countConversation = countConversation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getVisitedDiv() {
        return visitedDiv;
    }

    public void setVisitedDiv(double visitedDiv) {
        this.visitedDiv = visitedDiv;
    }

    @Override
    public int compareTo(Object o) {
        DynamicConversation dynamic = (DynamicConversation) o;
        if(this.date.after(dynamic.getDate())){
            return 1;
        }else if(this.date.before(dynamic.getDate())){
            return -1;
        }
        return 0;
    }
}
