package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.response.ya.data.direct.banner.BannersStatItem;
import com.analytics.entity.response.ya.data.direct.banner.Data;
import com.analytics.entity.response.ya.data.metrics.Dimension;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BannerStatItemDAO {

    public HashMap<String, BannersStatItem> getBannerSummaryStat(QueryClient queryClient) {
        HashMap<String, BannersStatItem> mapBanners = new HashMap<>();
        ArrayList<String> listDate = getSevenDays(queryClient.getDate1(), queryClient.getDate2());
        Data data = null;
        for (int i = 0; i < queryClient.getClient().getDirectCompanyID().size(); i++){
            for(int j = 0; j < listDate.size();) {
                JSONArray groupByColumns = new JSONArray();
                groupByColumns.add("clPhrase");
                groupByColumns.add("clDeviceType");
                groupByColumns.add("clAveragePosition");
                JSONObject param = new JSONObject();
                param.put("CampaignID", queryClient.getClient().getDirectCompanyID().get(i));
                param.put("StartDate", listDate.get(j));
                param.put("EndDate", listDate.get(j++));
                param.put("GroupByColumns", groupByColumns);
                param.put("Currency", "RUB");
                param.put("IncludeVAT", "Yes");
                param.put("IncludeDiscount", "Yes");


                JSONObject jsonResult = new JSONObject();
                jsonResult.put("method", "GetBannersStat");
                jsonResult.put("param", param);
                jsonResult.put("token", queryClient.getClient().getoAuthorIDDirect());

                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                try {
                    HttpPost request = new HttpPost("https://api.direct.yandex.ru/live/v4/json/");
                    StringEntity params = new StringEntity(jsonResult.toString());

                    request.addHeader("content-type", "application/json");
                    request.setEntity(params);

            /*Checking response */
                    HttpResponse response = httpClient.execute(request);
                    String jsonString = EntityUtils.toString(response.getEntity());
                    Gson gson = new Gson();
                    data = gson.fromJson(jsonString, Data.class);
                    for (BannersStatItem bannersStatItem : data.getData().getStat()) {
                        bannersStatItem.setCompanyName(queryClient.getClient().getDirectCompanyName().get(i));
                    }
                    sumBannersStatItems(mapBanners, data);
                } catch (Exception ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return mapBanners;
    }

    private void sumBannersStatItems(HashMap<String, BannersStatItem> map , Data data){
        for(BannersStatItem bannersStatItem: data.getData().getStat()){
            BannersStatItem bannersBase = map.get(bannersStatItem.getPhraseID());
            if(bannersBase == null){
                map.put(bannersStatItem.getCompanyName() + bannersStatItem.getPhrase(), bannersStatItem);
            }else {
                bannersBase.setClicks(bannersBase.getClicks() + bannersStatItem.getClicks());
                bannersBase.setShows(bannersBase.getShowsContext() + bannersStatItem.getShows());
                bannersBase.setSum(bannersBase.getSum() + bannersStatItem.getSum());
                map.put(bannersBase.getCompanyName() + bannersBase.getPhrase(), bannersBase);
            }
        }
    }

    public void getCompanyName(QueryClient queryClient){
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "year")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:ad:directOrder")
                .queryParam("metrics", "ym:ad:<currency>AdCost")
                .queryParam("currency", "RUB")
                .queryParam("top_keys", "30")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

        for(int j = 0; j < dimensionDatas.size(); j++){
            String splitId = dimensionDatas.get(j).getDimensions().get(0).getDirect_id().split("N-")[1];
            String companyName = dimensionDatas.get(j).getDimensions().get(0).getName();
            queryClient.getClient().getDirectCompanyID().add(splitId);
            queryClient.getClient().getDirectCompanyName().add(companyName);
        }
    }

    public int getConversationOfBanner(QueryClient queryClient){
        //String clearPhras = clearingPhrase(phrases);
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/")
                .path("stat/v1/data")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:s:UTMContent")
                .queryParam("dimensions", "ym:s:UTMCampaign")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
             //   .queryParam("filters=ym:s:UTMContent=", "'" + clearPhras + "'" + " AND ym:s:UTMCampaign==" + "'" + companyName + "'")
                .queryParam("currency", "RUB")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        int conversation = 0;
        for(int j = 0; j < dimensionDatas.size(); j++){
            ArrayList<Dimension> dimensions = dimensionDatas.get(j).getDimensions();
            for(Dimension dimension: dimensions){
                System.out.println(dimension.getName());
            }
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();
            for(Double conversationNext: metrics.get(0)) {
                conversation += conversationNext;
            }
        }

        return conversation;
    }

    private String clearingPhrase(String phrase){
        String newPhrase = phrase;
        if(newPhrase.contains(" -")){
            newPhrase = newPhrase.substring(0, newPhrase.indexOf(" -"));
        }
        if(newPhrase.contains("+")){

            String[] splitString = newPhrase.split("[+]");
            String newPhrases = "";
            for(String string: splitString){
                newPhrases += string;
            }
            newPhrase = newPhrases;
        }

        return newPhrase;
    }

    private ArrayList<String> getSevenDays(String start, String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> list = new ArrayList<>();
        try {
            Date dateStart = sdf.parse(start);
            Date dateEnd = sdf.parse(end);
            list.add(start);
            if(dateStart.equals(dateEnd)) {
                list.add(end);
            }else{
                long sevenDays = 1000*60*60*24*7;
                while((dateStart.getTime() + sevenDays) < dateEnd.getTime()){
                    dateStart.setTime(dateStart.getTime() + sevenDays);
                    list.add(sdf.format(dateStart));
                }
                dateStart.setTime(dateStart.getTime() + (dateEnd.getTime() - dateStart.getTime()));
                list.add(sdf.format(dateStart));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}