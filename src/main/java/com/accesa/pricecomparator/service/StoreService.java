package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store save(Store store){
        return storeRepository.save(store);
    }
}
