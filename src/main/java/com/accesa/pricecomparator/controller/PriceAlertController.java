package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.dto.PriceAlertDto;
import com.accesa.pricecomparator.dto.PriceAlertResultedProductDto;
import com.accesa.pricecomparator.service.PriceAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class PriceAlertController {
    private final PriceAlertService priceAlertService;

    @Autowired
    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    /**
     * TASK: Custom alert price
     * Creates a new price alert for a specific product.
     * The alert triggers when the product's price drops below or equals the target price.
     *
     * @param priceAlertDto contains the product ID and the target price threshold
     * @return HTTP 200 response with confirmation or existing alert info
     */
    @PostMapping("/set-price-alert")
    public ResponseEntity<?> createAlert(@RequestBody PriceAlertDto priceAlertDto){
        return ResponseEntity.ok(priceAlertService.createAlert(priceAlertDto));
    }

    /**
     * Deletes an existing price alert for the given product.
     *
     * @param productId the ID of the product whose alert should be removed
     * @return HTTP 200 response confirming deletion
     */
    @DeleteMapping("/delete-price-alert")
    public ResponseEntity<?> deleteAlert(@RequestParam String productId){
        return ResponseEntity.ok(priceAlertService.deleteAlert(productId));
    }

    /**
     * Retrieves all active price alerts that have been triggered.
     *
     * @return list of products that satisfy the alert conditions
     */
    @GetMapping("/get-alerts")
    public ResponseEntity<List<PriceAlertResultedProductDto>> getAlerts(){
        return ResponseEntity.ok(priceAlertService.getPriceAlerts());
    }
}
