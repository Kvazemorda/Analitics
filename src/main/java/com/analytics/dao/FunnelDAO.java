package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Funnel;
import com.analytics.entity.response.ya.data.direct.StatItem;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class FunnelDAO {

    public Funnel getFunnel(QueryClient queryClient){
        StatItem statItem = new SearchOrContextDAO().getSummaryStat(queryClient);
        ArrayList<AdvertAnalyticDAO> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "year")
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
        double spend = 0.0;
        for(int j = 0; j < dimensionDatas.size(); j++){
            for(int i = 0; i < dimensionDatas.get(j).getMetrics().get(0).size(); i++){
                spend += dimensionDatas.get(j).getMetrics().get(0).get(i);
            }
        }

        Funnel funnel = new Funnel();
        funnel.setQualityShowAdvert(statItem.getShowsContext() + statItem.getShowsSearch());
        funnel.setQualityClick(statItem.getClicksContext() + statItem.getClicksSearch());
        funnel.setSpendAll(spend);
        funnel.setQualityGetGoals(getGoalByContext(queryClient));
        funnel.setEffecientConversation((funnel.getQualityGetGoals()/(double)funnel.getQualityClick()));
        funnel.setCpaCost(funnel.getSpendAll()/funnel.getQualityGetGoals());

        return funnel;
    }

    private int getGoalByContext(QueryClient queryClient){
        StatItem statItem = new SearchOrContextDAO().getSummaryStat(queryClient);
        ArrayList<AdvertAnalyticDAO> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("direct_client_logins", queryClient.getClient().getLoginDirect())
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("group", "month")
                .queryParam("accuracy", "full")
                .queryParam("dimensions", "ym:s:<attribution>DirectClickOrder")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("attribution", "last")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        int conversation = 0;
        String name = "";
        for(int j = 0; j < dimensionDatas.size(); j++){
            for(int i = 0; i < dimensionDatas.get(j).getMetrics().get(0).size(); i++){
                conversation += dimensionDatas.get(j).getMetrics().get(0).get(i);
            }
        }

        return conversation;
    }
}
