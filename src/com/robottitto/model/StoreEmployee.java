package com.robottitto.model;

import javax.persistence.*;

@Entity
@Table(name = "STORE_EMPLOYEE")
public class StoreEmployee {

    @EmbeddedId
    private StoreEmployeeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("STORE_ID")
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("EMPLOYEE_ID")
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "ST_EM_HOURS")
    private int hours;

    public StoreEmployee() {
    }

    public StoreEmployee(Store store, Employee employee) {
        this.store = store;
        this.employee = employee;
        this.id = new StoreEmployeeId(store.getId(), employee.getId());
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
