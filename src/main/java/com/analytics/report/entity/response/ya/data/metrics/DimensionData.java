package com.analytics.report.entity.response.ya.data.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionData {
    private ArrayList<Dimension> dimensions;
    private ArrayList<ArrayList<Double>> metrics;

    public DimensionData() {
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public ArrayList<ArrayList<Double>> getMetrics() {
        return metrics;
    }

    public void setMetrics(ArrayList<ArrayList<Double>> metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "DimensionData{" +
                "dimensions=" + dimensions +
                ", metrics=" + metrics +
                '}';
    }
}
