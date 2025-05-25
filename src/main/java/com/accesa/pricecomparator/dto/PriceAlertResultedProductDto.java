package com.accesa.pricecomparator.dto;

/**
 * DTO representing a product that has matched a user's price alert.
 * Used to notify users about price drops.
 */
public class PriceAlertResultedProductDto {
    private String productId;
    private String productName;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private String storeName;
    private boolean withDiscount;

    public PriceAlertResultedProductDto(String productId, String productName, Double price, Double discount, String storeName, boolean withDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.storeName = storeName;
        this.withDiscount = withDiscount;
        this.finalPrice = price * (1 - discount/100.0);
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getStoreName() {
        return storeName;
    }

    public boolean isWithDiscount() {
        return withDiscount;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }
}
