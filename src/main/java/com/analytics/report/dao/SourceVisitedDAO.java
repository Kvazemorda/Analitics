package com.analytics.report.dao;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.entity.report.SourceVisited;
import com.analytics.report.entity.response.ya.data.metrics.DimensionData;
import com.analytics.report.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class SourceVisitedDAO {

    public ArrayList<SourceVisited> getSourceVisited(QueryClient queryClient){
        ArrayList<SourceVisited> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("metrics", "ym:s:anyGoalConversionRate")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("dimensions", "ym:s:<attribution>TrafficSource")
                .queryParam("attribution", "last")
                .queryParam("top_keys", "30")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoOAuthorIDMetric())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        for(int j = 0; j < dimensionDatas.size(); j++){
            Double metric = 0.0;
            Double conversation = 0.0;
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();
            for(Double conversationNext: metrics.get(0)) {
                conversation += conversationNext;
            }
            for(Double metricNext: metrics.get(1)) {
                metric += metricNext;
            }
            list.add(new SourceVisited(dimensionDatas.get(j).getDimensions().get(0).getName(), metric, conversation));
        }

        return list;
    }


}
