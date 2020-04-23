package com.robottitto;

import com.robottitto.model.*;
import com.robottitto.repository.*;
import com.robottitto.util.DBUtils;
import com.robottitto.util.MenuUtils;
import com.robottitto.util.SAXUtils;
import org.hibernate.Session;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String URL_EL_PAIS = "http://ep00.epimg.net/rss/elpais/portada.xml";

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        DBUtils.connect();
        DBUtils.insertProvinces();
        showMenu();
    }

    private static void showMenu() throws FileNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("[1] Engadir unha tenda");
            System.out.println("[2] Mostrar as tendas");
            System.out.println("[3] Eliminar unha tenda");
            System.out.println("[4] Engadir un produto");
            System.out.println("[5] Mostrar os productos da franquicia");
            System.out.println("[6] Engadir un producto a unha tenda");
            System.out.println("[7] Mostrar os productos dunha tenda");
            System.out.println("[8] Actualizar o stock dun producto nunha determinada tenda");
            System.out.println("[9] Mostrar o stock dun producto dunha tenda");
            System.out.println("[10] Eliminar un producto dunha determinada tenda");
            System.out.println("[11] Eliminar un producto");
            System.out.println("[12] Engadir un cliente");
            System.out.println("[13] Mostrar os clientes");
            System.out.println("[14] Eliminar un cliente");
            System.out.println("[15] Xerar un informe json co stock de todos os productos en formato JSON");
            System.out.println("[16] Ler os titulares do periódico El País");
            System.out.println("[0] Saír do aplicación");
            System.out.println("Elixa unha opción:");
            do {
                option = Integer.parseInt(scanner.nextLine());
                if (option < 0 || option > 16) {
                    System.out.println("Erro: introduza unha opción entre 0 e 16");
                }
            } while (option < 0 || option > 16);
            switch (option) {
                case 0:
                    System.out.println("Saír da aplicación");
                    break;
                case 1:
                    // Nome
                    Store store = new Store();
                    System.out.println("Introduza o nome:");
                    String enterName = scanner.nextLine();
                    store.setName(enterName);
                    // Cidade
                    System.out.println("Introduza o cidade:");
                    String enterCity = scanner.nextLine();
                    store.setCity(enterCity);
                    // Provincia
                    try {
                        List<Province> provinces = ProvinceRepository.getProvinces();
                        System.out.println("Elixa unha provincia:");
                        for (Province p : provinces) {
                            System.out.printf("[%d] %s%n", p.getId(), p.getName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Introduza a provincia:");
                    int enterProvince = Integer.parseInt(scanner.nextLine());
                    Province province = new Province();
                    province.setId(enterProvince);
                    store.setProvince(province);
                    StoreRepository.addStore(store);
                    break;
                case 2:
                    MenuUtils.getStores();
                    break;
                case 3:
                    MenuUtils.getStores();
                    System.out.println("Introduza o ID da tenda:");
                    int enterStoreId = Integer.parseInt(scanner.nextLine());
                    StoreRepository.deleteStore(enterStoreId);
                    break;
                case 4:
                    Product product = new Product();
                    System.out.println("Introduza o nome do produto:");
                    String enterProductName = scanner.nextLine();
                    product.setName(enterProductName);
                    System.out.println("Introduza a descrición do produto:");
                    String enterProductDescription = scanner.nextLine();
                    product.setDescription(enterProductDescription);
                    System.out.println("Introduza o prezo do produto:");
                    double enterProductPrice = Double.parseDouble(scanner.nextLine());
                    product.setPrice(enterProductPrice);
                    ProductRepository.addProduct(product);
                    break;
                case 5:
                    MenuUtils.getProducts();
                    break;
                case 6:
                    MenuUtils.getStores();
                    // ID tenda
                    System.out.println("Introduza o ID da tenda:");
                    enterStoreId = Integer.parseInt(scanner.nextLine());
                    store = StoreRepository.getStore(enterStoreId);
                    MenuUtils.getProducts();
                    // ID produto
                    System.out.println("Introduza o ID do produto:");
                    int enterProductId = Integer.parseInt(scanner.nextLine());
                    product = ProductRepository.getProduct(enterProductId);
                    // Cantidade produto
                    System.out.println("Introduza a cantidade do produto:");
                    int enterQuantity = Integer.parseInt(scanner.nextLine());
                    StoreProduct storeProduct = new StoreProduct(store, product);
                    storeProduct.setQuantity(enterQuantity);
                    StoreProductRepository.addStoreProduct(storeProduct);
                    break;
                case 7:
                    MenuUtils.getStores();
                    // ID tenda
                    System.out.println("Introduza o ID da tenda:");
                    enterStoreId = Integer.parseInt(scanner.nextLine());
                    MenuUtils.getProductsByStore(enterStoreId);
                    break;
                case 8:
                    MenuUtils.getStores();
                    // ID tenda
                    System.out.println("Introduza o ID da tenda:");
                    enterStoreId = Integer.parseInt(scanner.nextLine());
                    store = StoreRepository.getStore(enterStoreId);
                    MenuUtils.getProductsByStore(enterStoreId);
                    // ID produto
                    System.out.println("Introduza o ID do produto:");
                    enterProductId = Integer.parseInt(scanner.nextLine());
                    product = ProductRepository.getProduct(enterProductId);
                    // Cantidade produto
                    System.out.println("Introduza a cantidade do produto:");
                    enterQuantity = Integer.parseInt(scanner.nextLine());
                    storeProduct = new StoreProduct(store, product);
                    storeProduct.setQuantity(enterQuantity);
                    StoreProductRepository.updateStoreProduct(storeProduct);
                    break;
                case 9:
                    MenuUtils.getStores();
                    // ID tenda
                    System.out.println("Introduza o ID da tenda:");
                    enterStoreId = Integer.parseInt(scanner.nextLine());
                    store = StoreRepository.getStore(enterStoreId);
                    MenuUtils.getProductsByStore(enterStoreId);
                    // ID produto
                    System.out.println("Introduza o ID do produto:");
                    enterProductId = Integer.parseInt(scanner.nextLine());
                    product = ProductRepository.getProduct(enterProductId);
                    storeProduct = new StoreProduct(store, product);
                    int stock = StoreProductRepository.getStoreProductStock(storeProduct);
                    System.out.printf("Stock: %d%n", stock);
                    break;
                case 10:
                    storeProduct = new StoreProduct();
                    MenuUtils.getStores();
                    // ID tenda
                    System.out.println("Introduza o ID da tenda:");
                    enterStoreId = Integer.parseInt(scanner.nextLine());
                    storeProduct.setStore(StoreRepository.getStore(enterStoreId));
                    MenuUtils.getProductsByStore(enterStoreId);
                    // ID produto
                    System.out.println("Introduza o ID do produto:");
                    enterProductId = Integer.parseInt(scanner.nextLine());
                    storeProduct.setProduct(ProductRepository.getProduct(enterProductId));
                    StoreProductRepository.removeStoreProduct(storeProduct);
                    break;
                case 11:
                    MenuUtils.getProducts();
                    System.out.println("Introduza o ID do produto:");
                    enterProductId = Integer.parseInt(scanner.nextLine());
                    ProductRepository.deleteProduct(enterProductId);
                    break;
                case 12:
                    Customer customer = new Customer();
                    System.out.println("Introduza o nome do cliente:");
                    String enterCustomerName = scanner.nextLine();
                    customer.setName(enterCustomerName);
                    System.out.println("Introduza o apelido do cliente:");
                    String enterCustomerSurname = scanner.nextLine();
                    customer.setSurname(enterCustomerSurname);
                    System.out.println("Introduza o email do cliente:");
                    String enterCustomerEmail = scanner.nextLine();
                    customer.setEmail(enterCustomerEmail);
                    CustomerRepository.addCustomer(customer);
                    break;
                case 13:
                    MenuUtils.getCustomers();
                    break;
                case 14:
                    MenuUtils.getCustomers();
                    System.out.println("Introduza o ID do cliente:");
                    int enterCustomerId = Integer.parseInt(scanner.nextLine());
                    CustomerRepository.deleteCustomer(enterCustomerId);
                    break;
                case 15:
                    MenuUtils.getReport();
                    break;
                case 16:
                    SAXUtils.listHeadings(URL_EL_PAIS);
                    break;
            }
        } while (option != 0);
    }

}
