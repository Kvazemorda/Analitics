package com.analytics.dao;

import com.analytics.entity.client.Client;
import com.analytics.service.hibernate.HibernateSessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;

public class ClientDAO {

    public ArrayList<Client> getClientList(){
        String hql = "select client from Client client ";
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery(hql);
        ArrayList<Client> list = (ArrayList<Client>) query.getResultList();

        return list;
    }
}
