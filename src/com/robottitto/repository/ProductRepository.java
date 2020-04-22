package com.robottitto.repository;

import com.robottitto.dao.ProductDAO;
import com.robottitto.model.Product;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository {

    public static List<Product> getProducts() throws FileNotFoundException, SQLException {
        return ProductDAO.getProducts();
    }

    public static List<Product> getProductsByStore(int storeId) {
        return ProductDAO.getProductsByStore(storeId);
    }

    public static Product getProduct(int productId) {
        return ProductDAO.getProduct(productId);
    }

    public static void addProduct(Product product) {
        ProductDAO.addProduct(product);
    }

    public static void deleteProduct(int productId) {
        ProductDAO.deleteProduct(productId);
    }

}
