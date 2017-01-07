package com.analytics.entity.report;

public class CompanyCosts implements Comparable {
    private String company;
    private String ad;
    private int click;
    private int position;
    private double conversation;
    private double conversationRate;
    private double costs;
    private double ctr;
    private double costOneConversation;

    public CompanyCosts() {
    }

    public CompanyCosts(String company, String ad, int click, int position, double conversation, double conversationRate, double costs, double ctr, double costOneConversation) {
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

    public double getConversation() {
        return conversation;
    }

    public void setConversation(double conversation) {
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
                '}' + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyCosts that = (CompanyCosts) o;

        if (click != that.click) return false;
        if (position != that.position) return false;
        if (conversation != that.conversation) return false;
        if (Double.compare(that.conversationRate, conversationRate) != 0) return false;
        if (Double.compare(that.costs, costs) != 0) return false;
        if (Double.compare(that.ctr, ctr) != 0) return false;
        if (Double.compare(that.costOneConversation, costOneConversation) != 0) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return ad != null ? ad.equals(that.ad) : that.ad == null;

    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (ad != null ? ad.hashCode() : 0);
        return result;
    }
}
