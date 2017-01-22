package com.analytics.report.dao;

import com.analytics.report.Constant;
import com.analytics.report.entity.response.ya.data.TokenGetter;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TokenGetterDAO {
    public void getCode(String login, String id){

        URI url2 = UriComponentsBuilder.fromUriString("https://oauth.yandex.ru/" )
                .path("authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", id)
                .queryParam("device_id", Constant.DEVICE_ID)
                .queryParam("device_name", Constant.DEVICE_NAME)
                .queryParam("login_hint", login)
                .queryParam("force_confirm", "true")
                .queryParam("state", login)
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        TokenGetter tokenGetter = getToken("1877841", Constant.METRIC_ID, Constant.METRIC_PASSWORD);

    }

    public TokenGetter getToken(String code, String clientId, String clientSecret) {
        //JSONObject param = new JSONObject();
        List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("grant_type", "authorization_code"));
        param.add(new BasicNameValuePair("code", code));
        param.add(new BasicNameValuePair("client_id", clientId));
        param.add(new BasicNameValuePair("client_secret", clientSecret));
        param.add(new BasicNameValuePair("device_id", Constant.DEVICE_ID));
        param.add(new BasicNameValuePair("device_name", Constant.DEVICE_NAME));

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("param", param);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        TokenGetter tokenGetter = null;
        try {
            HttpPost request = new HttpPost("https://oauth.yandex.ru/token");
            StringEntity params = new StringEntity(jsonResult.toString());
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setEntity(new UrlEncodedFormEntity(param));

            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            tokenGetter = gson.fromJson(jsonString, TokenGetter.class);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenGetter;
    }
}
