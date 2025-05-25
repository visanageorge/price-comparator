package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.dto.BasketItemDto;
import com.accesa.pricecomparator.dto.BasketItemWithPriceDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.model.intermediate.PriceOption;
import com.accesa.pricecomparator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Service class for managing shopping basket functionalities.
 * Offers methods to evaluate and calculate best price combinations for products across stores.
 */
@Service
public class ShoppingService {
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns a map of the best price option per product, grouped by store.
     *
     * @param basket list of basket items containing product names and quantities
     * @return a map where the key is the store name and the value is the list of priced items
     */
    public Map<String, List<BasketItemWithPriceDto>> getBasket(List<BasketItemDto> basket){
        // Extract product names from basket
        List<String> basketProductNames = basket.stream()
                .map(BasketItemDto::getProductName)
                .collect(Collectors.toList());

        // Map each product name to its quantity
        Map<String, Integer> productToQuantity = basket.stream()
                .collect(Collectors.toMap(
                       BasketItemDto::getProductName,
                       BasketItemDto::getQuantity

                ));
        // Retrieve all price options from the database for the listed product names
        List<PriceOption> priceOptions = productRepository.getProductsFromBasket(basketProductNames);

        // From all price options, choose the one with the lowest final price for each product
        Map<String, PriceOption> bestOptionsPerProduct = priceOptions.stream()
                .collect(Collectors.toMap(
                        PriceOption::getProductName,
                        po->po,
                        BinaryOperator.minBy(Comparator.comparing(PriceOption::getFinalPrice))
                ));

        // Create the final map grouped by store
        Map<String, List<BasketItemWithPriceDto>> basketItemsWithPrice = new HashMap<>();

        for(Map.Entry<String, PriceOption> entry: bestOptionsPerProduct.entrySet()){
            // Add key with empty list if key is absent
            basketItemsWithPrice.putIfAbsent(entry.getValue().getStoreName(), new ArrayList<>());
            // Retrieve the list for a specific key
            List<BasketItemWithPriceDto> priceList = basketItemsWithPrice.get(entry.getValue().getStoreName());
            // Build the DTO for each best-priced item
            if(priceList != null){
                BasketItemWithPriceDto basketItemWithPriceDto = new BasketItemWithPriceDto(entry.getValue().getProductName(),
                        productToQuantity.get(entry.getValue().getProductName()), entry.getValue().getFinalPrice());
                priceList.add(basketItemWithPriceDto);
            }
        }

        return basketItemsWithPrice;
    }
}
