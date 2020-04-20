package com.robottitto.model;

public class Config {

    private DbConnection dbConnection;
    private Hibernate hibernate;

    public String getAddress() {
        return dbConnection.address;
    }

    public int getPort() {
        return dbConnection.port;
    }

    public String getName() {
        return dbConnection.name;
    }

    public String getUser() {
        return dbConnection.user;
    }

    public String getPassword() {
        return dbConnection.password;
    }

    public String getDriver() {
        return hibernate.driver;
    }

    public String getDialect() {
        return hibernate.dialect;
    }

    public String getHBM2DDL_AUTO() {
        return hibernate.HBM2DDL_AUTO;
    }

    public String getSHOW_SQL() {
        return hibernate.SHOW_SQL;
    }

    public class DbConnection {
        String address;
        int port;
        String name;
        String user;
        String password;
    }

    public class Hibernate {
        String driver;
        String dialect;
        String HBM2DDL_AUTO;
        String SHOW_SQL;
    }

}
