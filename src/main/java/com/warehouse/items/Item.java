package com.warehouse.items;

public abstract class Item implements ItemInterface {

    protected String paymentMethod;
    protected String productName;
    protected String description;
    protected Double price;
    protected Integer quantity;

    public Item() {

    }


    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(Double price) {
    }

    @Override
    public void setQuantity(Integer quantity) {
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }


    @Override
    public void getPrice() {
    }

    @Override
    public void getQuantity() {
    }
}
