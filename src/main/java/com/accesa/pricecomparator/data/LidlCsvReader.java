package com.accesa.pricecomparator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CSV importer for Lidl store.
 * Provides "lidl" identifier to the base CsvImporter class.
 */
@Component
public class LidlCsvReader extends CsvImporter{

    protected LidlCsvReader(CsvReader csvReader) {
        super(csvReader);
    }

    @Override
    protected String getStore() {
        return "lidl";
    }
}
