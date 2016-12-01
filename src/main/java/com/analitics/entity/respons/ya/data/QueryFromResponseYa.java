package com.analitics.entity.respons.ya.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryFromResponseYa {
    private int[] ids;
    private String[] dimensions;
    private String[] metrics;
    private String[] sort;
    private String date1;
    private String date2;
    private String attribution;
    private String group;
    private String auto_group_size;
    private String quantile;
    private String currency;
    private String auto_group_type;

    public QueryFromResponseYa() {
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public String[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public String[] getMetrics() {
        return metrics;
    }

    public void setMetrics(String[] metrics) {
        this.metrics = metrics;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
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

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAuto_group_size() {
        return auto_group_size;
    }

    public void setAuto_group_size(String auto_group_size) {
        this.auto_group_size = auto_group_size;
    }

    public String getQuantile() {
        return quantile;
    }

    public void setQuantile(String quantile) {
        this.quantile = quantile;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuto_group_type() {
        return auto_group_type;
    }

    public void setAuto_group_type(String auto_group_type) {
        this.auto_group_type = auto_group_type;
    }

    @Override
    public String toString() {
        return "QueryFromResponseYa{" +
                "ids=" + Arrays.toString(ids) +
                ", dimensions=" + Arrays.toString(dimensions) +
                ", metrics=" + Arrays.toString(metrics) +
                ", sort=" + Arrays.toString(sort) +
                ", date1='" + date1 + '\'' +
                ", date2='" + date2 + '\'' +
                ", attribution='" + attribution + '\'' +
                ", group='" + group + '\'' +
                ", auto_group_size='" + auto_group_size + '\'' +
                ", quantile='" + quantile + '\'' +
                ", currency='" + currency + '\'' +
                ", auto_group_type='" + auto_group_type + '\'' +
                '}';
    }
}
