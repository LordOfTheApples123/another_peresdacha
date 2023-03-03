package com.another_project.entity;

import java.util.List;

public class Client {
    private long id;
    private String name;
    private List<Violation> violations;
    private List<Auto> rentedAutos;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        this.id = -1;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public List<Auto> getRentedAutos() {
        return rentedAutos;
    }

    public void setRentedAutos(List<Auto> rentedAutos) {
        this.rentedAutos = rentedAutos;
    }

    @Override
    public String toString() {
        return "client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
