package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.response.ya.data.direct.banner.BannersStatItem;
import com.analytics.entity.response.ya.data.direct.banner.CompanyDirect;
import com.analytics.entity.response.ya.data.direct.banner.Data;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.DimensionDataTableFormat;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaTable;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BannerStatItemDAO {
    private HashMap<Integer, BannersStatItem> mapBannerStatItem = new HashMap<>();

    public HashMap<Integer, BannersStatItem> getBannerSummaryStat(QueryClient queryClient) {
        ArrayList<String> listDate = getSevenDays(queryClient.getDate1(), queryClient.getDate2());
        Data data = null;
        for (int iCompany = 0; iCompany < queryClient.getCompanyDirect().size(); iCompany++){

            for(int dateRange = 0; dateRange < listDate.size(); dateRange++) {
                JSONArray groupByColumns = new JSONArray();
                groupByColumns.add("clDate");
                groupByColumns.add("clPhrase");
                groupByColumns.add("clDeviceType");
                groupByColumns.add("clAveragePosition");
                JSONObject param = new JSONObject();
                param.put("CampaignID", queryClient.getCompanyDirect().get(iCompany).getCompanyID());
                param.put("StartDate", listDate.get(dateRange));
                if(dateRange + 1 == listDate.size()){
                    break;
                }else {
                    param.put("EndDate", listDate.get(++dateRange));
                }

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
                    for (int statItem = 0; statItem < data.getData().getStat().size(); statItem++) {
                        data.getData().getStat().get(statItem).setCompanyName(queryClient.getCompanyDirect().get(iCompany).getCompanyName());
                        data.getData().getStat().get(statItem).setPhrase(clearingPhrase(data.getData().getStat().get(statItem).getPhrase()));
                    }
                    sumBannersStatItems(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mapBannerStatItem;
    }

    private void sumBannersStatItems(Data data){

        ArrayList<BannersStatItem> listFromYa = data.getData().getStat();
        for(int iBannerFromYa = 0; iBannerFromYa < listFromYa.size(); iBannerFromYa++) {
            int hashCode = listFromYa.get(iBannerFromYa).hashCode();
            if (mapBannerStatItem.containsKey(hashCode)) {
                BannersStatItem bannersStatItem = mapBannerStatItem.get(hashCode);
                bannersStatItem.setClicks(mapBannerStatItem.get(hashCode).getClicks() + listFromYa.get(iBannerFromYa).getClicks());
                bannersStatItem.setShows(mapBannerStatItem.get(hashCode).getShows() + listFromYa.get(iBannerFromYa).getShows());
                bannersStatItem.setSum(mapBannerStatItem.get(hashCode).getSum() + listFromYa.get(iBannerFromYa).getSum());
                mapBannerStatItem.remove(hashCode);
                mapBannerStatItem.put(hashCode, bannersStatItem);
            } else {
                mapBannerStatItem.put(listFromYa.get(iBannerFromYa).hashCode(), listFromYa.get(iBannerFromYa));
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
                .queryParam("oauth_token", queryClient.getClient().getoOAuthorIDMetric())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

        for(int j = 0; j < dimensionDatas.size(); j++){
            String splitId = dimensionDatas.get(j).getDimensions().get(0).getDirect_id().split("N-")[1];
            String companyName = dimensionDatas.get(j).getDimensions().get(0).getName();
            queryClient.getCompanyDirect().add(new CompanyDirect(splitId, companyName));
        }
    }

    public ArrayList<DimensionDataTableFormat> getConversationOfBanner(QueryClient queryClient){
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/")
                .path("stat/v1/data")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:s:UTMTerm")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("currency", "RUB")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoOAuthorIDMetric())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaTable sourceVisitedFromYaTable = restTemplate.getForObject(url2, SourceVisitedFromYaTable.class);
        ArrayList<DimensionDataTableFormat> dimensionDatas = sourceVisitedFromYaTable.getData();
        return dimensionDatas;
    }

    private String clearingPhrase(String phrase){
        String newPhrase = phrase;
        if(newPhrase.contains(" -")){
            newPhrase = newPhrase.substring(0, newPhrase.indexOf(" -"));
        }
        if(newPhrase.contains("!")){
            newPhrase = newPhrase.substring(newPhrase.indexOf("!") + 1);
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
                long sevenDays = 1000*60*60*24*6;
                long oneDay = 1000*60*60*24*1;
                while((dateStart.getTime() + sevenDays) < dateEnd.getTime()){
                    dateStart.setTime(dateStart.getTime() + sevenDays);
                    list.add(sdf.format(dateStart));
                    dateStart.setTime(dateStart.getTime() + oneDay);
                    list.add(sdf.format(dateStart));
                }
                dateStart.setTime(dateStart.getTime() + (dateEnd.getTime() - dateStart.getTime()));
                list.add(sdf.format(dateStart));
            }
            list.forEach(System.out::println);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}