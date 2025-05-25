package com.accesa.pricecomparator.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CSV importer for Kaufland store.
 * Defines "kaufland" as the identifier for CSV file retrieval.
 */
@Component
public class KauflandCsvReader extends CsvImporter{


    protected KauflandCsvReader(CsvReader csvReader) {
        super(csvReader);
    }

    @Override
    protected String getStore() {
        return "kaufland";
    }
}
