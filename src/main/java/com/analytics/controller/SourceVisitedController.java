package com.analytics.controller;

import com.analytics.client.QueryClient;
import com.analytics.dao.SourceVisitedDAO;
import com.analytics.entity.report.SourceVisited;

import java.util.ArrayList;

public class SourceVisitedController {

    public SourceVisitedController() {

    }

    public ArrayList<SourceVisited> getVisitedSources(QueryClient queryClient){
        SourceVisitedDAO sourceVisitedDAO = new SourceVisitedDAO();

        return  sourceVisitedDAO.getSourceVisited(queryClient);
    }



}
