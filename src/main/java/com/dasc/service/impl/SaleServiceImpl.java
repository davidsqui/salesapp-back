package com.dasc.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dasc.model.Sale;
import com.dasc.repository.SaleRepository;
import com.dasc.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

	private SaleRepository saleRepository;

	public SaleServiceImpl(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	@Override
	public Page<Sale> findAll(Pageable pageable) {

		return saleRepository.findAll(pageable);
	}

	@Override
	public Sale findById(Integer id) {
		Optional<Sale> op = saleRepository.findById(id);
		return op.isPresent() ? op.get() : new Sale();
	}

	@Override
	public Sale save(Sale sale) {

		return saleRepository.save(sale);
	}

	@Override
	public Sale update(Sale sale) {

		return saleRepository.save(sale);
	}

}
