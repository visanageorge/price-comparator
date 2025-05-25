package com.accesa.pricecomparator.data;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Abstract base class for defining CSV import logic for specific stores.
 * Concrete implementations provide the store name and inherit product and discount import functionality.
 */
public abstract class CsvImporter {
    private final CsvReader csvReader;

    @Autowired
    protected CsvImporter(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    protected abstract String getStore();
    public void importData() throws IOException {
        importProducts(getStore());
        importDiscounts(getStore());
    };

    private void importProducts(String store) throws IOException {
        ArrayList<String> products = csvReader.csvFileNameLoaderFromDirectory("products", store);
        for(String file: products){
            csvReader.loadProductsFromCsv(file);
        }
    }

    private void importDiscounts(String store) throws IOException {
        ArrayList<String> discounts = csvReader.csvFileNameLoaderFromDirectory("discounts", store);
        for(String file: discounts){
            csvReader.loadDiscountsFromCsv(file);
        }
    }

}
