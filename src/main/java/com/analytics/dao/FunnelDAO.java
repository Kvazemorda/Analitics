package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.Funnel;
import com.analytics.entity.response.ya.data.direct.StatItem;

public class FunnelDAO {

    public Funnel getFunnel(QueryClient queryClient){
        StatItem statItem = new SearchOrContextDAO().getSummaryStat(queryClient);
        Funnel funnel = new Funnel();
        funnel.setQualityShowAdvert(statItem.getShowsContext() + statItem.getShowsSearch());
        funnel.setQualityClick(statItem.getClicksContext() + statItem.getClicksSearch());
        funnel.setSpendAll((double) (statItem.getSumContext() + statItem.getSumSearch()));
        funnel.setQualityGetGoals(statItem.getGoalContext() + statItem.getGoalSearch());
        funnel.setEffecientConversation((funnel.getQualityGetGoals()/(double)funnel.getQualityClick()));
        funnel.setCpaCost(funnel.getSpendAll()/funnel.getQualityGetGoals());

        return funnel;
    }
}
