package com.robottitto.dao;

import com.robottitto.model.Customer;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static List<Customer> getCustomers() throws HibernateException, FileNotFoundException {
        List<Customer> customers;
        customers = HibernateUtils.createQuery("SELECT c FROM Customer c").list();
        return customers;
    }

    public static void addCustomer(Customer customer) throws HibernateException {
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            System.out.println("Engadiuse o cliente " + customer.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Non se puido engadir o cliente " + customer.getName());
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int customerId) {
        int deletedRows = 0;
        try {
            Transaction transaction = null;
            Session session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Customer WHERE id=:customerId");
            query.setParameter("customerId", customerId);
            deletedRows = query.executeUpdate();
            transaction.commit();
            if (deletedRows > 0) {
                System.out.println("Eliminouse o cliente " + customerId);
            } else {
                System.out.println("Non se atopou o cliente " + customerId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
