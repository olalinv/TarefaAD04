package com.robottitto.repository;

import com.robottitto.dao.CustomerDAO;
import com.robottitto.model.Customer;

import java.io.FileNotFoundException;
import java.util.List;

public class CustomerRepository {

    public static List<Customer> getCustomers() throws FileNotFoundException {
        return CustomerDAO.getCustomers();
    }

    public static void addCustomer(Customer customer) {
        CustomerDAO.addCustomer(customer);
    }

    public static void deleteCustomer(int customerId) {
        CustomerDAO.deleteCustomer(customerId);
    }

}
