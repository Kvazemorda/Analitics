package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.DynamicConversation;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class DynamicConversationDAO {

    public ArrayList<DynamicConversation> getList(QueryClient queryClient){
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "day")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:s:date")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("top_keys", "30")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoOAuthorIDMetric())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

        ArrayList<DynamicConversation> dynamicsList = new ArrayList<>();
        TreeSet<DynamicConversation> set = new TreeSet<>();
        for(int i = 0; i < dimensionDatas.size(); i++){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            int visited = 0;
            int conversation = 0;
            for(int j = 0; j < dimensionDatas.get(i).getMetrics().get(1).size(); j++){
                    visited += dimensionDatas.get(i).getMetrics().get(1).get(j);
            }
            for(int j = 0; j < dimensionDatas.get(i).getMetrics().get(0).size(); j++){
                conversation += dimensionDatas.get(i).getMetrics().get(0).get(j);
            }
            for (int j = 0; j < dimensionDatas.get(i).getDimensions().size(); j++){
                try {
                    date = simpleDateFormat.parse(dimensionDatas.get(i).getDimensions().get(j).getName());
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
            }
            set.add(new DynamicConversation(visited, conversation, date, visited/10));
        }
        dynamicsList.addAll(set);
        return dynamicsList;
    }
}
