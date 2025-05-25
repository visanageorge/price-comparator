package com.accesa.pricecomparator.repository;

import com.accesa.pricecomparator.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("SELECT d FROM Discount d WHERE :discountDate BETWEEN d.startDate AND d.endDate")
    List<Discount> findActiveDiscountOnDate(@Param("discountDate") LocalDate discountDate);

    @Query("SELECT d FROM Discount d WHERE d.addDate = :today")
    List<Discount> findDiscountsAddedLast24Hours(@Param("today") LocalDate today);

    /**
     * Retrieves the best available (i.e., highest percentage) discount for each product,
     * considering only the discounts that are currently active (valid today).
     *<p>
     * Uses a subquery with ROW_NUMBER() to rank discounts per product and selects only
     * the top-ranked discount (RN = 1) for each product.
     *<p>
     * This ensures each product appears only once with its best active discount.
     * Note: The expression CAST('2025-05-08' AS DATE) is used as a static replacement for CURRENT_DATE in order to simulate a specific evaluation date.
     * @return a list of Discount entities representing the best current discount per product
     */
    @Query(value = """
    SELECT * FROM (
        SELECT *, ROW_NUMBER() OVER (
            PARTITION BY PRODUCT_PRODUCT_ID
            ORDER BY PERCENTAGE_OF_DISCOUNT DESC
        ) AS RN
        FROM DISCOUNT
        WHERE CAST('2025-05-08' AS DATE) BETWEEN START_DATE AND END_DATE
    ) AS RANKED
    WHERE RN = 1
    """, nativeQuery = true)
    List<Discount> findBestDiscountForProduct();


}
