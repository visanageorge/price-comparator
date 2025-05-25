package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.dto.PriceAlertDto;
import com.accesa.pricecomparator.dto.PriceHistoryDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.repository.PriceEntryRepository;
import com.accesa.pricecomparator.service.PriceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-entry")
public class PriceEntryController {
    private final PriceEntryService priceEntryService;

    @Autowired
    public PriceEntryController(PriceEntryService priceEntryService) {
        this.priceEntryService = priceEntryService;
    }

    /**
     * TASK: Dynamic price history graphs filterable by store, product category, or brand
     * Retrieves historical price data, optionally filtered by store, brand, and/or category.
     * <p>
     * If any of the parameters are omitted (null), they are ignored in the filtering.
     * The result includes product and store info, price, and the date the price was recorded.
     *
     * @param store    optional store name filter
     * @param brand    optional product brand filter
     * @param category optional product category filter
     * @return list of historical price entries matching the provided filters
     */
    @GetMapping("/price-history")
    public ResponseEntity<List<PriceHistoryDto>> getPriceHistoryFiltered(@RequestParam(required = false) String store, @RequestParam(required = false) String brand, @RequestParam(required = false) String category){
        return ResponseEntity.ok(priceEntryService.getPriceHistoryFiltered(store, brand, category));
    }


}
