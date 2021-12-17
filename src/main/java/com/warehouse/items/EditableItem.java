package com.warehouse.items;

public class EditableItem extends Item{

    public EditableItem(String productName, String description, Integer avaiableQuantity, Double price){
        this.productName = productName;
        this.description = description;
        this.avaiableQuantity = avaiableQuantity;
        this.price = price;
    }

}
