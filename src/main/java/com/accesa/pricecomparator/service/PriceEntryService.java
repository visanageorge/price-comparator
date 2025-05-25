package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.dto.PriceHistoryDto;
import com.accesa.pricecomparator.model.PriceEntry;
import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.repository.PriceEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing price entries.
 * Provides persistence and filtered historical price views.
 */
@Service
public class PriceEntryService {
    private final PriceEntryRepository priceEntryRepository;

    @Autowired
    public PriceEntryService(PriceEntryRepository priceEntryRepository) {
        this.priceEntryRepository = priceEntryRepository;
    }

    public PriceEntry save(PriceEntry priceEntry){
        return priceEntryRepository.save(priceEntry);
    }

    public List<PriceHistoryDto> getPriceHistoryFiltered(String store, String brand, String category){
        return priceEntryRepository.filteredData(store, brand, category);
    }


}
