package com.dasc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasc.model.Sale;
import com.dasc.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	private SaleService saleService;

	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	@GetMapping
	private ResponseEntity<Page<Sale>> findAll(Pageable pageable) {
		Page<Sale> salePage = saleService.findAll(pageable);
		return new ResponseEntity<>(salePage, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Sale> findById(@PathVariable("id") Integer id) {
		Sale sale = saleService.findById(id);
		return new ResponseEntity<>(sale, HttpStatus.OK);
	}

	@PostMapping
	private ResponseEntity<Object> save(@RequestBody Sale sale) {
		Sale savedSale = saleService.save(sale);
		return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
	}

	@PutMapping
	private ResponseEntity<Sale> update(@RequestBody Sale sale) {
		Sale updatedSale = saleService.update(sale);
		return new ResponseEntity<>(updatedSale, HttpStatus.OK);
	}

}
