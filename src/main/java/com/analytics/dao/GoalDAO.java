package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Goal;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class GoalDAO {

    public ArrayList<Goal> getGoalList(QueryClient queryClient){
        ArrayList<Goal> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("dimensions", "ym:s:goal")
                .queryParam("top_keys", "30")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

            for(int j = 0; j < dimensionDatas.size(); j++){
                double conversation = 0.0;
                ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();
                for(int i = 0; i < metrics.get(0).size(); i++){
                    conversation += metrics.get(0).get(i);
            }
                list.add(new Goal(dimensionDatas.get(j).getDimensions().get(0).getName(), conversation));
        }
        return list;
    }
}
