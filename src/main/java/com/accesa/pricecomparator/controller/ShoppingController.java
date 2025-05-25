package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.dto.BasketItemDto;
import com.accesa.pricecomparator.dto.BasketItemWithPriceDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.model.intermediate.PriceOption;
import com.accesa.pricecomparator.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/shopping")
public class ShoppingController {
    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    /**
     * TASK: Daily shopping basket monitoring
     * Calculates the best price for each product in the user's basket across all available stores.
     * <p>
     * The result groups products by store and selects the cheapest available price per product.
     * Discounts are applied if valid at the time of evaluation.
     *
     * @param basket list of products with name and quantity
     * @return map where each key is a store name and the value is a list of basket items with final price per store
     */
    @PostMapping("/basket")
    public ResponseEntity<Map<String, List<BasketItemWithPriceDto>>> getProductList(@RequestBody List<BasketItemDto> basket){
        return ResponseEntity.ok(shoppingService.getBasket(basket));
    }
}
