package com.warehouse.database;

import java.sql.*;
public class DatabaseManager {
    private static Connection connection;
    private String url;

    public DatabaseManager() throws ClassNotFoundException {

        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");


        try {
            // create a database connection
            this.connection = DriverManager.getConnection("jdbc:sqlite:OrderList.db");
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println(meta);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createOrderListTable(){

        this.url = "jdbc:sqlite:E://CodingProjects/JavaProjects/Warehouse/OrderList.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS OrderList (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + " surname text,\n"
                + " prdname text,\n"
                + " payment_method text,\n"
                + " price real,\n"
                + "	quantity integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void insertIntoDB(String name, String surname, String productName, Integer quantity, String paymentMethod){

        String sql = "INSERT INTO OrderList(name, surname, prdname, payment_method, price, quantity) VALUES(?,?,?,?,?,?)";


        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3,productName);
            pstmt.setString(4,paymentMethod);
            pstmt.setDouble(5, 0);
            pstmt.setInt(6,quantity);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}