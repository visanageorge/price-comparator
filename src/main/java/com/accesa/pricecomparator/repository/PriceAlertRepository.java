package com.accesa.pricecomparator.repository;

import com.accesa.pricecomparator.dto.PriceAlertResultedProductDto;
import com.accesa.pricecomparator.model.PriceAlert;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> {

    @Modifying
    @Query("DELETE FROM PriceAlert pa WHERE pa.product.productId = :productId")
    void deleteAlertByProductId(@Param("productId") String productId);

    /**
     * Returns products that triggered a user's price alert.
     * For each product with an alert, checks if the most recent price (with discount if valid)
     * is less than or equal to the alert's target price.
     * Only one entry per product-store pair is returned, with info about the discount.
     * Note: The expression CAST('2025-05-08' AS DATE) is used as a static replacement for CURRENT_DATE in order to simulate a specific evaluation date.
     * @return list of matched products with price and discount info
     */
    @Query(value = """
        SELECT new com.accesa.pricecomparator.dto.PriceAlertResultedProductDto(
        p.productId,
        p.productName,
        pe.price,
        (CASE WHEN d IS NOT NULL AND CAST('2025-05-08' AS DATE) BETWEEN d.startDate AND d.endDate THEN CAST(d.percentageOfDiscount AS double) ELSE CAST(0.0 AS double ) END),
        s.storeName,
        (CASE WHEN d IS NOT NULL THEN true ELSE false END))
        FROM PriceAlert pa
        JOIN Product p ON p.productId = pa.product.productId
        JOIN PriceEntry pe ON pe.product.productId = p.productId
        JOIN Store s ON s.storeName = pe.store.storeName
        LEFT JOIN Discount d ON d.product.productId = p.productId AND s.storeName = d.store.storeName AND CAST('2025-05-08' AS DATE) BETWEEN d.startDate AND d.endDate
        WHERE (CASE WHEN d IS NOT NULL AND CAST('2025-05-08' AS DATE) BETWEEN d.startDate AND d.endDate THEN pe.price * (1-d.percentageOfDiscount*1.0/100) ELSE pe.price END) <= pa.targetPrice
           AND pe.data = (SELECT MAX(pe2.data) FROM PriceEntry pe2)
        """)
    List<PriceAlertResultedProductDto> listProductsWithAlert();
}
