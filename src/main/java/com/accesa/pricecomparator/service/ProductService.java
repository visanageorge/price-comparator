package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.dto.ProductRecommendationDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service class for interacting with products and computing price recommendations.
 * Sorts returned results by value per unit for better user decision-making.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<ProductRecommendationDto> getProductRecommendations(String productName){
        return productRepository.getProductRecommendations(productName).stream().sorted((Comparator.comparing(ProductRecommendationDto::getValuePerUnit))).toList();
    }

    public List<ProductRecommendationDto> getCategoryRecommendations(String productCategory){
        return productRepository.getCategoryRecommendations(productCategory).stream().sorted((Comparator.comparing(ProductRecommendationDto::getValuePerUnit))).toList();
    }
}
