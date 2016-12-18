package com.analytics.dao;

import com.analytics.entity.report.AdvertCost;

public class AdvertCostDAO {

    public AdvertCost getAdvertCost(){
        AdvertCost advertCost = new AdvertCost(25360.00, 354);
        return advertCost;
    }
}
