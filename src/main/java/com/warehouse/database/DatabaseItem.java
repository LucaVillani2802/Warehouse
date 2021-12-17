package com.warehouse.database;

import com.warehouse.items.Item;

public class DatabaseItem extends Item {
    public Double totalCost;
    public String personName;
    public String personSurname;


    public DatabaseItem(String personName, String personSurname, String productName, Integer quantity, Double price, String paymentMethod) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.productName = productName;
        this.totalCost = price * quantity;
        this.paymentMethod = paymentMethod;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public String getPersonName() {
        return personName;
    }
    public Double getTotalCost() {
        return totalCost;
    }

}
