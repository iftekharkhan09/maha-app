package com.neobank.domain;

public class OrderLine {
    private String productId;
    private int quantity;

    public OrderLine (String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId () { return productId; }

    public int getQuantity () { return quantity; }

    @Override
    public String toString () {
        return "OrderLine{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
