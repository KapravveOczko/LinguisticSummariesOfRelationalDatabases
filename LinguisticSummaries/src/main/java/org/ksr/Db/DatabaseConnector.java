package org.ksr.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void queryDataTable(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // getting metadata
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // printing column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();

            // printing data
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Double> getDataFromColumn(String tableName, String columnName) {
        ArrayList<Double> data = new ArrayList<>();
        String query = "SELECT " + columnName + " FROM " + tableName;
        System.out.println(query);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Double value = rs.getDouble(columnName);
                data.add(value);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


//    obsolete
//    public void queryData(String tableName, String columnName) {
//        String query = "SELECT * FROM" + tableName;
//
//        try (Connection conn = connect();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//
//            while (rs.next()) {
//                System.out.println(rs.getString(columnName));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
