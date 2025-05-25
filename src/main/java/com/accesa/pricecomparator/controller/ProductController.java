package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.dto.ProductRecommendationDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * TASK: Product substitutes and recommendations for product
     * Returns product recommendations matching the given product name.
     * <p>
     * The response includes the most recent price per store, adjusted if a valid discount is available.
     *
     * @param productName partial or full product name (case-insensitive)
     * @return list of recommended products with current price and store information
     */
    @GetMapping("/product-recommendations")
    public List<ProductRecommendationDto> getProductRecommendations(@RequestParam String productName){
        return productService.getProductRecommendations(productName);
    }

    /**
     * TASK: Product substitutes and recommendations for category
     * Returns product recommendations based on the given product category.
     * <p>
     * Results are filtered by category and include the latest prices per store,
     * adjusted for active discounts when applicable.
     *
     * @param productCategory category name to filter products (case-insensitive)
     * @return list of recommended products in the given category with price and store info
     */
    @GetMapping("/category-recommendations")
    public List<ProductRecommendationDto> getCategoryRecommendations(@RequestParam String productCategory){
        return productService.getCategoryRecommendations(productCategory);
    }
}
