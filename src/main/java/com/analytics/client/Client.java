package com.analytics.client;

import com.analytics.entity.response.ya.data.direct.banner.CompanyDirect;

import java.util.ArrayList;

/**
 * Each client has metricsID and oAuthorID.
 * Them need get before start create report
 */
public class Client {
    private String metricsID;
    private String oAuthorID;
    private ArrayList<CompanyDirect> companyDirect;
    private String oAuthorIDDirect;
    private String loginDirect;

    public Client() {
    }

    public String getMetricsID() {
        return metricsID;
    }

    public void setMetricsID(String metricsID) {
        this.metricsID = metricsID;
    }

    public String getoAuthorID() {
        return oAuthorID;
    }

    public void setoAuthorID(String oAuthorID) {
        this.oAuthorID = oAuthorID;
    }

    public String getoAuthorIDDirect() {
        return oAuthorIDDirect;
    }

    public void setoAuthorIDDirect(String oAuthorIDDirect) {
        this.oAuthorIDDirect = oAuthorIDDirect;
    }

    public String getLoginDirect() {
        return loginDirect;
    }

    public void setLoginDirect(String loginDirect) {
        this.loginDirect = loginDirect;
    }

    public ArrayList<CompanyDirect> getCompanyDirect() {
        return companyDirect;
    }

    public void setCompanyDirect(ArrayList<CompanyDirect> companyDirect) {
        this.companyDirect = companyDirect;
    }
}
