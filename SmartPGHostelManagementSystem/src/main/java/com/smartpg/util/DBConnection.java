package com.smartpg.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    // Connection settings are loaded once from db.properties (next to this
    // class on the classpath) instead of being hardcoded here, so the URL
    // and credentials can be changed without editing/recompiling Java code.
    private static final String DB_URL;
    private static final String DB_USERNAME;
    private static final String DB_PASSWORD;

    static {

        Properties props = new Properties();

        try (InputStream in = DBConnection.class.getResourceAsStream("db.properties")) {

            if (in == null) {
                throw new IOException(
                        "db.properties not found next to DBConnection.class " +
                        "(expected at com/smartpg/util/db.properties on the classpath)");
            }

            props.load(in);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    "Could not load database configuration: " + e.getMessage());
        }

        DB_URL = props.getProperty("db.url");
        DB_USERNAME = props.getProperty("db.username");
        DB_PASSWORD = props.getProperty("db.password");
    }

    public static Connection getConnection() {

        Connection con = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;
    }

}