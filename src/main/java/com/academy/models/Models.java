package com.academy.models;

import java.io.Serializable;

public abstract class Models implements Serializable {
    private String name;
    private Integer ID;

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public Integer getID() {
        return ID;
    }
}
