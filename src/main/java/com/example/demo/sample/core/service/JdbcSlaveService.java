package com.example.demo.sample.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class JdbcSlaveService {
    @Value("${spring.datasource.driver-class-name}")
    private String DB_DRIVER;

    @Value("${spring.datasource.slave.url}")
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

    public Map queryForSelectOne(String sql) throws SQLException {
        Map<String, Object> result = null;
        Connection con = getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();

            try {
                rs = stmt.executeQuery(sql);

                try {
                    ResultSetMetaData metaData = rs.getMetaData();
                    Integer columnCount = metaData.getColumnCount();
                    while (rs.next()) {
                        result = new HashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            result.put(metaData.getColumnLabel(i), rs.getObject(i));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("JDBC queryForSelectOne ERROR >> " + sql);
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


    public ArrayList<Map> queryForSelectList(String sql) throws SQLException {
        ArrayList<Map> result = new ArrayList<>();
        Connection con = getJDBCConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();

            try {
                rs = stmt.executeQuery(sql);

                try {
                    ResultSetMetaData metaData = rs.getMetaData();
                    Integer columnCount = metaData.getColumnCount();
                    Map<String, Object> row;
                    // Map<Column Label(Alias Name NOT Column Name), Column Value>
                    while (rs.next()) {
                        row = new HashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.put(metaData.getColumnLabel(i), rs.getObject(i));
                        }
                        result.add(row);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("JDBC queryForSelectList ERROR >> " + sql);
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
