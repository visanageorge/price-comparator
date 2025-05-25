package com.accesa.pricecomparator.dto;

/**
 * DTO representing an item in the user's shopping basket.
 * Contains product name and quantity.
 */
public class BasketItemDto {
    private String productName;
    private int quantity;

    public BasketItemDto() {
    }

    public BasketItemDto(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
