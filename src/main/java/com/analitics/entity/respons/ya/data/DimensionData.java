package com.analitics.entity.respons.ya.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionData {
    private Dimension[] dimensions;
    private List<Double>[] metrics;

    public DimensionData() {
    }

    public Dimension[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension[] dimensions) {
        this.dimensions = dimensions;
    }

    public List<Double>[] getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Double>[] metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "DimensionData{" +
                "dimensions=" + Arrays.toString(dimensions) +
                ", metrics=" + Arrays.toString(metrics) +
                '}';
    }
}
