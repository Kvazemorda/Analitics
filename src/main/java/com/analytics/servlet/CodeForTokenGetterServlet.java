package com.analytics.servlet;

import com.analytics.dao.ClientDAO;
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/analytics-0_1/callback")
public class CodeForTokenGetterServlet extends HttpServlet{
    String jsonString = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String state = req.getParameter("state");
        String login = state.substring(0, state.indexOf(":"));
        String type = state.substring(state.indexOf(":type=") + 6, state.indexOf(":idCount"));
        TokenGetter tokenGetter = null;
        ClientDAO clientDAO = new ClientDAO();
        switch (type){
            case Constant.TypeMetric:
                String metricID = state.substring(state.indexOf(":idCount=") + 9);
                tokenGetter = getToken(req.getParameter("code"), Constant.METRIC_ID, Constant.METRIC_PASSWORD);
                //clientDAO.saveOrUpdate(login, metricID, tokenGetter.getAccess_token());
                out.println(login + " " + metricID + " " + tokenGetter.getAccess_token());
                break;

            case Constant.TypeDirect:
               /* tokenGetter = getToken(req.getParameter("code"), Constant.METRIC_ID, Constant.METRIC_PASSWORD);
                clientDAO.saveOrUpdate(login, tokenGetter.getAccess_token());
                out.println(login + " " + " " + tokenGetter.getAccess_token());*/
                break;
        }
        out.println("Saved");
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

