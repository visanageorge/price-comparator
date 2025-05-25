package com.accesa.pricecomparator.dto;

/**
 * DTO for recommending a product, including pricing and unit normalization logic.
 * Automatically computes value per unit to enable fair comparison.
 */
public class ProductRecommendationDto {
    private String productId;
    private String productName;
    private Double quantity;
    private String unit;
    private Double price;
    private Double valuePerUnit;
    private String storeName;

    public ProductRecommendationDto(String productId, String productName, Double quantity, String unit, Double price, String storeName) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.storeName = storeName;
        this.valuePerUnit = getValuePerUnit();
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
    public Double getQuantity() {
        return quantity;
    }
    public String getUnit() {
        return unit;
    }

    public Double getPrice() {
        return price;
    }
    public String getStoreName() {
        return storeName;
    }
    public Double getValuePerUnit() {
        Double value = switch (unit.toLowerCase()){
            case "g", "ml" -> quantity / 1000;
            case "kg", "l" -> quantity;
            default -> 1.0;
        };
        return price/value;
    }
}
