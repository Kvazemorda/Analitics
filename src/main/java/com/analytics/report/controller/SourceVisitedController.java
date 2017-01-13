package com.analytics.report.controller;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.dao.SourceVisitedDAO;
import com.analytics.report.entity.report.SourceVisited;

import java.util.ArrayList;

public class SourceVisitedController {

    public SourceVisitedController() {

    }

    public ArrayList<SourceVisited> getVisitedSources(QueryClient queryClient){
        SourceVisitedDAO sourceVisitedDAO = new SourceVisitedDAO();

        return  sourceVisitedDAO.getSourceVisited(queryClient);
    }



}
