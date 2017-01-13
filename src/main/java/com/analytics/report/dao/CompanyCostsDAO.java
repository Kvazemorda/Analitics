package com.analytics.report.dao;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.entity.report.CompanyCosts;
import com.analytics.report.entity.response.ya.data.direct.banner.BannersStatItem;
import com.analytics.report.entity.response.ya.data.metrics.DimensionDataTableFormat;
import com.analytics.report.translitiration.Transliterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CompanyCostsDAO {

    public ArrayList<CompanyCosts> getListCosts(QueryClient queryClient){
        TreeSet<CompanyCosts> set = new TreeSet<>();
        BannerStatItemDAO bannersStatItemDAO = new BannerStatItemDAO();
        HashMap<Integer,BannersStatItem> bannersStatItems = bannersStatItemDAO.getBannerSummaryStat(queryClient);
        ArrayList<CompanyCosts> list = new ArrayList<>();
        for(Map.Entry<Integer, BannersStatItem> pair: bannersStatItems.entrySet()){
            BannersStatItem bannersStatItem = pair.getValue();
            CompanyCosts companyCosts = new CompanyCosts();
            companyCosts.setCompany(bannersStatItem.getCompanyName());
            companyCosts.setAd(bannersStatItem.getPhrase());
            companyCosts.setClick(bannersStatItem.getClicks());
            if(companyCosts.getClick() == 0){
                companyCosts.setConversationRate(0);
            }else {
                companyCosts.setConversationRate((companyCosts.getConversation() / companyCosts.getClick()) * 100);
            }
            companyCosts.setCosts(bannersStatItem.getSum());
            companyCosts.setCtr((companyCosts.getClick() /(double) bannersStatItem.getShows()) * 100);
            companyCosts.setPosition((int)bannersStatItem.getShowsAveragePosition());
            list.add(companyCosts);
        }
        ArrayList<DimensionDataTableFormat> dimensionDataTableFormats = bannersStatItemDAO.getConversationOfBanner(queryClient);
        Transliterator transliterator = new Transliterator();
        for (int i = 0; i < dimensionDataTableFormats.size(); i++){
            for(int j =0; j < list.size(); j++){
                String companyName = transliterator.transliterate(list.get(j).getCompany());
                try{
                    if(dimensionDataTableFormats.get(i).getDimensions().get(0).getName().equals(list.get(j).getAd())){
                        list.get(j).setConversation(dimensionDataTableFormats.get(i).getMetrics().get(0));
                        if(list.get(j).getConversation() == 0){
                            list.get(j).setCostOneConversation(0);
                        }else {
                            list.get(j).setCostOneConversation(list.get(j).getCosts() / list.get(j).getConversation());
                        }
                    }
                }catch (NullPointerException exp){
                    exp.printStackTrace();
                }
            }
        }
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
