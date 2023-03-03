package com.another_project.entity;

public class Violation {

    private long id;
    private long client_id;
    private long auto_id;
    private String descr;

    public Violation(long id, long client_id, long auto_id, String descr) {
        this.id = id;
        this.client_id = client_id;
        this.auto_id = auto_id;
        this.descr = descr;
    }

    public Violation(long client_id, long auto_id, String descr) {
        this.client_id = client_id;
        this.auto_id = auto_id;
        this.descr = descr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(long auto_id) {
        this.auto_id = auto_id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
