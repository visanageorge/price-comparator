package com.accesa.pricecomparator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * JPA entity representing a product.
 * Contains identifiers, brand, category, quantity and unit.
 */
@Entity
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productCategory;
    private String productBrand;
    private Double productQuantity;
    private String productUnit;

    public Product() {
    }

    public Product(String productId, String productName, String productCategory, String productBrand, double productQuantity, String productUnit) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.productQuantity = productQuantity;
        this.productUnit = productUnit;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public Double getProductQuantity() {
        return productQuantity;
    }

    public String getProductUnit() {
        return productUnit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productQuantity=" + productQuantity +
                ", productUnit='" + productUnit + '\'' +
                '}';
    }
}
