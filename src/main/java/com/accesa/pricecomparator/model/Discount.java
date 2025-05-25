package com.accesa.pricecomparator.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * JPA entity representing a discount for a product in a store.
 * Includes time validity (start/end), percentage and the date of record addition.
 */
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Store store;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentageOfDiscount;
    private LocalDate addDate;

    public Discount() {
    }

    public Discount(Product product, Store store, LocalDate startDate, LocalDate endDate, Double percentageOfDiscount, LocalDate addDate) {
        this.product = product;
        this.store = store;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageOfDiscount = percentageOfDiscount;
        this.addDate = addDate;
    }

    public Product getProduct() {
        return product;
    }

    public Store getStore() {
        return store;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getPercentageOfDiscount() {
        return percentageOfDiscount;
    }

    public LocalDate getAddDate(){return addDate;}
    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", product=" + product +
                ", store=" + store +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", percentageOfDiscount=" + percentageOfDiscount +
                ", addDate=" + addDate +
                '}';
    }
}
