package com.robottitto.model;

import java.util.ArrayList;
import java.util.List;

public class ReportStore {

    private int id;
    private String name;
    private List<ReportProduct> products;

    public ReportStore() {
        this.products = new ArrayList<ReportProduct>();
    }

    public ReportStore(int id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<ReportProduct>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ReportProduct> products) {
        this.products = products;
    }

}
