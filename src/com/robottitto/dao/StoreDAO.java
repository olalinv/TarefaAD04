package com.robottitto.dao;

import com.robottitto.model.Store;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    public static List<Store> getStores() throws FileNotFoundException {
        List<Store> stores = new ArrayList<Store>();
        stores = HibernateUtils.createQuery("SELECT s FROM Store s").list();
        return stores;
    }

    public static Store getStore(int storeId) {
        Store store = new Store();
        try {
            store = HibernateUtils.getSession().get(Store.class, storeId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return store;
    }

    public static void addStore(Store store) throws HibernateException {
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(store);
            transaction.commit();
            System.out.println("Engadiuse a tenda " + store.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Non se puido engadir a tenda " + store.getName());
            e.printStackTrace();
        }
    }

    public static void deleteStore(int storeId) {
        int deletedRows = 0;
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Store WHERE id=:storeId");
            query.setParameter("storeId", storeId);
            deletedRows = query.executeUpdate();
            transaction.commit();
            if (deletedRows > 0) {
                System.out.println("Eliminouse a tenda " + storeId);
            } else {
                System.out.println("Non se atopou a tenda " + storeId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
