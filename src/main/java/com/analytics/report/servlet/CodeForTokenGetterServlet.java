package com.analytics.report.servlet;

import com.analytics.report.Constant;
import com.analytics.report.entity.response.ya.data.TokenGetter;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet("/analytics-0_1/callback")
public class CodeForTokenGetterServlet extends HttpServlet{
    String jsonString = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(req.getParameter("code"));
        TokenGetter tokenGetter = getToken(req.getParameter("code"), Constant.METRIC_ID, Constant.METRIC_PASSWORD);
        out.println(jsonString);
        out.println(tokenGetter.getAccess_token());
    }

    public TokenGetter getToken(String code, String clientId, String clientSecret) {
        JSONObject param = new JSONObject();
        param.put("Code", code);
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        param.put("device_id", Constant.DEVICE_ID);
        param.put("device_name", Constant.DEVICE_NAME);

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("param", param);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        TokenGetter tokenGetter = null;
        try {
            HttpPost request = new HttpPost("https://oauth.yandex.ru");
            StringEntity params = new StringEntity(jsonResult.toString());

            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            /*Checking response */
            HttpResponse response = httpClient.execute(request);
            jsonString = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            tokenGetter = gson.fromJson(jsonString, TokenGetter.class);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenGetter;
    }
}

