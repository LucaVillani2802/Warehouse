package com.warehouse.database;

import com.warehouse.items.Item;

public class DatabaseItem extends Item {
    public Double totalCost;
    public String personName;
    public String personSurname;
    public Integer orderedQuantity;
    public Integer orderId;


    public DatabaseItem(Integer id, String personName, String personSurname, String productName, Integer quantity, Double price, String paymentMethod) {
        this.orderId = id;
        this.personName = personName;
        this.personSurname = personSurname;
        this.productName = productName;
        this.totalCost = price;
        this.paymentMethod = paymentMethod;
        this.orderedQuantity = quantity;
    }

    public DatabaseItem(String text, String text1, String value, Integer value1, double v, String value2) {
        this.personName = text;
        this.personSurname = text1;
        this.productName = value;
        this.orderedQuantity = value1;
        this.totalCost = v;
        this.paymentMethod = value2;
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

    public Integer getOrderId()
    {
        return this.orderId;
    }
}
