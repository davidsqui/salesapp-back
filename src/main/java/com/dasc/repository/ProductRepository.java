package com.dasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasc.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
