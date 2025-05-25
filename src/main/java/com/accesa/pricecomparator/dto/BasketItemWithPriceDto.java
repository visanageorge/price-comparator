package com.accesa.pricecomparator.dto;

/**
 * DTO used to return a basket item along with its computed price.
 * Useful for displaying enriched basket information.
 */
public class BasketItemWithPriceDto {
    private String productName;
    private int quantity;
    private Double price;

    public BasketItemWithPriceDto() {
    }
    public BasketItemWithPriceDto(String productName, int quantity, Double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
