package com.analytics.dao;

import com.analytics.client.QueryClient;
import com.analytics.entity.report.CompanyCosts;

import java.util.ArrayList;
import java.util.TreeSet;

public class CompanyCostsDAO {

    public ArrayList<CompanyCosts> getListCosts(QueryClient queryClient){
        TreeSet<CompanyCosts> set = new TreeSet<>();
        for(int i = 0; i < 40; i++){
            int click =  i * Math.round((float)Math.random())+20;
            int conversation = i * Math.round((float)Math.random());
            int visited = i * Math.round((float)Math.random())+200;
            double expensive = i * Math.random() + 200;
            set.add(new CompanyCosts("company " + i, "ad" + i, click, i, conversation, conversation /(double) click, expensive, click/(double)visited, expensive / conversation ));
        }
        return new ArrayList<>(set);
    }
}
