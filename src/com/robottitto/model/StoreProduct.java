package com.robottitto.model;

import javax.persistence.*;

@Entity
@Table(name = "STORE_PRODUCT")
public class StoreProduct {

    @EmbeddedId
    private StoreProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("STORE_ID")
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("PRODUCT_ID")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "ST_PR_QUANTITY")
    private Double quantity;

    public StoreProduct() {
    }

    public StoreProductId getId() {
        return id;
    }

    public void setId(StoreProductId id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

}
