package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.dto.PriceAlertDto;
import com.accesa.pricecomparator.dto.PriceAlertResultedProductDto;
import com.accesa.pricecomparator.model.PriceAlert;
import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.repository.PriceAlertRepository;
import com.accesa.pricecomparator.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service layer for managing price alerts.
 * Supports alert creation, deletion, and fetching matched products.
 */
@Service
public class PriceAlertService {
    private final PriceAlertRepository priceAlertRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PriceAlertService(PriceAlertRepository priceAlertRepository, ProductRepository productRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.productRepository = productRepository;
    }

    public List<PriceAlertResultedProductDto> getPriceAlerts(){
        return priceAlertRepository.listProductsWithAlert();
    }

    /**
     * Creates a new price alert for a given product and target price.
     * <p>
     * The alert is associated with the given product and becomes immediately active.
     * <p>
     * Note: The creation date is statically set to 2025-05-08 to simulate behavior for a fixed point in time,
     * useful for controlled testing and reproducibility.
     *
     * @param priceAlertDto contains the product ID and desired price threshold
     * @return confirmation message after saving the alert
     * @throws RuntimeException if the product ID is invalid
     */
    public String createAlert(PriceAlertDto priceAlertDto){
        Product product = productRepository.findById(priceAlertDto.getProductId()).orElseThrow(() ->new RuntimeException("Product not found!"));
        PriceAlert priceAlert = new PriceAlert();
        priceAlert.setProduct(product);
        priceAlert.setTargetPrice(priceAlertDto.getTargetPrice());
        priceAlert.setCreatedAt(LocalDate.parse("2025-05-08"));
        priceAlert.setActive(true);
        priceAlertRepository.save(priceAlert);
        return "Alert created";
    }

    @Transactional
    public String deleteAlert(String productId){
        priceAlertRepository.deleteAlertByProductId(productId);
        return "Alert deleted";
    }
}
