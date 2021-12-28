package com.warehouse.database;

import com.warehouse.items.EditableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class DatabaseManager {
    private static Connection connection;
    public static String url = "jdbc:sqlite:OrderList.db";

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

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS OrderTable (\n"
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


    public void createItemsTable(){

        String sql = "CREATE TABLE IF NOT EXISTS ItemsTable(\n"
                    + "id integer PRIMARY KEY, \n"
                    + "item_name text not null, \n"
                    + "description text, \n"
                    + "quantity_avaiable integer not null, \n"
                    + "price real not null"
                    + ");";
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()){
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void insertIntoDB(DatabaseItem databaseItem){

        String sql = "INSERT INTO OrderTable(name, surname, prdname, payment_method, price, quantity) VALUES(?,?,?,?,?,?)";


        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, databaseItem.getPersonName());
            pstmt.setString(2, databaseItem.getPersonSurname());
            pstmt.setString(3,databaseItem.getProductName());
            pstmt.setString(4,databaseItem.getPaymentMethod());
            pstmt.setDouble(5, databaseItem.getTotalCost());
            pstmt.setInt(6,databaseItem.getQuantity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertIntoItemTable( EditableItem editableItem ){

        String sql = "INSERT INTO ItemsTable (item_name, description, quantity_avaiable, price) VALUES(?,?,?,?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, editableItem.getProductName());
            pstmt.setString(2, editableItem.getDescription());
            pstmt.setInt(3,editableItem.getQuantity());
            pstmt.setDouble(4, editableItem.getPrice());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList<DatabasePropertyItem> showOrders(){
        String sql = "SELECT * FROM OrderTable";
        ObservableList<DatabasePropertyItem> data = FXCollections.observableArrayList();

        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){

                data.add(new DatabasePropertyItem(  rs.getString("name"),
                                            rs.getString("surname"),
                                            rs.getString("prdname"),
                                            rs.getInt("quantity"),
                                            rs.getDouble("price"),
                                            rs.getString("payment_method")));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
}