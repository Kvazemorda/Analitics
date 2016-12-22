package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Week;
import com.analytics.entity.response.ya.data.metrics.DimensionData;
import com.analytics.entity.response.ya.data.metrics.SourceVisitedFromYaByTime;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class WeekDAO {

    public ArrayList<Week> getWeekList(QueryClient queryClient){
        ArrayList<Week> list = new ArrayList<>();
        URI url2 = UriComponentsBuilder.fromUriString("https://api-metrika.yandex.ru/stat/v1/data/")
                .path("bytime")
                .queryParam("date1", queryClient.getDate1())
                .queryParam("date2", queryClient.getDate2())
                .queryParam("accuracy", "full")
                .queryParam("metrics", "ym:s:sumGoalReachesAny")
                .queryParam("metrics", "ym:s:visits")
                .queryParam("dimensions", "ym:s:dayOfWeek")
                .queryParam("ids", queryClient.getClient().getMetricsID())
                .queryParam("oauth_token", queryClient.getClient().getoAuthorID())
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYaByTime sourceVisitedFromYaByTime = restTemplate.getForObject(url2, SourceVisitedFromYaByTime.class);
        ArrayList<DimensionData> dimensionDatas = sourceVisitedFromYaByTime.getData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleFormatDayOfWeek = new SimpleDateFormat("EEEE");

        String day = "";
        Date date = null;
        String dayOfWeek = "";
        for(int j = 0; j < dimensionDatas.size(); j++){
            double metric = 0.0;
            double conversation = 0.0;
            ArrayList<ArrayList<Double>> metrics = dimensionDatas.get(j).getMetrics();
            for(Double conversationNext: metrics.get(0)) {
                conversation += conversationNext;
            }
            for(Double metricNext: metrics.get(1)) {
                metric += metricNext;
            }
            TreeSet<Week> set = new TreeSet<>();
            set.add(new Week(dimensionDatas.get(j).getDimensions().get(0).getName(), metric, conversation));
            list.addAll(set);
            System.out.println(dimensionDatas.get(j).getDimensions().get(0).getName() + " " + metric + " " + conversation);
        }

        return list;
    }
}
