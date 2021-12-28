package com.warehouse.database;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DatabasePropertyItem {

    SimpleStringProperty personName;
    SimpleStringProperty personSurname;
    SimpleStringProperty productName;
    SimpleStringProperty paymentMethod;
    SimpleIntegerProperty orderedQuantity;
    SimpleDoubleProperty totalCost;

    public DatabasePropertyItem(String personName, String personSurname, String productName, Integer quantity, Double price, String paymentMethod) {
        this.personName = new SimpleStringProperty(personName);
        this.personSurname = new SimpleStringProperty(personSurname);
        this.productName = new SimpleStringProperty(productName);
        this.totalCost = new SimpleDoubleProperty(price);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.orderedQuantity = new SimpleIntegerProperty(quantity);
    }
}
