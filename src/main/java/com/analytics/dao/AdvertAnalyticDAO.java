package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class AdvertAnalyticDAO {
    public ArrayList<AdvertAnalyticDAO> getAdvertAnalyticList(QueryClient queryClient){
        ArrayList<AdvertAnalyticDAO> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:ad:directPlatformType")
                .queryParam("metrics", "ym:ad:<currency>AdCost")
                .queryParam("currency", "RUB")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        for(int j = 0; j < dimensionDatas.size(); j++){
            System.out.println(dimensionDatas.get(j).getDimensions().get(0).getId() + " " + dimensionDatas.get(j).getDimensions().get(0).getName() + " " + dimensionDatas.get(j).getMetrics().get(0));
        }

        return list;
    }
}
