package com.analytics.dao;

import com.analytics.entity.client.Client;

import java.util.ArrayList;

public class ClientDAO {

    public ArrayList<Client> getClientList(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client("login1"));
        list.add(new Client("login2"));
        list.add(new Client("login3"));
        list.add(new Client("login4"));
        return list;
    }
}
