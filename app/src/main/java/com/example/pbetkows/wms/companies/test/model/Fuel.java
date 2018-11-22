package com.example.pbetkows.wms.companies.test.model;

public class Fuel {

    private int id;

    public Fuel() {
    }

    public Fuel(int id, String typeFuel) {
        this.id = id;
        this.typeFuel = typeFuel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(String typeFuel) {
        this.typeFuel = typeFuel;
    }

    private String typeFuel;
}
