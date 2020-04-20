package com.robottitto.util;

import com.robottitto.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.TimeZone;

public class HibernateUtils {

    private static final String DB_CONFIG = "config.json";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws FileNotFoundException {
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration();
                Config config = JSONUtils.readConfig(DB_CONFIG);
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, config.getDriver());
                properties.put(Environment.URL, "jdbc:mysql://" + config.getAddress() + ":" + config.getPort() + "/" + config.getName() + "?serverTimezone=" + TimeZone.getDefault().getID());
                properties.put(Environment.USER, config.getUser());
                properties.put(Environment.PASS, config.getPassword());
                properties.put(Environment.DIALECT, config.getDialect());
                properties.put(Environment.HBM2DDL_AUTO, config.getHBM2DDL_AUTO());
                properties.put(Environment.SHOW_SQL, config.getHBM2DDL_AUTO());
                conf.setProperties(properties);
                conf.addAnnotatedClass(Province.class);
                conf.addAnnotatedClass(Store.class);
                conf.addAnnotatedClass(Product.class);
                conf.addAnnotatedClass(Employee.class);
                conf.addAnnotatedClass(Customer.class);
                conf.addAnnotatedClass(StoreProduct.class);
                conf.addAnnotatedClass(StoreEmployee.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSession() {
        sessionFactory.close();
        sessionFactory = null;
    }

    public static Query createQuery(String query) throws FileNotFoundException {
        return getSessionFactory().openSession().createQuery(query);

    }

    public static Session getSession() throws FileNotFoundException {
        return getSessionFactory().openSession();
    }

}
