package com.dasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasc.model.ProductStatus;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {

}
