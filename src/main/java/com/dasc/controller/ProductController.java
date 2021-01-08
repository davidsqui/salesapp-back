package com.dasc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasc.model.Product;
import com.dasc.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	private ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		Page<Product> productPage = productService.findAll(pageable);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	private ResponseEntity<Product> save(@RequestBody Product product) {
		Product savedProduct = productService.save(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@PutMapping
	private ResponseEntity<Product> update(@RequestBody Product product) {
		Product updatedProduct = productService.update(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		productService.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
