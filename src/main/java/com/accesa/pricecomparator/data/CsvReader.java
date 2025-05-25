package com.accesa.pricecomparator.data;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.model.PriceEntry;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.service.DiscountService;
import com.accesa.pricecomparator.service.PriceEntryService;
import com.accesa.pricecomparator.service.ProductService;
import com.accesa.pricecomparator.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvReader is responsible for reading product and discount data from CSV files located in the classpath.
 * It parses the input lines and delegates persistence to the appropriate services.
 */
@Component
public class CsvReader{
    private final ProductService productService;
    private final StoreService storeService;
    private final PriceEntryService priceEntryService;
    private final DiscountService discountService;

    @Autowired
    public CsvReader(ProductService productService, StoreService storeService, PriceEntryService priceEntryService, DiscountService discountService) {
        this.productService = productService;
        this.storeService = storeService;
        this.priceEntryService = priceEntryService;
        this.discountService = discountService;
    }

    /**
     * Loads products and their price entries from a CSV file located in /resources/csv/products/.
     * <p>
     * Each row is parsed into a Product and corresponding PriceEntry.
     * The store name is derived from the filename, and the date is extracted from the filename as well.
     *
     * @param file the name of the CSV file (e.g. lidl_2025-05-01.csv)
     */
    public void loadProductsFromCsv(String file) {

        try{
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource("classpath:csv/products/" + file);

            try (InputStream is = resource.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String productLine;
                br.readLine();
                while((productLine = br.readLine()) != null){
                    String[] columns = productLine.split(";");

                    Product product = new Product(columns[0], columns[1], columns[2], columns[3], Double.parseDouble(columns[4]), columns[5]);
                    //Getting store name from csv file name
                    Store store = new Store(file.split("_")[0]);
                    PriceEntry priceEntry = new PriceEntry(product, store, Double.parseDouble(columns[6]), columns[7],  LocalDate.parse(file.split("[_.]")[1]));
                    productService.save(product);
                    storeService.save(store);
                    priceEntryService.save(priceEntry);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Loads discounts from a CSV file located in /resources/csv/discounts/.
     * <p>
     * Each row represents a discount for a product in a store.
     * The store name and discount insertion date are derived from the filename.
     *
     * @param file the name of the discount CSV file (e.g. kaufland_discounts_2025-05-01.csv)
     */
    public void loadDiscountsFromCsv(String file) {

        try{
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource("classpath:csv/discounts/" + file);

            try (InputStream is = resource.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String discountLine;
                br.readLine();
                while((discountLine = br.readLine()) != null){
                    String[] columns = discountLine.split(";");

                    Product product = new Product(columns[0], columns[1], columns[5], columns[2], Double.parseDouble(columns[3]), columns[4]);
                    //Get store name from csv file name
                    Store store = new Store(file.split("_")[0]);
                    Discount discount = new Discount(product, store, LocalDate.parse(columns[6]), LocalDate.parse(columns[7]), Double.parseDouble(columns[8]), LocalDate.parse(file.split("[_.]")[2]));
                    discountService.save(discount);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Retrieves all CSV filenames from a specific directory that match the given store name prefix.
     *
     * @param directoryName the subdirectory under /resources/csv/ (e.g. "products" or "discounts")
     * @param storeName     the prefix of the files to match (e.g. "lidl", "kaufland")
     * @return list of matching filenames
     * @throws IOException if the directory cannot be accessed
     */
    public ArrayList<String> csvFileNameLoaderFromDirectory(String directoryName, String storeName) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:csv/" +  directoryName + "/" + storeName + "*.csv");

        for(Resource resource : resources){
            files.add(resource.getFilename());
        }

        return files;
    }
}
