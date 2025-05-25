package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.service.DiscountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }


    /**
     * Display all time discounts for a specific date
     * @param date
     * Returns active discounts on date
     */
    @GetMapping("/on-date")
    public ResponseEntity<List<Discount>> getActiveDiscountOnDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(discountService.getActiveDiscountFromDate(date));
    }

    /**
     * TASK: Display newly added discounts in the last 24 hours.
     * Returns all Discount entries added today or yesterday.
     */
    @GetMapping("/last-24-hours")
    public  ResponseEntity<List<Discount>> getLast24HoursDiscounts(){
        return  ResponseEntity.ok(discountService.getNewlyAddedDiscountFromLast24Hours());
    }

    /**
     * TASK: Display best discounts across all tracked stores
     * Returns all best Discount entries for current date
     */
    @GetMapping("/best-discounts")
    public ResponseEntity<List<Discount>> getBestDiscounts(){
        return ResponseEntity.ok(discountService.getBestDiscountsFromToday());
    }
}
