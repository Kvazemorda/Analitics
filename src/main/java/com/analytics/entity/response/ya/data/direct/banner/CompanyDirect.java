package com.analytics.entity.response.ya.data.direct.banner;

import java.util.ArrayList;

public class CompanyDirect {
    private ArrayList<BannersStatItem> bannersStatItems;
    private String companyID;
    private String companyName;

    public CompanyDirect() {
    }

    public CompanyDirect(String companyID, String companyName) {
        this.companyID = companyID;
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyDirect that = (CompanyDirect) o;

        if (companyID != null ? !companyID.equals(that.companyID) : that.companyID != null) return false;
        return companyName != null ? companyName.equals(that.companyName) : that.companyName == null;

    }

    @Override
    public int hashCode() {
        int result = companyID != null ? companyID.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }

    public ArrayList<BannersStatItem> getBannersStatItems() {
        return bannersStatItems;
    }

    public void setBannersStatItems(ArrayList<BannersStatItem> bannersStatItems) {
        this.bannersStatItems = bannersStatItems;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
