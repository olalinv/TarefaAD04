package com.robottitto.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROVINCE")
public class Province implements Serializable {

    @Id
    @Column(name = "PROVINCE_ID")
    private int id;

    @Column(name = "NAME")
    @SerializedName("nome")
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Store> stores;

    public Province() {
    }

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
        this.stores = new HashSet<Store>();
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
}
