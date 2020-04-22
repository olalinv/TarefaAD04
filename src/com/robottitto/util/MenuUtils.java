package com.robottitto.util;

import com.robottitto.model.Product;
import com.robottitto.model.Store;
import com.robottitto.repository.ProductRepository;
import com.robottitto.repository.StoreRepository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class MenuUtils {

    public static void getStores() {
        try {
            List<Store> stores = StoreRepository.getStores();
            System.out.println("Lista de tendas:");
            for (Store s : stores) {
                System.out.printf("Id: %d Nome: %s Cidade: %s Provincia: %s%n", s.getId(), s.getName(), s.getCity(), s.getProvince().getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getProducts() {
        try {
            List<Product> products = ProductRepository.getProducts();
            System.out.println("Lista de produtos:");
            for (Product p : products) {
                System.out.printf("Id: %d Nome: %s Descrición: %s Prezo: %f%n", p.getId(), p.getName(), p.getDescription(), p.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void getProductsByStore(int storeId) {
        List<Product> products = ProductRepository.getProductsByStore(storeId);
        System.out.println("Lista de produtos:");
        for (Product p : products) {
            System.out.printf("Id: %d Nome: %s Prezo: %f", p.getId(), p.getName(), p.getPrice());
        }
    }

}
