package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.DeviceClient;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class DeviceClientDAO {

    public ArrayList<DeviceClient> deviceList(QueryClient queryClient){
        ArrayList<DeviceClient> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("metrics", "ym:s:bounceRate")
                .queryParam("dimensions", "ym:s:deviceCategory")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();

        for(int j = 0; j < dimensionDatas.size(); j++){
            Double metric = 0.0;
            Double conversation = 0.0;
            Double bounceRate = 0.0;
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();
            for(Double conversationNext: metrics.get(0)) {
                conversation += conversationNext;
            }
            for(Double metricNext: metrics.get(1)) {
                metric += metricNext;
            }
            for(Double bounce: metrics.get(2)) {
                bounceRate += bounce;
            }
            bounceRate = (bounceRate / metric) * 100;
            list.add(new DeviceClient(dimensionDatas.get(j).getDimensions().get(0).getName(), metric, conversation, bounceRate));
        }

        return list;
    }

    public boolean companyHasMobileAd(){
        return false;
    }
}
