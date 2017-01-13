package com.analytics.report.entity.response.ya.data.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DimensionTableFormat {
    private String name;

    public DimensionTableFormat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DimensionTableFormat{" +
                "name=" + name +
                '}';
    }
}
