package com.robottitto.dao;

import com.robottitto.model.Store;
import com.robottitto.model.StoreProduct;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StoreProductDAO {

    public static List<StoreProduct> getStoreProducts() throws FileNotFoundException {
        List<StoreProduct> storeProducts;
        storeProducts = HibernateUtils.createQuery("SELECT s FROM StoreProduct s").list();
        return storeProducts;
    }

    public static int getStoreProductQuantity(StoreProduct storeProduct) {
        int quantity = 0;
        try {
            StoreProduct sp = HibernateUtils.getSession().get(StoreProduct.class, storeProduct.getId());
            quantity = sp.getQuantity();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public static void addStoreProduct(StoreProduct storeProduct) throws HibernateException {
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(storeProduct);
            transaction.commit();
            System.out.println("Engadíronse " + storeProduct.getQuantity() + " unidades do produto " + storeProduct.getProduct().getName() + " á tenda " +
                    storeProduct.getStore().getName());
        } catch (FileNotFoundException e) {
            System.out.println("Non se puideron engadir " + storeProduct.getQuantity() + " unidades do produto " + storeProduct.getProduct().getName() + " á tenda " + storeProduct.getStore().getName());
            e.printStackTrace();
        }
    }

    public static void updateStoreProduct(StoreProduct storeProduct) {
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.merge(storeProduct);
            transaction.commit();
            System.out.println("Actualizouse a " + storeProduct.getQuantity() + " unidades do produto " + storeProduct.getProduct().getName() + " na tenda " +
                    storeProduct.getStore().getName());
        } catch (FileNotFoundException e) {
            System.out.println("Non se pudo actualizar a " + storeProduct.getQuantity() + " unidades do produto " + storeProduct.getProduct().getName() + " na tenda " +
                    storeProduct.getStore().getName());
            e.printStackTrace();
        }
    }

    public static void deleteStoreProduct(StoreProduct storeProduct) {
        int deletedRows = 0;
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM StoreProduct WHERE STORE_ID=:storeId AND PRODUCT_ID=:productId");
            query.setParameter("storeId", storeProduct.getStore().getId());
            query.setParameter("productId", storeProduct.getProduct().getId());
            deletedRows = query.executeUpdate();
            transaction.commit();
            if (deletedRows > 0) {
                System.out.println("Eliminouse o produto " + storeProduct.getProduct().getName() + " na tenda " +
                        storeProduct.getStore().getName());
            } else {
                System.out.println("Non se pudo eliminar o produto " + storeProduct.getProduct().getName() + " na tenda " +
                        storeProduct.getStore().getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
