package com.dasc.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dasc.constants.ProductConstant;
import com.dasc.exception.ApiException;
import com.dasc.model.Product;
import com.dasc.model.ProductStatus;
import com.dasc.model.Sale;
import com.dasc.model.SaleDetail;
import com.dasc.repository.ProductRepository;
import com.dasc.repository.ProductStatusRepository;
import com.dasc.repository.SaleRepository;
import com.dasc.service.SaleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		Optional<Sale> saleOptional = saleRepository.findById(id);

		if (!saleOptional.isPresent()) {
			log.info(String.format("No se encontró la venta %s", id));
			throw new ApiException(String.format("No se encontró la venta %s", id));
		}

		return saleOptional.get();
	}

	@Override
	@Transactional
	public Sale save(Sale sale) {
		ProductStatus statusSold = productStatusReporsitory.findById(ProductConstant.PRODUCT_STATUS_SOLD_ID).get();

		sale.getDetails().forEach(detail -> {

			Optional<Product> productToSell = productRepository.findById(detail.getProduct().getId());

			if (!productToSell.isPresent()) {
				throw new ApiException(String.format("El producto %s no existe", detail.getProduct().getId()));
			}

			if (productToSell.get().getStatus().getId() == ProductConstant.PRODUCT_STATUS_STOLEN_ID
					|| productToSell.get().getStatus().getId() == ProductConstant.PRODUCT_STATUS_LOST_ID
					|| productToSell.get().getStatus().getId() == ProductConstant.PRODUCT_STATUS_SOLD_ID) {
				throw new ApiException("El producto no está disponible");
			}

			double subTotal = detail.getAmount() * productToSell.get().getPrice();

			detail.setSubTotal(subTotal);
			detail.setSale(sale);
			detail.setProduct(productToSell.get());

			detail.getProduct().setStatus(statusSold);
			productRepository.updateStatus(detail.getProduct().getId(), statusSold);
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
