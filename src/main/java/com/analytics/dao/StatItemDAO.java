package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.response.ya.data.direct.AdvertCost;
import com.analytics.entity.response.ya.data.direct.Data;
import com.analytics.entity.response.ya.data.direct.StatItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class StatItemDAO {

    public StatItem getSummaryStat(QueryClient queryClient) {
        JSONArray campaingIDS = new JSONArray();
        for (String campanyID : queryClient.getClient().getDirectCompanyID()) {
            campaingIDS.add(campanyID);
        }
        JSONObject param = new JSONObject();
        param.put("CampaignIDS", campaingIDS);
        param.put("StartDate", queryClient.getDate1());
        param.put("EndDate", queryClient.getDate2());
       // param.put("Currency", "RUB");
        //param.put("IncludeVAT", "Yes");
        //param.put("IncludeDiscount", "No");

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("method", "GetSummaryStat");
        jsonResult.put("param", param);
        jsonResult.put("token", queryClient.getClient().getoAuthorIDDirect());

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StatItem statItem = null;


        try {
            HttpPost request = new HttpPost("https://api.direct.yandex.ru/v4/json/");
            StringEntity params = new StringEntity(jsonResult.toString());

            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            /*Checking response */

            String queryString = request.toString();

            HttpResponse response = httpClient.execute(request);

            String jsonString = EntityUtils.toString(response.getEntity());
            System.out.println("///////////////////////////////////");
            System.out.println(jsonString);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            ObjectMapper objectMapper = new ObjectMapper();

            Gson gson = new Gson();
            Data data = gson.fromJson(jsonString, Data.class);
            System.out.println(new Date());
            ArrayList<StatItem> list = data.getData();
            statItem = new StatItem();
            AdvertCost advertCost = new AdvertCost();
            double costSearchByPeriod = 0.0;
            int countDayContext = 0;
            int countDaySearch = 0;
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
                costContextByPeriod += list.get(i).getSumContext() * list.get(i).getClicksContext();
                costSearchByPeriod += list.get(i).getSumSearch() * list.get(i).getClicksSearch();

                if(list.get(i).getSumSearch() != 0.0){
                    countDaySearch++;
                }
                if(list.get(i).getSumContext() != 0.0){
                    countDayContext++;
                }
            }
            statItem.setCostContext((float)costContextByPeriod);
            statItem.setCostSearch((float) costSearchByPeriod);
            statItem.setSumContext((float) costContextByPeriod/countDayContext);
            statItem.setSumSearch((float) costSearchByPeriod/countDaySearch);
            advertCost.setAdvertClick(statItem.getClicksContext() + statItem.getClicksSearch());
            advertCost.setAdvertCost(costContextByPeriod + costSearchByPeriod);
            advertCost.setAdvertClickOneCost(advertCost.getAdvertCost() / advertCost.getAdvertClick());

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
}
