package com.analytics.entity.report;

public class CompanyCosts implements Comparable {
    private String company;
    private String ad;
    private int click;
    private int position;
    private int conversation;
    private double conversationRate;
    private double costs;
    private double ctr;
    private double costOneConversation;

    public CompanyCosts() {
    }

    public CompanyCosts(String company, String ad, int click, int position, int conversation, double conversationRate, double costs, double ctr, double costOneConversation) {
        this.company = company;
        this.ad = ad;
        this.click = click;
        this.position = position;
        this.conversation = conversation;
        this.conversationRate = conversationRate;
        this.costs = costs;
        this.ctr = ctr;
        this.costOneConversation = costOneConversation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public double getConversationRate() {
        return conversationRate;
    }

    public void setConversationRate(double conversationRate) {
        this.conversationRate = conversationRate;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public double getCostOneConversation() {
        return costOneConversation;
    }

    public void setCostOneConversation(double costOneConversation) {
        this.costOneConversation = costOneConversation;
    }

    @Override
    public int compareTo(Object o) {
        CompanyCosts companyCosts = (CompanyCosts) o;
        if(companyCosts.conversation == 0 && this.conversation == 0){
            if(this.getCosts() > companyCosts.getCosts()){
                return 1;
            }else {
                return -1;
            }
        }else if(companyCosts.conversation == 0){
            return 1;
        }else if(this.conversation == 0){
            return -1;
        }else if(this.costOneConversation < companyCosts.costOneConversation){
            return 1;
        }else if(this.costOneConversation > companyCosts.costOneConversation){
            return -1;
        }else return 0;
    }

    @Override
    public String toString() {
        return "CompanyExpansive{" +
                "company='" + company + '\'' +
                ", ad='" + ad + '\'' +
                ", click=" + click +
                ", position=" + position +
                ", conversation=" + conversation +
                ", conversationRate=" + conversationRate +
                ", costs=" + costs +
                ", ctr=" + ctr +
                ", costOneConversation=" + costOneConversation +
                '}';
    }
}
