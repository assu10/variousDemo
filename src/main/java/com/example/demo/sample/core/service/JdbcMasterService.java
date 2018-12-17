package com.example.demo.sample.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class JdbcMasterService {
    @Value("${spring.datasource.driver-class-name}")
    private String DB_DRIVER;

    @Value("${spring.datasource.url}")
    private String DB_CONNECTION;

    @Value("${spring.datasource.username}")
    private String DB_USER;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    public Connection getJDBCConnection() {
        Connection dbConn = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbConn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConn;
    }

    public int queryForInsertUpdate(String sql) throws SQLException {
        Connection con = getJDBCConnection();
        Statement stmt = null;
        int result = 0;
        try {
            stmt = con.createStatement();

            try {
                result = stmt.executeUpdate(sql);

            } catch (Exception e) {
                System.out.println("JDBC queryForInsertUpdate ERROR >> " + sql);
                e.printStackTrace();
            } finally {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.close();
        }

        return result;
    }
}
