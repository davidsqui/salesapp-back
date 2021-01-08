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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "sale_details")
@Data
public class SaleDetail implements Serializable {

	private static final long serialVersionUID = 7611268654848087767L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sale_id", nullable = false, foreignKey = @ForeignKey(name = "fk_detail_sale"))
	private Sale sale;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_detail_product"))
	private Product product;

	private Integer amount;

	private Double subTotal;

}
