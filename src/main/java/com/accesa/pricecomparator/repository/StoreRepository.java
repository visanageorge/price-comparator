package com.accesa.pricecomparator.repository;

import com.accesa.pricecomparator.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
}
