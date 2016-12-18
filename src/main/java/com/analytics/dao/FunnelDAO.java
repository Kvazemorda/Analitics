package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Funnel;

public class FunnelDAO {

    public Funnel getFunnel(QueryClient queryClient){
        Funnel funnel = new Funnel();
        funnel.setQualityShowAdvert(1351);
        funnel.setQualityClick(351);
        funnel.setSpendAll(6589.15);
        funnel.setQualityGetGoals(15);
        funnel.setEffecientConversation((double) (funnel.getQualityGetGoals()/(double)funnel.getQualityClick()));

        funnel.setCpaCost(funnel.getSpendAll()/funnel.getQualityGetGoals());

        return funnel;
    }
}
