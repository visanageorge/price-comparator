package com.accesa.pricecomparator.repository;

import com.accesa.pricecomparator.dto.PriceHistoryDto;
import com.accesa.pricecomparator.model.PriceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceEntryRepository extends JpaRepository<PriceEntry, Long> {
    /**
     * Returns historical price entries as DTOs, filtered optionally by store, brand, and category.
     *<p>
     * Includes product and store details, as well as the price and date.
     * The results are ordered by store, brand, and category for structured display.
     *
     * @param store    optional filter by store name (nullable)
     * @param brand    optional filter by product brand (nullable)
     * @param category optional filter by product category (nullable)
     * @return list of PriceHistoryDto entries matching the filters
     */
    @Query("""
        SELECT new com.accesa.pricecomparator.dto.PriceHistoryDto(p.productId, p.productName,
        s.storeName, p.productBrand, p.productCategory, pe.price, pe.data)
        FROM PriceEntry pe
        JOIN Product p ON p.productId = pe.product.productId
        JOIN Store s ON s.storeName = pe.store.storeName
        LEFT JOIN Discount d ON d.product.productId = p.productId AND d.store.storeName = s.storeName AND pe.data BETWEEN d.startDate AND d.endDate
        WHERE (:store IS NULL OR s.storeName = :store)
        AND (:brand IS NULL OR p.productBrand = :brand)
        AND (:category IS NULL OR p.productCategory = :category)
        ORDER BY s.storeName, p.productBrand, p.productCategory
    """)
    List<PriceHistoryDto> filteredData(@Param("store") String store, @Param("brand") String brand, @Param("category") String category);


}
