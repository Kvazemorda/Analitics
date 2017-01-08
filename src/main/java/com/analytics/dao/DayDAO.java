package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Day;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class DayDAO {
    public ArrayList<Day> getDayList(QueryClient queryClient){
        ArrayList<Day> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("accuracy", "full")
                .queryParam("group", "year")
                .queryParam("dimensions", "ym:s:hour")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("filters=ym:s:trafficSource=", "'ad'")
                .queryParam("top_keys", "24")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        for(int j = 0; j < dimensionDatas.size(); j++){
            int metric = 0;
            int conversation = 0;
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();

            for(Double conversationNext: metrics.get(0)) {
                conversation += conversationNext;
            }
            for(Double metricNext: metrics.get(1)) {
                metric += metricNext;
            }
            list.add(new Day(dimensionDatas.get(j).getDimensions().get(0).getName(),metric, conversation));
        }

        return list;
    }
}
