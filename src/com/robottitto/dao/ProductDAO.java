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

public class ProductDAO {

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            products = HibernateUtils.createQuery("SELECT p FROM Product p").list();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Product getProduct(int productId) {
        Product product = new Product();
        Query query = null;
        try {
            query = HibernateUtils.getSession().createQuery("SELECT p FROM Product p WHERE p.id=:productId");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        query.setParameter("productId", productId);
        return product;
    }

    public static List<Product> getProductsByStore(int storeId) {
        List<Product> products = new ArrayList<Product>();
        Query query = null;
        try {
            query = HibernateUtils.getSession().createQuery("SELECT p FROM Product p JOIN p.storeProducts s WHERE STORE_ID=:storeId");
            query.setParameter("storeId", storeId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void addProduct(Product product) {
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            System.out.println("Engadiuse o produto " + product.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Non se puido engadir o produto " + product.getName());
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int productId) {
        int deletedRows = 0;
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Product WHERE id=:productId");
            query.setParameter("productId", productId);
            deletedRows = query.executeUpdate();
            transaction.commit();
            if (deletedRows > 0) {
                System.out.println("Eliminouse o produto " + productId);
            } else {
                System.out.println("Non se atopou o produto " + productId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
