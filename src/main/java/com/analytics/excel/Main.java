package com.analytics.excel;

import com.analytics.client.Client;
import com.analytics.client.QueryClient;
import com.analytics.dao.BannerStatItemDAO;

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

       //Aristokrat
        client.setMetricsID("33661869");
        client.setoAuthorID("AQAAAAAX4cvvAAOfZ6owq72SMETPiz7NAAIik7Q");
        client.setDirectCompanyID(new ArrayList<>());
        client.setDirectCompanyName(new ArrayList<>());
        /*
        client.getDirectCompanyID().add("20255884");
        client.getDirectCompanyID().add("21265071");
        client.getDirectCompanyID().add("21265077");
        client.getDirectCompanyID().add("21808774");
        client.getDirectCompanyID().add("21878625");
        client.getDirectCompanyID().add("21879001");
        client.getDirectCompanyID().add("21984478");
        client.getDirectCompanyID().add("22015078");
        client.getDirectCompanyID().add("22018753");
        client.getDirectCompanyID().add("22037246");
        client.getDirectCompanyID().add("22325876");
        client.getDirectCompanyID().add("23139450");*/
        client.setoAuthorIDDirect("AQAAAAAX4cvvAAPC2-tQJOpLaEY8vk_70YJuZ_U");
        client.setLoginDirect("AristokratDirect");
        QueryClient queryClient = new QueryClient("2016-11-30", "2016-11-30", client, 250);


        BannerStatItemDAO bannerStatItemDAO = new BannerStatItemDAO();
        bannerStatItemDAO.getCompanyName(queryClient);
        bannerStatItemDAO.getConversationOfBanner(queryClient);
        //CreateExcelReport createExcelTemplate = new CreateExcelReport(queryClient);

}

}
