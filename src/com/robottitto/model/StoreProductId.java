package com.robottitto.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoreProductId implements Serializable {

    @Column(name = "STORE_ID")
    private int storeId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    private StoreProductId() {
    }

    public StoreProductId(int storeId, int productId) {
        this.setStoreId(storeId);
        this.setProductId(productId);
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
