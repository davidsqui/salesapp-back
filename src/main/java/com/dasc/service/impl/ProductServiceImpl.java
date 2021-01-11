package com.dasc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dasc.constants.ProductConstant;
import com.dasc.exception.ApiException;
import com.dasc.model.Product;
import com.dasc.model.ProductStatus;
import com.dasc.repository.ProductRepository;
import com.dasc.repository.ProductStatusRepository;
import com.dasc.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ProductStatusRepository productStatusRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductStatusRepository productStatusRepository) {
		this.productRepository = productRepository;
		this.productStatusRepository = productStatusRepository;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {

		return productRepository.findAll(pageable);
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			log.info(String.format("No existe el producto con id: %s ", id));
			throw new ApiException(String.format("No existe el producto con id: %s ", id));
		}
		return productOptional.get();
	}

	@Override
	public Product save(Product product) {

		ProductStatus productStatus = productStatusRepository.findById(ProductConstant.PRODUCT_STATUS_AVAILABLE_ID)
				.get();

		product.setStatus(productStatus);

		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {

		this.findById(product.getId());

		Optional<ProductStatus> status = productStatusRepository.findById(product.getStatus().getId());

		if (!status.isPresent()) {
			log.info("El estado del producto no es válido");
			throw new ApiException("El estado del producto no es válido");
		}

		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		this.findById(id);

		productRepository.deleteById(id);

	}

	@Override
	public List<ProductStatus> findAllStatus() {

		return productStatusRepository.findAll();
	}

}
