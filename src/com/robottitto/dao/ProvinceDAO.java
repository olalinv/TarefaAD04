package com.robottitto.dao;

import com.robottitto.model.Province;
import com.robottitto.util.DBUtils;
import com.robottitto.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO {

    public static List<Province> getProvinces() throws HibernateException, FileNotFoundException {
        List<Province> provinces = new ArrayList<Province>();
        provinces = HibernateUtils.createQuery("SELECT p FROM Province p ORDER BY id").list();
        return provinces;
    }

    public static void addProvince(Province province) throws HibernateException, FileNotFoundException {
        Transaction transaction = null;
        Session session = HibernateUtils.getSession();
        transaction = session.beginTransaction();
        session.save(province);
        transaction.commit();
    }

}
