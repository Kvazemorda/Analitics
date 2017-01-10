package com.analytics.client;

import com.analytics.entity.response.ya.data.direct.banner.CompanyDirect;

import java.util.ArrayList;

public class QueryClient {

    private String date1;
    private String date2;
    private double maxCpa;
    private Client client;
    private ArrayList<CompanyDirect> companyDirect;

    public QueryClient(String date1, String date2, Client client, double maxCpa) {
        this.date1 = date1;
        this.date2 = date2;
        this.client = client;
        this.maxCpa = maxCpa;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getMaxCpa() {
        return maxCpa;
    }

    public void setMaxCpa(double maxCpa) {
        this.maxCpa = maxCpa;
    }

    public ArrayList<CompanyDirect> getCompanyDirect() {
        return companyDirect;
    }

    public void setCompanyDirect(ArrayList<CompanyDirect> companyDirect) {
        this.companyDirect = companyDirect;
    }
}
