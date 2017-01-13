package com.analytics.report.entity.response.ya.data.direct.banner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBanner {
    private int CampaignID;
    private Date StartDate;
    private Date EndDate;
    ArrayList<BannersStatItem> Stat;

    public DataBanner() {
    }

    public DataBanner(int campaignID, Date startDate, Date endDate, ArrayList<BannersStatItem> data) {
        CampaignID = campaignID;
        StartDate = startDate;
        EndDate = endDate;
        this.Stat = data;
    }

    public int getCampaignID() {
        return CampaignID;
    }

    public void setCampaignID(int campaignID) {
        CampaignID = campaignID;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public ArrayList<BannersStatItem> getStat() {
        return Stat;
    }

    public void setStat(ArrayList<BannersStatItem> stat) {
        this.Stat = stat;
    }

    @Override
    public String toString() {
        return "Data{" +
                "CampaignID=" + CampaignID +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                ", Stat=" + Stat +
                '}';
    }
}
