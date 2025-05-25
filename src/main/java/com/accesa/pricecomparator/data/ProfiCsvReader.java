package com.accesa.pricecomparator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CSV importer for Profi store.
 * Inherits behavior from CsvImporter and provides store identifier.
 */
@Component
public class ProfiCsvReader extends CsvImporter{


    protected ProfiCsvReader(CsvReader csvReader) {
        super(csvReader);
    }

    @Override
    protected String getStore() {
        return "profi";
    }
}
