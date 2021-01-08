package com.dasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dasc.model.Product;
import com.dasc.model.ProductStatus;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Query("UPDATE Product set status = :status where id = :id")
	void updateStatus(@Param("id") int id, ProductStatus status);

}
