package com.warehouse.items;

import com.warehouse.database.DatabaseItem;
import com.warehouse.database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;

public class EditableItem extends Item{
    Integer id;
    public EditableItem(Integer id, String productName, String description, Integer quantity, Double price){
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }

    public EditableItem(String text, String descr, Integer value, double parseDouble) {
        this.productName = text;
        this.description = descr;
        this.quantity = value;
        this.price = parseDouble;
    }

    public Integer getId(){
        return this.id;
    }
    public static double findPrice(String itemname){
        String sql = "SELECT price FROM ItemsTable WHERE item_name = ?";

        try(Connection connection = DriverManager.getConnection(DatabaseManager.url);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, itemname);

            ResultSet rs = statement.executeQuery();
            return rs.getDouble("price");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0;
    }

    public static ObservableList<String> findnames(){
        String sql = "SELECT item_name FROM ItemsTable";

        ObservableList<String> itemsnames = FXCollections.observableArrayList();

        try(Connection connection = DriverManager.getConnection(DatabaseManager.url);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                itemsnames.add(rs.getString("item_name"));
            }

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return itemsnames;
    }
}
