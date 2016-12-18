package com.analytics.controller;

import com.analytics.client.Client;
import com.analytics.dao.SourceVisitedDAO;
import com.analytics.entity.report.SourceVisited;
import com.analytics.excel.Main;

import java.util.ArrayList;

public class SourceVisitedController {
    private Client client;

    public SourceVisitedController(Client client) {
        this.client = client;
    }

    public ArrayList<SourceVisited> getVisitedSources(){
        SourceVisitedDAO sourceVisitedDAO = new SourceVisitedDAO();

        return  sourceVisitedDAO.getSourceVisited(Main.queryClient);
    }



}
