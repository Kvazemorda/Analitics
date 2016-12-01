package com.analitics.controller;

import com.analitics.entity.respons.ya.data.SourceVisitedFromYa;
import com.analitics.report.PDFCreator;
import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class SourceVisitedController {
    String url = "https://api-metrika.yandex.ru/stat/v1/data/bytime?date1=2016-10-28&date2=2016-11-27&group=month&dimensions=ym:s:<attribution>TrafficSource&attribution=last&ids=38437860&metrics=ym:s:visits&oauth_token=AQAAAAASjEe8AAOfZ4L88v3m-U9PvgBL9J0AI-g";

    @RequestMapping("/source")
    public String getVisitedSources(){
        RestTemplate restTemplate = new RestTemplate();
        SourceVisitedFromYa sourceVisitedFromYa = restTemplate.getForObject(url, SourceVisitedFromYa.class);
        try {
            PDFCreator pdfCreator = new PDFCreator(sourceVisitedFromYa);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return sourceVisitedFromYa.toString();
    }

}
