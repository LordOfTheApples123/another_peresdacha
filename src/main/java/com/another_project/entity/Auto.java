package com.another_project.entity;

public class Auto extends AbstractEntity{
    private long id;
    private String model;
    private String number;

    public Auto(long id, String model, String number) {
        this.id = id;
        this.model = model;
        this.number = number;
    }

    public Auto(String model, String number) {
        this.id = -1;
        this.model = model;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
