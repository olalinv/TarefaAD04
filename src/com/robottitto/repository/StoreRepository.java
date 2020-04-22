package com.robottitto.repository;

import com.robottitto.dao.StoreDAO;
import com.robottitto.model.Store;
import org.hibernate.HibernateException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class StoreRepository {

    public static List<Store> getStores() throws FileNotFoundException {
        return StoreDAO.getStores();
    }

    public static Store getStore(int storeId) {
        return StoreDAO.getStore(storeId);
    }

    public static void addStore(Store store) {
        StoreDAO.addStore(store);
    }

    public static void deleteStore(int storeId) {
        StoreDAO.deleteStore(storeId);
    }

}
