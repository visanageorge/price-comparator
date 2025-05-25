package com.accesa.pricecomparator.repository;

import com.accesa.pricecomparator.dto.ProductRecommendationDto;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.model.intermediate.PriceOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * Retrieves price options for a list of product names, including any active discounts on a specific date.
     * <p>
     * Each result includes the product, store, price, and discount percentage (0 if no active discount).
     * Note: The expression CAST('2025-05-08' AS DATE) is used as a static replacement for CURRENT_DATE in order to simulate a specific evaluation date.
     * @param productNames list of product names (case-insensitive) to search for
     * @return list of PriceOption objects combining product, store, price, and discount data
     */
    @Query(value = """
           SELECT new com.accesa.pricecomparator.model.intermediate.PriceOption(p.productId, p.productName, s.storeName, pe.price, coalesce(d.percentageOfDiscount, 0))
           FROM PriceEntry pe
           JOIN Product p ON p.productId = pe.product.productId
           JOIN Store s ON s.storeName = pe.store.storeName
           LEFT JOIN Discount d ON pe.product.productId = d.product.productId AND pe.store.storeName = d.store.storeName
           AND CAST('2025-05-08' AS DATE) BETWEEN d.startDate AND d.endDate
           WHERE LOWER(p.productName) IN :productNames
           """)
    List<PriceOption> getProductsFromBasket(@Param("productNames") List<String> productNames);

    /**
     * Retrieves product recommendations matching a given name (case-insensitive),
     * using the most recent price data per product.
     * <p>
     * The final price is dynamically calculated: if a valid discount is active today (`CURRENT_DATE`),
     * it applies the discount; otherwise, the regular price is used.
     * <p>
     * Results are returned as DTOs containing product info, adjusted price, and store name.
     *
     * @param productName partial product name to filter by (case-insensitive)
     * @return list of recommended products with current price and store information
     */
    @Query("""
        SELECT new com.accesa.pricecomparator.dto.ProductRecommendationDto(
        p.productId,
        p.productName,
        p.productQuantity,
        p.productUnit,
        CAST((CASE WHEN d IS NOT NULL AND CURRENT_DATE BETWEEN d.startDate AND d.endDate THEN pe.price*(1.0-d.percentageOfDiscount*1.0/100.0) ELSE pe.price END) AS double),
        s.storeName)
        FROM PriceEntry pe
        JOIN Product p ON p.productId = pe.product.productId
        JOIN Store s ON s.storeName = pe.store.storeName
        LEFT JOIN Discount d ON d.product.productId = p.productId AND d.store.storeName = s.storeName AND CURRENT_DATE BETWEEN d.startDate AND d.endDate
        WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))
            AND pe.data = (SELECT MAX(pe2.data) FROM PriceEntry pe2)
        """)
    List<ProductRecommendationDto>getProductRecommendations(@Param("productName") String productName);

    /**
     * Retrieves product recommendations filtered by category (case-insensitive),
     * using the most recent price data and applying active discounts if available.
     * <p>
     * The final price is calculated based on whether the product has a valid discount
     * on the current day (`CURRENT_DATE`). Results include product details, adjusted price, and store.
     *
     * @param productCategory partial category name to filter by
     * @return list of recommended products within the category, with current price and store info
     */
    @Query("""
        SELECT new com.accesa.pricecomparator.dto.ProductRecommendationDto(
        p.productId,
        p.productName,
        p.productQuantity,
        p.productUnit,
        CAST((CASE WHEN d IS NOT NULL AND CURRENT_DATE BETWEEN d.startDate AND d.endDate THEN pe.price*(1.0-d.percentageOfDiscount*1.0/100.0) ELSE pe.price END) AS double),
        s.storeName)
        FROM PriceEntry pe
        JOIN Product p ON p.productId = pe.product.productId
        JOIN Store s ON s.storeName = pe.store.storeName
        LEFT JOIN Discount d ON d.product.productId = p.productId AND d.store.storeName = s.storeName AND CURRENT_DATE BETWEEN d.startDate AND d.endDate
        WHERE LOWER(p.productCategory) LIKE LOWER(CONCAT('%', :productCategory, '%'))
            AND pe.data = (SELECT MAX(pe2.data) FROM PriceEntry pe2)
        """)
    List<ProductRecommendationDto>getCategoryRecommendations(@Param("productCategory") String productCategory);


}
