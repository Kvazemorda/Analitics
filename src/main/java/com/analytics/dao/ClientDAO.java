package com.analytics.dao;

import com.analytics.client.Client;
import com.analytics.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

public class ClientDAO {
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public void saveClient(Client client){
        session.beginTransaction();
        session.saveOrUpdate(client);
        session.getTransaction().commit();
        session.close();
    }
}
