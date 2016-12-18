package com.analytics.client;

public class QueryClient {

    private String date1;
    private String date2;
    private Client client;

    public QueryClient(String date1, String date2, Client client) {
        this.date1 = date1;
        this.date2 = date2;
        this.client = client;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
