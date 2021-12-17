package com.warehouse.database;

import com.warehouse.items.Item;

public class DatabaseItem extends Item {
    public Double totalCost;
    public String personName;
    public String personSurname;

    public DatabaseItem(String personName, String personSurname,String productName, Double price, Integer quantity, String paymentMethod) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.productName = productName;
        this.totalCost = price * quantity;
        this.paymentMethod = paymentMethod;
    }

    public Double getTotalCost() {
        return totalCost;
    }

}
