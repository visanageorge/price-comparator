package com.accesa.pricecomparator.dto;

import com.accesa.pricecomparator.model.Store;

import java.time.LocalDate;
import java.util.Date;

/**
 * DTO encapsulating the price evolution of a product.
 * Includes product identifiers and metadata alongside price and date.
 */
public class PriceHistoryDto {

    private String productId;
    private String productName;
    private String store;
    private String brand;
    private String category;
    private Double price;
    private LocalDate priceDate;
    public PriceHistoryDto() {
    }

    public PriceHistoryDto(String productId, String productName, String store, String brand, String category, Double price, LocalDate priceDate) {
        this.productId = productId;
        this.productName = productName;
        this.store = store;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.priceDate = priceDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getStore() {
        return store;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getPriceDate() {
        return priceDate;
    }
}
