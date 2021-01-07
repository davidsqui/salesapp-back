package com.dasc.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dasc.model.Product;
import com.dasc.repository.ProductRepository;
import com.dasc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {

		return productRepository.findAll(pageable);
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> op = productRepository.findById(id);
		return op.isPresent() ? op.get() : new Product();
	}

	@Override
	public Product save(Product product) {

		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {

		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);

	}

}
