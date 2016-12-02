package com.analitics.entity.respons.ya.data;

public class SourceVisited {
    private String source;
    private double quality;

    public SourceVisited() {
    }

    public SourceVisited(String source, double quality) {
        this.source = source;
        this.quality = quality;
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
}
