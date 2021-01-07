package com.dasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasc.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
