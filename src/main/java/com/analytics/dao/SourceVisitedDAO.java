package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.SourceVisited;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class SourceVisitedDAO {
    //String url = "https://api-metrika.yandex.ru/stat/v1/data/bytime?date1=2016-10-28&date2=2016-11-27&group=month&dimensions=ym:s:<attribution>TrafficSource&attribution=last&ids=38437860&metrics=ym:s:visits&oauth_token=AQAAAAASjEe8AAOfZ4L88v3m-U9PvgBL9J0AI-g";
    String url = "https://api-metrika.yandex.ru/stat/v1/data/" +
            "bytime?" +
            "date1=2016-10-28&date2=2016-11-27&group=year&metrics=ym:s:anyGoalConversionRate&dimensions=ym:s:<attribution>TrafficSource&attribution=last&ids=38437860&metrics=ym:s:visits&oauth_token=AQAAAAASjEe8AAOfZ4L88v3m-U9PvgBL9J0AI-g";


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
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);


        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        System.out.println(dimensionDatas.size());
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
