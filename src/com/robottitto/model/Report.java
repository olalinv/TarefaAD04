package com.robottitto.model;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private List<ReportStore> stores;

    public Report() {
        this.setStores(new ArrayList<ReportStore>());
    }

    public List<ReportStore> getStores() {
        return stores;
    }

    public void setStores(List<ReportStore> stores) {
        this.stores = stores;
    }

}
