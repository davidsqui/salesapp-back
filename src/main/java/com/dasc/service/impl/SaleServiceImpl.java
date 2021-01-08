package com.dasc.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dasc.constants.ProductConstant;
import com.dasc.model.ProductStatus;
import com.dasc.model.Sale;
import com.dasc.model.SaleDetail;
import com.dasc.repository.ProductRepository;
import com.dasc.repository.ProductStatusRepository;
import com.dasc.repository.SaleRepository;
import com.dasc.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

	private SaleRepository saleRepository;
	private ProductRepository productRepository;
	private ProductStatusRepository productStatusReporsitory;

	public SaleServiceImpl(SaleRepository saleRepository, ProductRepository productRepository,
			ProductStatusRepository productStatusReporsitory) {
		this.saleRepository = saleRepository;
		this.productRepository = productRepository;
		this.productStatusReporsitory = productStatusReporsitory;
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
	@Transactional
	public Sale save(Sale sale) {
		ProductStatus productStatus = productStatusReporsitory.findById(ProductConstant.PRODUCT_STATUS_SOLD_ID).get();

		sale.getDetails().forEach(detail -> {
			double subTotal = detail.getAmount() * detail.getProduct().getPrice();

			detail.setSubTotal(subTotal);
			detail.setSale(sale);
			detail.getProduct().setStatus(productStatus);
		});

		double total = sale.getDetails().stream().map(SaleDetail::getSubTotal).reduce(0.00, (a, b) -> a + b);

		sale.setTotal(total);
		sale.setSaleDate(LocalDateTime.now());

		return saleRepository.save(sale);
	}

	@Override
	public Sale update(Sale sale) {

		return saleRepository.save(sale);
	}

}
