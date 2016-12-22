package com.analytics.excel;

import com.analytics.client.Client;
import com.analytics.client.QueryClient;
import com.analytics.dao.AdvertAnalyticDAO;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        //Доступ к workhacker.su
       /* client.setMetricsID("38437860");
        client.setoAuthorID("AQAAAAAZtlXnAAOfZy_UO_ohF09ig1-Ur7eADwk");
        client.setDirectCompanyID(new ArrayList<>());
        client.getDirectCompanyID().add("22568139");
        client.getDirectCompanyID().add("22568167");
        client.getDirectCompanyID().add("22606160");
        client.getDirectCompanyID().add("22606166");
        client.setoAuthorIDDirect("AQAAAAAZtlXnAAPC2zPHP_BBvUMfs_1632QL40I");*/

        client.setMetricsID("38437860");
        client.setoAuthorID("AQAAAAAZtlXnAAOfZy_UO_ohF09ig1-Ur7eADwk");
        client.setDirectCompanyID(new ArrayList<>());
        client.getDirectCompanyID().add("22568139");
        client.getDirectCompanyID().add("22568167");
        client.getDirectCompanyID().add("22606160");
        client.getDirectCompanyID().add("22606166");
        client.setoAuthorIDDirect("AQAAAAAZtlXnAAPC2zPHP_BBvUMfs_1632QL40I");

        QueryClient queryClient = new QueryClient("2016-10-28", "2016-11-27", client, 250);

        CreateExcelReport createExcelTemplate = new CreateExcelReport(queryClient);
        AdvertAnalyticDAO advertAnalyticDAO = new AdvertAnalyticDAO();
        advertAnalyticDAO.getAdvertAnalyticList(queryClient);
    }


}
