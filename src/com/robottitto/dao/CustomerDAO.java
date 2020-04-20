package com.robottitto.dao;

import com.robottitto.model.Customer;
import com.robottitto.util.DBUtils;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static List<Customer> getCustomers() throws HibernateException, FileNotFoundException {
        List<Customer> customers = new ArrayList<Customer>();
        customers = HibernateUtils.createQuery("SELECT * FROM CUSTOMER").list();
        return customers;
    }

    public static void addCustomer(Customer customer) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
    }

    public static void removeCustomer(Customer customer) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
    }

}
