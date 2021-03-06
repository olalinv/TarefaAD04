package com.robottitto.dao;

import com.robottitto.model.Product;
import com.robottitto.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static List<Product> getProducts() throws FileNotFoundException {
        List<Product> products;
        products = HibernateUtils.createQuery("SELECT p FROM Product p").list();
        return products;
    }

    public static Product getProduct(int productId) {
        Product product = new Product();
        try {
            product = HibernateUtils.getSession().get(Product.class, productId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> getProductsByStore(int storeId) {
        List<Product> products = new ArrayList<Product>();
        try {
            Query query = HibernateUtils.getSession().createQuery("SELECT p FROM Product p JOIN p.storeProducts sp WHERE STORE_ID=:storeId");
            query.setParameter("storeId", storeId);
            products = query.list();
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
