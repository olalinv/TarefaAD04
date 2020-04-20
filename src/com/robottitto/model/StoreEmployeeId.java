package com.robottitto.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoreEmployeeId implements Serializable {

    @Column(name = "STORE_ID")
    private int storeId;

    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    private StoreEmployeeId() {
    }

    public StoreEmployeeId(int storeId, int employeeId) {
        this.storeId = storeId;
        this.employeeId = employeeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}
