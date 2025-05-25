package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service layer for managing Discount entities.
 * Exposes methods for saving and retrieving relevant discount data.
 */
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount save(Discount discount){
        return discountRepository.save(discount);
    }

    public List<Discount> getActiveDiscountFromDate(LocalDate discountDate){
        return discountRepository.findActiveDiscountOnDate(discountDate);
    }

    public List<Discount> getNewlyAddedDiscountFromLast24Hours(){
        return discountRepository.findDiscountsAddedLast24Hours(LocalDate.parse("2025-05-08"));
    }

    public List<Discount> getBestDiscountsFromToday(){
        return discountRepository.findBestDiscountForProduct();
    }

}
