package com.analytics;

import com.analytics.client.Client;
import com.analytics.client.QueryClient;
import com.analytics.dao.BannerStatItemDAO;
import com.analytics.hibernate.HibernateSessionFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Client client = new Client();
        Client work = new Client();

        //Доступ к workhacker.su
       /* work.setMetricsID("38437860");
        work.setoOAuthorIDMetric("AQAAAAAZtlXnAAOfZy_UO_ohF09ig1-Ur7eADwk");
        work.setoAuthorIDDirect("AQAAAAAZtlXnAAPC2zPHP_BBvUMfs_1632QL40I");
        work.setLoginDirect("direct.exel");
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.saveClient(work);

       //Aristokrat
        client.setMetricsID("33661869");
        client.setoOAuthorIDMetric("AQAAAAAX4cvvAAOfZ6owq72SMETPiz7NAAIik7Q");
        client.setoAuthorIDDirect("AQAAAAAX4cvvAAPC2-tQJOpLaEY8vk_70YJuZ_U");
        client.setLoginDirect("AristokratDirect");
        client.getCompanyDirect().add(new CompanyDirect("21879001", "Банкротство для кредиторов РСЯ 29.09.16"));

        clientDAO.saveClient(client);*/


        QueryClient queryClient = new QueryClient("2016-11-30", "2016-12-06", work, 70);
        queryClient.setCompanyDirect(new ArrayList<>());


        BannerStatItemDAO bannerStatItemDAO = new BannerStatItemDAO();
        bannerStatItemDAO.getCompanyName(queryClient);
        //CreateExcelReport createExcelTemplate = new CreateExcelReport(queryClient);

        HibernateSessionFactory.getSessionFactory().close();
        HibernateSessionFactory.shutdown();

}

}
