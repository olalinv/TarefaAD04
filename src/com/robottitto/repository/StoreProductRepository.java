package com.robottitto.repository;

import com.robottitto.dao.StoreDAO;
import com.robottitto.dao.StoreProductDAO;
import com.robottitto.model.Store;
import com.robottitto.model.StoreProduct;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class StoreProductRepository {

    public static List<StoreProduct> getStoreProducts() throws FileNotFoundException {
        return StoreProductDAO.getStoreProducts();
    }

    public static int getStoreProductStock(StoreProduct storeProduct) {
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
