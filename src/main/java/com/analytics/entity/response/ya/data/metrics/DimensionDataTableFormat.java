package com.analytics.entity.response.ya.data.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionDataTableFormat {
    private ArrayList<DimensionTableFormat> dimensions;
    private ArrayList<Double> metrics;

    public DimensionDataTableFormat() {
    }

    public ArrayList<DimensionTableFormat> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<DimensionTableFormat> dimensions) {
        this.dimensions = dimensions;
    }

    public ArrayList<Double> getMetrics() {
        return metrics;
    }

    public void setMetrics(ArrayList<Double> metrics) {
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
