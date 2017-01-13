package com.analytics.report.dao;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.entity.report.SourceDetail;
import com.analytics.report.entity.response.ya.data.metrics.Dimension;
import com.analytics.report.entity.response.ya.data.metrics.DimensionData;
import com.analytics.report.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class SourceDetailDAO {

    public ArrayList<SourceDetail> getSourceDetail(QueryClient queryClient){

        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/" )
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("accuracy", "full")
                .queryParam("group", "month")
                .queryParam("dimensions", "ym:s:<attribution>SourceEngine")
                .queryParam("attribution", "last")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("top_keys", "30")
                .queryParam("oauth_token", queryClient.getClient().getoOAuthorIDMetric())
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<SourceDetail> sourceDetails = new ArrayList<>();
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        for(int i = 0; i < dimensionDatas.size(); i++){
           ArrayList<Dimension> dimensions = dimensionDatas.get(i).getDimensions();
            SourceDetail sourceDetail = new SourceDetail();
            sourceDetail.setSourceDetail(dimensions.get(0).getName());
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(i).getMetrics();
            double conversationRate = 0.0;
            int visits = 0;
            for (int j = 0; j < metrics.get(0).size(); j++){
                conversationRate += metrics.get(0).get(j);
                visits += metrics.get(1).get(j);
            }
            sourceDetail.setConversationDetail(conversationRate);
            sourceDetail.setCountVisited(visits);
            sourceDetails.add(sourceDetail);
        }

        return sourceDetails;
    }
}
