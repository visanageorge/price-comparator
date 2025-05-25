package com.accesa.pricecomparator.model;

import jakarta.persistence.*;

/**
 * JPA entity representing a store, uniquely identified by name.
 * Used as a relation target for discounts and price entries.
 */
@Entity
public class Store {

    @Id
    private String storeName;

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Store() {

    }

    public String getStoreName() {
        return storeName;
    }


    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                '}';
    }
}
