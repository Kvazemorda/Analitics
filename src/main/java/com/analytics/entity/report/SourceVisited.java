package com.analytics.entity.report;

public class SourceVisited {
    private String source;
    private double quality;
    private double conversation_per;

    public SourceVisited() {
    }

    public SourceVisited(String source, double quality, double conversation_per) {
        this.source = source;
        this.quality = quality;
        this.conversation_per = conversation_per;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getConversation_per() {
        return conversation_per;
    }

    public void setConversation_per(double conversation_per) {
        this.conversation_per = conversation_per;
    }
}
