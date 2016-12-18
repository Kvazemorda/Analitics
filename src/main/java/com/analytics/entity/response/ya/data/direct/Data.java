package com.analytics.entity.response.ya.data.direct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    ArrayList<StatItem> data;

    public Data() {
    }

    public ArrayList<StatItem> getData() {
        return data;
    }

    public void setData(ArrayList<StatItem> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
