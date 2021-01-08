package com.dasc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = -2727589912230732261L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private double price;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_status"))
	private ProductStatus status;

}
