package com.analytics.client;

import java.util.ArrayList;

/**
 * Each client has metricsID and oAuthorID.
 * Them need get before start create report
 */
public class Client {
    private String metricsID;
    private String oAuthorID;
    private ArrayList<String> directCompanyID;
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

    public ArrayList<String> getDirectCompanyID() {
        return directCompanyID;
    }

    public void setDirectCompanyID(ArrayList<String> directCompanyID) {
        this.directCompanyID = directCompanyID;
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
}
