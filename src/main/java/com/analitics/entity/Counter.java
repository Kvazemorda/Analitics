package com.analitics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Counter {
    private int id;
    private String status;
    private String owner_login;
    private String code_status;
    private CodeStatusInfo code_status_info;
    private String name;
    private String site;
    private String type;
    private boolean favorite;
    private String permission;
    private String[] mirrors;
    private String[] goals;
    private Filters filters;

    public Counter() {
    }
}
