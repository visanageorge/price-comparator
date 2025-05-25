package com.accesa.pricecomparator.dto;

/**
 * DTO for creating a price alert.
 * Carries product ID and user-defined target price.
 */
public class PriceAlertDto {
    private String productId;
    private Double targetPrice;

    public PriceAlertDto(String productId, Double targerPrice) {
        this.productId = productId;
        this.targetPrice = targerPrice;
    }

    public PriceAlertDto() {
    }

    public String getProductId() {
        return productId;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }
}
