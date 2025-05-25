package com.accesa.pricecomparator.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity representing a historical price entry for a product in a specific store.
 * <p>
 * Each entry contains:
 * <ul>
 *   <li>The associated product and store</li>
 *   <li>The price value and currency</li>
 *   <li>The date the price was recorded</li>
 * </ul>
 * This entity is used to track price evolution over time for product comparisons and history views.
 */
@Entity
public class PriceEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Store store;
    private Double price;
    private String currency;
    private LocalDate data;

    public PriceEntry() {
    }

    public PriceEntry(Product product, Store store, double price, String currency, LocalDate data) {
        this.product = product;
        this.store = store;
        this.price = price;
        this.currency = currency;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Store getStore() {
        return store;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PriceEntry{" +
                "id=" + id +
                ", product=" + product +
                ", store=" + store +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", data=" + data +
                '}';
    }
}
