package com.analitics.entity.respons.ya.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension {
    private String name;
    private String icon_id;
    private String icon_type;
    private String id;

    public Dimension() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(String icon_id) {
        this.icon_id = icon_id;
    }

    public String getIcon_type() {
        return icon_type;
    }

    public void setIcon_type(String icon_type) {
        this.icon_type = icon_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "name='" + name + '\'' +
                ", icon_id='" + icon_id + '\'' +
                ", icon_type='" + icon_type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
