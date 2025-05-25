package com.accesa.pricecomparator.model.intermediate;

/**
 * Helper class to represent different price options for a product.
 * Computes final price based on discount percentage.
 */
public class PriceOption {
    private String productId;
    private String productName;
    private String storeName;
    private double price;
    private double percentageOfDiscount;
    private double finalPrice;

    public PriceOption(String productId, String productName, String storeName, double price, double percentageOfDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.storeName = storeName;
        this.price = price;
        this.percentageOfDiscount = percentageOfDiscount;
        this.finalPrice = price*(1-percentageOfDiscount/100);
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public double getPrice() {
        return price;
    }

    public double getPercentageOfDiscount() {
        return percentageOfDiscount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    @Override
    public String toString() {
        return "PriceOption{" +
               "productId='" + productId + '\'' +
               ", productName='" + productName + '\'' +
               ", storeName='" + storeName + '\'' +
               ", price=" + price +
               ", percentageOfDiscount=" + percentageOfDiscount +
               ", finalPrice=" + finalPrice +
               '}';
    }
}
