package com.analitics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filters {
    private int id;
    private String attr;
    private String type;
    private String value;
    private String action;
    private String status;
    private String start_ip;
    private String end_ip;
    private boolean with_subdomains;

    public Filters() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_ip() {
        return start_ip;
    }

    public void setStart_ip(String start_ip) {
        this.start_ip = start_ip;
    }

    public String getEnd_ip() {
        return end_ip;
    }

    public void setEnd_ip(String end_ip) {
        this.end_ip = end_ip;
    }

    public boolean isWith_subdomains() {
        return with_subdomains;
    }

    public void setWith_subdomains(boolean with_subdomains) {
        this.with_subdomains = with_subdomains;
    }

    @Override
    public String toString() {
        return "Filters{" +
                "id=" + id +
                ", attr='" + attr + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                ", start_ip='" + start_ip + '\'' +
                ", end_ip='" + end_ip + '\'' +
                ", with_subdomains=" + with_subdomains +
                '}';
    }
}
