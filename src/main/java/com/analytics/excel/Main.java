package com.analytics.excel;

import com.analytics.Constant;
import com.analytics.client.Client;
import com.analytics.client.QueryClient;
import com.analytics.dao.AdvertAnalyticDAO;

import java.util.ArrayList;

public class Main {
    public static Client client;
    public static QueryClient queryClient;

    public static void main(String[] args) {
        client = new Client();
        client.setMetricsID("38437860");
        client.setoAuthorID("AQAAAAAZtlXnAAOfZy_UO_ohF09ig1-Ur7eADwk");
        client.setDirectCompanyID(new ArrayList<>());
        client.getDirectCompanyID().add("22568139");
        client.getDirectCompanyID().add("22568167");
        client.getDirectCompanyID().add("22606160");
        client.getDirectCompanyID().add("22606166");

        client.setoAuthorIDDirect(Constant.QUATH_TOKEN_DIRECT);
        queryClient = new QueryClient("2016-10-28", "2016-11-27", client);

        CreateExcelReport createExcelTemplate = new CreateExcelReport();


        AdvertAnalyticDAO advertAnalyticDAO = new AdvertAnalyticDAO();
        advertAnalyticDAO.getAdvertAnalyticList(queryClient);
    }


}
