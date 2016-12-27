package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.response.ya.data.direct.Data;
import com.analytics.entity.response.ya.data.direct.StatItem;
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
import java.util.ArrayList;

public class SearchOrContextDAO {
    private StatItem statItem = null;
    public StatItem getSummaryStat(QueryClient queryClient) {
        JSONArray campaingIDS = new JSONArray();
        for (String campanyID : queryClient.getClient().getDirectCompanyID()) {
            campaingIDS.add(campanyID);
        }
        JSONObject param = new JSONObject();
        param.put("CampaignIDS", campaingIDS);
        param.put("StartDate", queryClient.getDate1());
        param.put("EndDate", queryClient.getDate2());
        param.put("Currency", "RUB");
        param.put("IncludeVAT", "Yes");
        param.put("IncludeDiscount", "Yes");

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("method", "GetSummaryStat");
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
            Data data = gson.fromJson(jsonString, Data.class);
            ArrayList<StatItem> list = data.getData();
            statItem = new StatItem();
            double costSearchByPeriod = 0.0;
            double costContextByPeriod = 0.0;
            for(int i = 0; i < list.size(); i++){
                statItem.setClicksContext(statItem.getClicksContext() + list.get(i).getClicksContext());
                statItem.setClicksSearch(statItem.getClicksSearch() + list.get(i).getClicksSearch());
                statItem.setGoalConversionContext(statItem.getGoalConversionContext() + list.get(i).getGoalConversionContext());
                statItem.setGoalConversionSearch(statItem.getGoalConversionSearch() + list.get(i).getGoalConversionSearch());
                statItem.setGoalCostContext(statItem.getGoalCostContext() + list.get(i).getGoalCostContext());
                statItem.setGoalCostSearch(statItem.getGoalCostSearch() + list.get(i).getGoalCostSearch());
                statItem.setSessionDepthContext(statItem.getSessionDepthContext() + list.get(i).getSessionDepthContext());
                statItem.setSessionDepthSearch(statItem.getSessionDepthSearch() + list.get(i).getSessionDepthSearch());
                statItem.setShowsContext(statItem.getShowsContext() + list.get(i).getShowsContext());
                statItem.setShowsSearch(statItem.getShowsSearch() + list.get(i).getShowsSearch());
                costContextByPeriod += list.get(i).getSumContext();
                costSearchByPeriod += list.get(i).getSumSearch();

            }
            statItem.setCostContext((float) costContextByPeriod/statItem.getClicksContext());
            statItem.setCostSearch((float) costSearchByPeriod/statItem.getClicksSearch());
            statItem.setSumContext((float) costContextByPeriod);
            statItem.setSumSearch((float) costSearchByPeriod);
            getGoalByContext(queryClient);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return statItem;
    }

    private void getGoalByContext(QueryClient queryClient){
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:s:<attribution>DirectPlatformType")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("attribution", "last")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

        for(int j = 0; j < dimensionDatas.size(); j++){
            System.out.println("/////////////////////////////");
            int conversation = 0;
            System.out.println(dimensionDatas.get(j).getDimensions().get(0).getName());
            if(dimensionDatas.get(j).getDimensions().get(0).getName().equals("Контекст")) {
                for (int i = 0; i < dimensionDatas.get(j).getMetrics().get(0).size(); i++) {
                    conversation += dimensionDatas.get(j).getMetrics().get(0).get(i);
                }
                statItem.setGoalContext(conversation);
                conversation = 0;
            }

            if(dimensionDatas.get(j).getDimensions().get(0).getName().equals("Поиск")){
                for (int i = 0; i < dimensionDatas.get(j).getMetrics().get(0).size(); i++) {
                    conversation += dimensionDatas.get(j).getMetrics().get(0).get(i);
                }
                statItem.setGoalSearch(conversation);
            }
        }
    }
}
