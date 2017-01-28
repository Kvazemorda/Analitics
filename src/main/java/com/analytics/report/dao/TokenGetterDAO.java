package com.analytics.report.dao;

import com.analytics.report.Constant;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class TokenGetterDAO {
    public String getCode(String login, String id, String direct){

        URI url2 = UriComponentsBuilder.fromUriString("https://oauth.yandex.ru/" )
                .path("authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", id)
                .queryParam("device_id", Constant.DEVICE_ID)
                .queryParam("device_name", Constant.DEVICE_NAME)
                .queryParam("login_hint", login)
                .queryParam("force_confirm", "true")
                .queryParam("state", login + ":type=" + direct)
                .build()
                .toUri();

        return url2.toString();
    }

    public String getCode(String login, String id, String metric, String idCountOfMetric){

        URI url2 = UriComponentsBuilder.fromUriString("https://oauth.yandex.ru/" )
                .path("authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", id)
                .queryParam("device_id", Constant.DEVICE_ID)
                .queryParam("device_name", Constant.DEVICE_NAME)
                .queryParam("login_hint", login)
                .queryParam("force_confirm", "true")
                .queryParam("state", login + ":type=" + metric + ":idCount=" + idCountOfMetric)
                .build()
                .toUri();

        return url2.toString();
    }
}
