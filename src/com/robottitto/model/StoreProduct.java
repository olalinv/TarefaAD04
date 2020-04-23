package com.robottitto.model;

import javax.persistence.*;

@Entity
@Table(name = "STORE_PRODUCT")
public class StoreProduct {

    @EmbeddedId
    private StoreProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "ST_PR_QUANTITY")
    private int quantity;

    public StoreProduct() {
    }

    public StoreProduct(Store store, Product product) {
        this.store = store;
        this.product = product;
        this.id = new StoreProductId(store.getId(), product.getId());
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
