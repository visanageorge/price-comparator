package com.accesa.pricecomparator.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity representing a user-defined price alert for a product.
 * <p>
 *
 * Used for automated notification and recommendation features.
 */
@Entity
public class PriceAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private Double targetPrice;
    private boolean active;
    private LocalDate createdAt;

    public PriceAlert(Product product, Double targetPrice, boolean active, LocalDate createdAt) {
        this.product = product;
        this.targetPrice = targetPrice;
        this.active = active;
        this.createdAt = createdAt;
    }

    public PriceAlert() {

    }

    public Product getProduct() {
        return product;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
