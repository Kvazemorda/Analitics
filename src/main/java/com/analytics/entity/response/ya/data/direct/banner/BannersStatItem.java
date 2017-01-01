package com.analytics.entity.response.ya.data.direct.banner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = false)
public class BannersStatItem {

    private Date StatDate;
    private long BannerID;
    private long PhraseID;
    private int RubricID;
    private int RetargetingID;
    private int WebpageID;
    private String Phrase;
    private float Sum;
    private float SumSearch;
    private float SumContext;
    private int Clicks;
    private int ClicksSearch;
    private int ClicksContext;
    private int Shows;
    private int ShowsSearch;
    private int ShowsContext;
    private String StatType;
    private String DeviceType;
    private float ShowsAveragePosition;
    private float ClicksAveragePosition;
    private float Revenue;
    private float ROI;
    private String companyName;

    public BannersStatItem() {
    }

    public BannersStatItem(Date statDate, long bannerID, long phraseID, int rubricID, int retargetingID, int webpageID,
                           String phrase, float sum, float sumSearch, float sumContext, int clicks, int clicksSearch,
                           int clicksContext, int shows, int showsSearch, int showsContext, String statType,
                           String deviceType, float showsAveragePosition, float clicksAveragePosition,
                           float revenue, float ROI) {
        StatDate = statDate;
        BannerID = bannerID;
        PhraseID = phraseID;
        RubricID = rubricID;
        RetargetingID = retargetingID;
        WebpageID = webpageID;
        Phrase = phrase;
        Sum = sum;
        SumSearch = sumSearch;
        SumContext = sumContext;
        Clicks = clicks;
        ClicksSearch = clicksSearch;
        ClicksContext = clicksContext;
        Shows = shows;
        ShowsSearch = showsSearch;
        ShowsContext = showsContext;
        StatType = statType;
        DeviceType = deviceType;
        ShowsAveragePosition = showsAveragePosition;
        ClicksAveragePosition = clicksAveragePosition;
        Revenue = revenue;
        this.ROI = ROI;
    }

    public Date getStatDate() {
        return StatDate;
    }

    public void setStatDate(Date statDate) {
        StatDate = statDate;
    }

    public long getBannerID() {
        return BannerID;
    }

    public void setBannerID(long bannerID) {
        BannerID = bannerID;
    }

    public long getPhraseID() {
        return PhraseID;
    }

    public void setPhraseID(long phraseID) {
        PhraseID = phraseID;
    }

    public int getRubricID() {
        return RubricID;
    }

    public void setRubricID(int rubricID) {
        RubricID = rubricID;
    }

    public int getRetargetingID() {
        return RetargetingID;
    }

    public void setRetargetingID(int retargetingID) {
        RetargetingID = retargetingID;
    }

    public int getWebpageID() {
        return WebpageID;
    }

    public void setWebpageID(int webpageID) {
        WebpageID = webpageID;
    }

    public String getPhrase() {
        return Phrase;
    }

    public void setPhrase(String phrase) {
        Phrase = phrase;
    }

    public float getSum() {
        return Sum;
    }

    public void setSum(float sum) {
        Sum = sum;
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

    public int getClicks() {
        return Clicks;
    }

    public void setClicks(int clicks) {
        Clicks = clicks;
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

    public int getShows() {
        return Shows;
    }

    public void setShows(int shows) {
        Shows = shows;
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

    public String getStatType() {
        return StatType;
    }

    public void setStatType(String statType) {
        StatType = statType;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public float getShowsAveragePosition() {
        return ShowsAveragePosition;
    }

    public void setShowsAveragePosition(float showsAveragePosition) {
        ShowsAveragePosition = showsAveragePosition;
    }

    public float getClicksAveragePosition() {
        return ClicksAveragePosition;
    }

    public void setClicksAveragePosition(float clicksAveragePosition) {
        ClicksAveragePosition = clicksAveragePosition;
    }

    public float getRevenue() {
        return Revenue;
    }

    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    public float getROI() {
        return ROI;
    }

    public void setROI(float ROI) {
        this.ROI = ROI;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "BannersStatItem{" +
                "StatDate=" + StatDate +
                ", BannerID=" + BannerID +
                ", PhraseID=" + PhraseID +
                ", RubricID=" + RubricID +
                ", RetargetingID=" + RetargetingID +
                ", WebpageID=" + WebpageID +
                ", Phrase='" + Phrase + '\'' +
                ", Sum=" + Sum +
                ", SumSearch=" + SumSearch +
                ", SumContext=" + SumContext +
                ", Clicks=" + Clicks +
                ", ClicksSearch=" + ClicksSearch +
                ", ClicksContext=" + ClicksContext +
                ", Shows=" + Shows +
                ", ShowsSearch=" + ShowsSearch +
                ", ShowsContext=" + ShowsContext +
                ", StatType='" + StatType + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", ShowsAveragePosition=" + ShowsAveragePosition +
                ", ClicksAveragePosition=" + ClicksAveragePosition +
                ", Revenue=" + Revenue +
                ", ROI=" + ROI +
                '}';
    }
}
