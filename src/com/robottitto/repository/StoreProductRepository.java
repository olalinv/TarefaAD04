package com.robottitto.repository;

import com.robottitto.dao.StoreProductDAO;
import com.robottitto.model.StoreProduct;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class StoreProductRepository {

    public static int getStoreProductStock(StoreProduct storeProduct) throws FileNotFoundException, SQLException {
        return StoreProductDAO.getStoreProductQuantity(storeProduct);
    }

    public static void addStoreProduct(StoreProduct storeProduct) {
        StoreProductDAO.addStoreProduct(storeProduct);
    }

    public static void updateStoreProduct(StoreProduct storeProduct) {
        StoreProductDAO.updateStoreProduct(storeProduct);
    }

    public static void removeStoreProduct(StoreProduct storeProduct) {
        StoreProductDAO.deleteStoreProduct(storeProduct);
    }

}
