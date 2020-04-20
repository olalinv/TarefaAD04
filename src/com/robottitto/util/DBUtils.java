package com.robottitto.util;

import com.robottitto.dao.ProvinceDAO;
import com.robottitto.model.Province;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtils {

    public static Session connect() {
        Session session = null;
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.OFF);
            session = HibernateUtils.getSessionFactory().openSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return session;
    }

    public static void insertProvinces() throws FileNotFoundException {
        List<Province> provinces = JSONUtils.readProvinces();
        for (Province province : provinces) {
            ProvinceDAO.addProvince(province);
        }
    }

}
