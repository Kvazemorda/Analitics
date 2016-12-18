package com.analytics.entity.report;

public class SourceDetail {
    private int countVisited;
    private String sourceDetail;
    private double conversationDetail;

    public SourceDetail() {
    }

    public SourceDetail(int countVisited, String sourceDetail, double conversationDetail) {
        this.countVisited = countVisited;
        this.sourceDetail = sourceDetail;
        this.conversationDetail = conversationDetail;
    }

    public int getCountVisited() {
        return countVisited;
    }

    public void setCountVisited(int countVisited) {
        this.countVisited = countVisited;
    }

    public String getSourceDetail() {
        return sourceDetail;
    }

    public void setSourceDetail(String sourceDetail) {
        this.sourceDetail = sourceDetail;
    }

    public double getConversationDetail() {
        return conversationDetail;
    }

    public void setConversationDetail(double conversationDetail) {
        this.conversationDetail = conversationDetail;
    }


}
