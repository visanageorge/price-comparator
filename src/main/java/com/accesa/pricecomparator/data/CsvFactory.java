package com.accesa.pricecomparator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Initializes all CsvImporter beans at application startup via CommandLineRunner.
 * Triggers importData() for each configured store.
 */
@Component
public class CsvFactory implements CommandLineRunner {

    private final List<CsvImporter> importers;

    @Autowired
    public CsvFactory(List<CsvImporter> importers) {
        this.importers = importers;
    }

    @Override
    public void run(String... args) throws Exception {
        for(CsvImporter storeCsvReader: importers){
            storeCsvReader.importData();
        }
    }
}
