package com.warehouse.items;

public interface ItemInterface {

    public void setDescription(String description);
    public void setPrice(Double price);
    public void setQuantity(Integer quantity);
    public void setProductName(String productName);
    public void setPaymentMethod(String paymentMethod);

    public String getProductName();
    public String getDescription();
    public String getPaymentMethod();
    public Double getPrice();
    public int getQuantity();


}
