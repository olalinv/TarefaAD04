package com.robottitto.dao;

import com.robottitto.model.Product;
import com.robottitto.util.DBUtils;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static List<Product> getProducts() throws SQLException, FileNotFoundException {
        List<Product> products = new ArrayList<Product>();
        products = HibernateUtils.createQuery("SELECT * FROM PRODUCT").list();
        return products;
    }

    public static List<Product> getProductsByStore(String storeName) throws HibernateException, FileNotFoundException {
        List<Product> products = new ArrayList<Product>();
        // String sql = "SELECT * FROM STORE_PRODUCT a, PRODUCT b, STORE c WHERE a.PRODUCT_ID = b.PRODUCT_ID AND a.STORE_ID = c.STORE_ID AND c.NAME = ?";
        return products;
    }

    public static Product getProduct(String productName) throws HibernateException, FileNotFoundException {
        Product product = new Product();
        // String sql = "SELECT * FROM PRODUCT WHERE NAME = ? LIMIT 1";
        return product;
    }

    public static void addProduct(Product product) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
    }

    public static void deleteProduct(Product product) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.delete(product);
        transaction.commit();
    }


}
