package com.robottitto.util;

import com.robottitto.model.*;
import com.robottitto.repository.CustomerRepository;
import com.robottitto.repository.ProductRepository;
import com.robottitto.repository.StoreProductRepository;
import com.robottitto.repository.StoreRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getProductsByStore(int storeId) {
        List<Product> products = ProductRepository.getProductsByStore(storeId);
        System.out.println("Lista de produtos:");
        for (Product p : products) {
            System.out.printf("Id: %d Nome: %s Prezo: %f%n", p.getId(), p.getName(), p.getPrice());
        }
    }

    public static void getCustomers() {
        try {
            List<Customer> customers = CustomerRepository.getCustomers();
            System.out.println("Lista de clientes:");
            for (Customer c : customers) {
                System.out.printf("Id: %d Nome: %s Apelido: %s Email: %s%n", c.getId(), c.getName(), c.getSurname(), c.getEmail());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getStoreProducts() {
        try {
            List<StoreProduct> storeProducts = StoreProductRepository.getStoreProducts();
            for (StoreProduct sp : storeProducts) {
                System.out.printf("IdTenda: %d Tenda: %s IdProduto: %d Produto: %s Stock: %d%n", sp.getId().getStoreId(), sp.getStore().getName(), sp.getId().getProductId(), sp.getProduct().getName(), sp.getQuantity());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getReport() {
        Report report = new Report();
        try {
            List<Store> stores = StoreRepository.getStores();
            for (Store store : stores) {
                ReportStore reportStore = new ReportStore(store.getId(), store.getName());
                for (StoreProduct storeProduct : store.getProducts()) {
                    ReportProduct reportProduct = new ReportProduct(storeProduct.getProduct().getId(), storeProduct.getProduct().getName(), storeProduct.getQuantity());
                    reportStore.getProducts().add(reportProduct);
                }
                report.getStores().add(reportStore);
            }
            JSONUtils.generateReport(report);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
