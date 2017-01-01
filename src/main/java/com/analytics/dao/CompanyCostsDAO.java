package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.CompanyCosts;
import com.analytics.entity.response.ya.data.direct.banner.BannersStatItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CompanyCostsDAO {

    public ArrayList<CompanyCosts> getListCosts(QueryClient queryClient){
        TreeSet<CompanyCosts> set = new TreeSet<>();
        BannerStatItemDAO bannersStatItemDAO = new BannerStatItemDAO();
        HashMap<String,BannersStatItem> bannersStatItems = bannersStatItemDAO.getBannerSummaryStat(queryClient);
        for(Map.Entry<String, BannersStatItem> pair: bannersStatItems.entrySet()){
            String key = pair.getKey();
            CompanyCosts companyCosts = new CompanyCosts();
            companyCosts.setCompany(bannersStatItems.get(key).getCompanyName());
            companyCosts.setAd(bannersStatItems.get(key).getPhrase());
            companyCosts.setClick(bannersStatItems.get(key).getClicks());
        //    companyCosts.setConversation(bannersStatItemDAO
          //          .getConversationOfBanner(queryClient, bannersStatItems.get(key).getPhrase(), companyCosts.getCompany()));
            if(companyCosts.getClick() == 0){
                companyCosts.setConversationRate(0);
            }else {
                companyCosts.setConversationRate((companyCosts.getConversation() / companyCosts.getClick()) * 100);
            }
            companyCosts.setCosts(bannersStatItems.get(key).getSum());
            if(companyCosts.getConversation() == 0){
                companyCosts.setCostOneConversation(0);
            }else {
                companyCosts.setCostOneConversation(companyCosts.getCosts() / companyCosts.getConversation());
            }
            companyCosts.setCtr((companyCosts.getClick() / bannersStatItems.get(key).getShows()) * 100);
            companyCosts.setPosition((int)bannersStatItems.get(key).getShowsAveragePosition());
            set.add(companyCosts);
        }
        return new ArrayList<>(set);
    }
}
