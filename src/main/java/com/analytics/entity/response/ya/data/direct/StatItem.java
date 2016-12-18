package com.analytics.entity.response.ya.data.direct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatItem {
    private int CampaignID;
    private Date StatDate;
    private float SumSearch;
    private float SumContext;
    private int ShowsSearch;
    private int ShowsContext;
    private int ClicksSearch;
    private int ClicksContext;
    private float SessionDepthSearch;
    private float SessionDepthContext;
    private float GoalConversionSearch;
    private float GoalConversionContext;
    private float GoalCostSearch;
    private float GoalCostContext;
    private float CostSearch;
    private float CostContext;

    public StatItem() {
    }

    public StatItem(int campaignID, Date statDate, float sumSearch, float sumContext, int showsSearch,
                    int showsContext, int clicksSearch, int clicksContext, float sessionDepthSearch,
                    float sessionDepthContext, float goalConversionSearch, float goalConversionContext,
                    float goalCostSearch, float goalCostContext) {
        CampaignID = campaignID;
        StatDate = statDate;
        SumSearch = sumSearch;
        SumContext = sumContext;
        ShowsSearch = showsSearch;
        ShowsContext = showsContext;
        ClicksSearch = clicksSearch;
        ClicksContext = clicksContext;
        SessionDepthSearch = sessionDepthSearch;
        SessionDepthContext = sessionDepthContext;
        GoalConversionSearch = goalConversionSearch;
        GoalConversionContext = goalConversionContext;
        GoalCostSearch = goalCostSearch;
        GoalCostContext = goalCostContext;
    }

    public int getCampaignID() {
        return CampaignID;
    }

    public void setCampaignID(int campaignID) {
        CampaignID = campaignID;
    }

    public Date getStatDate() {
        return StatDate;
    }

    public void setStatDate(Date statDate) {
        StatDate = statDate;
    }

    public float getSumSearch() {
        return SumSearch;
    }

    public void setSumSearch(float sumSearch) {
        SumSearch = sumSearch;
    }

    public float getSumContext() {
        return SumContext;
    }

    public void setSumContext(float sumContext) {
        SumContext = sumContext;
    }

    public int getShowsSearch() {
        return ShowsSearch;
    }

    public void setShowsSearch(int showsSearch) {
        ShowsSearch = showsSearch;
    }

    public int getShowsContext() {
        return ShowsContext;
    }

    public void setShowsContext(int showsContext) {
        ShowsContext = showsContext;
    }

    public int getClicksSearch() {
        return ClicksSearch;
    }

    public void setClicksSearch(int clicksSearch) {
        ClicksSearch = clicksSearch;
    }

    public int getClicksContext() {
        return ClicksContext;
    }

    public void setClicksContext(int clicksContext) {
        ClicksContext = clicksContext;
    }

    public float getSessionDepthSearch() {
        return SessionDepthSearch;
    }

    public void setSessionDepthSearch(float sessionDepthSearch) {
        SessionDepthSearch = sessionDepthSearch;
    }

    public float getSessionDepthContext() {
        return SessionDepthContext;
    }

    public void setSessionDepthContext(float sessionDepthContext) {
        SessionDepthContext = sessionDepthContext;
    }

    public float getGoalConversionSearch() {
        return GoalConversionSearch;
    }

    public void setGoalConversionSearch(float goalConversionSearch) {
        GoalConversionSearch = goalConversionSearch;
    }

    public float getGoalConversionContext() {
        return GoalConversionContext;
    }

    public void setGoalConversionContext(float goalConversionContext) {
        GoalConversionContext = goalConversionContext;
    }

    public float getGoalCostSearch() {
        return GoalCostSearch;
    }

    public void setGoalCostSearch(float goalCostSearch) {
        GoalCostSearch = goalCostSearch;
    }

    public float getGoalCostContext() {
        return GoalCostContext;
    }

    public void setGoalCostContext(float goalCostContext) {
        GoalCostContext = goalCostContext;
    }

    public float getCostSearch() {
        return CostSearch;
    }

    public void setCostSearch(float costSearch) {
        CostSearch = costSearch;
    }

    public float getCostContext() {
        return CostContext;
    }

    public void setCostContext(float costContext) {
        CostContext = costContext;
    }

    @Override
    public String toString() {
        return "GetSummaryStat{" +
                "CampaignID=" + CampaignID +
                ", StatDate=" + StatDate +
                ", SumSearch=" + SumSearch +
                ", SumContext=" + SumContext +
                ", ShowsSearch=" + ShowsSearch +
                ", ShowsContext=" + ShowsContext +
                ", ClicksSearch=" + ClicksSearch +
                ", ClicksContext=" + ClicksContext +
                ", SessionDepthSearch=" + SessionDepthSearch +
                ", SessionDepthContext=" + SessionDepthContext +
                ", GoalConversionSearch=" + GoalConversionSearch +
                ", GoalConversionContext=" + GoalConversionContext +
                ", GoalCostSearch=" + GoalCostSearch +
                ", GoalCostContext=" + GoalCostContext +
                '}';
    }
}
