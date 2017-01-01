package com.analytics.entity.response.ya.data.direct.banner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Data {
    private DataBanner data;

    public Data() {
    }

    public DataBanner getData() {
        return data;
    }

    public void setData(DataBanner data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
