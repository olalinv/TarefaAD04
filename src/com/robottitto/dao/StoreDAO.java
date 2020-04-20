package com.robottitto.dao;

import com.robottitto.model.Product;
import com.robottitto.model.Store;
import com.robottitto.util.DBUtils;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    public static List<Store> getStores() throws SQLException, FileNotFoundException {
        List<Store> stores = new ArrayList<Store>();
        stores = HibernateUtils.createQuery("SELECT * FROM STORE").list();
        return stores;
    }

    public static Store getStore(String storeName) throws HibernateException, FileNotFoundException {
        Store store = new Store();
        Query query = HibernateUtils.getSession().createQuery("SELECT * FROM STORE WHERE NAME=:name LIMIT 1");
        query.setParameter("name", storeName);
        return store;
    }

    public static void addStore(Store store) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.save(store);
        transaction.commit();
    }

    public static void deleteStore(Store store) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.delete(store);
        transaction.commit();
    }

}
