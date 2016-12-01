package com.analitics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDataTime {
    private String[] direct_client_logins;
    private int[] ids;
    private String metrics;
    private String accuracy;
    private String callback;
    private String date1;
    private String date2;
    private String dimensions;
    private String filters;
    private String group;
    private int id;
    private boolean include_undefined;
    private String lang;
    private String preset;
    private boolean pretty;
    private boolean proposed_accuracy;
    private String row_ids;
    private String timezone;
    private int top_keys;

    public QueryDataTime() {
    }

    public String[] getDirect_client_logins() {
        return direct_client_logins;
    }

    public void setDirect_client_logins(String[] direct_client_logins) {
        this.direct_client_logins = direct_client_logins;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInclude_undefined() {
        return include_undefined;
    }

    public void setInclude_undefined(boolean include_undefined) {
        this.include_undefined = include_undefined;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public boolean isPretty() {
        return pretty;
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    public boolean isProposed_accuracy() {
        return proposed_accuracy;
    }

    public void setProposed_accuracy(boolean proposed_accuracy) {
        this.proposed_accuracy = proposed_accuracy;
    }

    public String getRow_ids() {
        return row_ids;
    }

    public void setRow_ids(String row_ids) {
        this.row_ids = row_ids;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTop_keys() {
        return top_keys;
    }

    public void setTop_keys(int top_keys) {
        this.top_keys = top_keys;
    }

    @Override
    public String toString() {
        return "QueryDataTime{" +
                "direct_client_logins=" + Arrays.toString(direct_client_logins) +
                ", ids=" + Arrays.toString(ids) +
                ", metrics='" + metrics + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", callback='" + callback + '\'' +
                ", date1='" + date1 + '\'' +
                ", date2='" + date2 + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", filters='" + filters + '\'' +
                ", group='" + group + '\'' +
                ", id=" + id +
                ", include_undefined=" + include_undefined +
                ", lang='" + lang + '\'' +
                ", preset='" + preset + '\'' +
                ", pretty=" + pretty +
                ", proposed_accuracy=" + proposed_accuracy +
                ", row_ids='" + row_ids + '\'' +
                ", timezone='" + timezone + '\'' +
                ", top_keys=" + top_keys +
                '}';
    }
}
