package com.analytics.entity.response.ya.data.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceVisitedFromYaByTime {
    private QueryFromResponseYa query;
    private ArrayList<DimensionData> data;
    private long total_rows;
    private boolean total_rows_rounded;
    private boolean sampled;
    private double sample_share;
    private long sample_size;
    private long sample_space;
    private int data_lag;
    private ArrayList<Double[]> totals;
    private int last_period_index;
    private ArrayList<String[]> time_intervals;

    public SourceVisitedFromYaByTime() {

    }

    public QueryFromResponseYa getQuery() {
        return query;
    }

    public void setQuery(QueryFromResponseYa query) {
        this.query = query;
    }

    public ArrayList<DimensionData> getData() {
        return data;
    }

    public void setData(ArrayList<DimensionData> data) {
        this.data = data;
    }

    public long getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(long total_rows) {
        this.total_rows = total_rows;
    }

    public boolean isTotal_rows_rounded() {
        return total_rows_rounded;
    }

    public void setTotal_rows_rounded(boolean total_rows_rounded) {
        this.total_rows_rounded = total_rows_rounded;
    }

    public boolean isSampled() {
        return sampled;
    }

    public void setSampled(boolean sampled) {
        this.sampled = sampled;
    }

    public double getSample_share() {
        return sample_share;
    }

    public void setSample_share(double sample_share) {
        this.sample_share = sample_share;
    }

    public long getSample_size() {
        return sample_size;
    }

    public void setSample_size(long sample_size) {
        this.sample_size = sample_size;
    }

    public long getSample_space() {
        return sample_space;
    }

    public void setSample_space(long sample_space) {
        this.sample_space = sample_space;
    }

    public int getData_lag() {
        return data_lag;
    }

    public void setData_lag(int data_lag) {
        this.data_lag = data_lag;
    }

    public int getLast_period_index() {
        return last_period_index;
    }

    public void setLast_period_index(int last_period_index) {
        this.last_period_index = last_period_index;
    }

    public ArrayList<Double[]> getTotals() {
        return totals;
    }

    public void setTotals(ArrayList<Double[]> totals) {
        this.totals = totals;
    }

    public ArrayList<String[]> getTime_intervals() {
        return time_intervals;
    }

    public void setTime_intervals(ArrayList<String[]> time_intervals) {
        this.time_intervals = time_intervals;
    }

    @Override
    public String toString() {
        return "SourceVisitedFromYaByTime{" +
                "query=" + query +
                ", data=" + data +
                ", total_rows=" + total_rows +
                ", total_rows_rounded=" + total_rows_rounded +
                ", sampled=" + sampled +
                ", sample_share=" + sample_share +
                ", sample_size=" + sample_size +
                ", sample_space=" + sample_space +
                ", data_lag=" + data_lag +
                ", totals=" + totals +
                ", last_period_index=" + last_period_index +
                ", time_intervals=" + time_intervals +
                '}';
    }
}
