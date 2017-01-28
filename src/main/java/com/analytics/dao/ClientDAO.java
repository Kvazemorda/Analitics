package com.analytics.dao;

import com.analytics.entity.client.Client;
import com.analytics.service.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;

public class ClientDAO {

    public ArrayList<Client> getClientList(){
        String hql = "select client from Client client ";
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery(hql);
        ArrayList<Client> list = (ArrayList<Client>) query.getResultList();

        return list;
    }

    private Client getClient(String login){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String hql = "select distinct client from Client client " +
                "where client.loginDirect = :login";

        Query query = session.createQuery(hql);
        query.setParameter("login", login);
        return (Client) query.getSingleResult();
    }

    public void saveOrUpdate(String login, String metricID, String token){
        Client clientFromDB = getClient(login);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        if(clientFromDB == null){
            Client client = new Client(metricID, token, login);
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
            session.close();
        }else {
            clientFromDB.setMetricsID(metricID);
            clientFromDB.setMetricsID(token);
            session.beginTransaction();
            session.merge(clientFromDB);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void saveOrUpdate(String login, String token) {
        Client clientFromDB = getClient(login);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        if (clientFromDB == null) {
            Client client = new Client(token, login);
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
            session.close();
        } else {
            clientFromDB.setLoginDirect(token);
            session.beginTransaction();
            session.merge(clientFromDB);
            session.getTransaction().commit();
            session.close();
        }
    }
}
