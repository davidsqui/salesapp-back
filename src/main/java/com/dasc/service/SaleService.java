package com.dasc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dasc.model.Sale;

public interface SaleService {

	Page<Sale> findAll(Pageable pageable);

	Sale findById(Integer id);

	Sale save(Sale sale);

	Sale update(Sale sale);

}
