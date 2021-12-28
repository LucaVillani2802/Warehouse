package com.warehouse.database;

import com.warehouse.items.Item;

public class DatabaseItem extends Item {
    public Double totalCost;
    public String personName;
    public String personSurname;
    public Integer orderedQuantity;


    public DatabaseItem(String personName, String personSurname, String productName, Integer quantity, Double price, String paymentMethod) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.productName = productName;
        this.totalCost = price;
        this.paymentMethod = paymentMethod;
        this.orderedQuantity = quantity;
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

    @Override
    public Integer getQuantity(){
        return this.orderedQuantity;
    }

}
