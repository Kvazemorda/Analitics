package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Region;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class RegionDAO {

    public ArrayList<Region> regionList(QueryClient queryClient){
        ArrayList<Region> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("metrics", "ym:s:anyGoalConversionRate")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("dimensions", "ym:s:regionDistrict")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("top_keys", "30")
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
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
            list.add(new Region(dimensionDatas.get(j).getDimensions().get(0).getName(), metric, conversation));
        }

        return list;
    }
}
