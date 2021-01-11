package com.dasc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dasc.model.Product;
import com.dasc.model.ProductStatus;

public interface ProductService {

	Page<Product> findAll(Pageable pageable);

	Product findById(Integer id);

	Product save(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<ProductStatus> findAllStatus();

}
