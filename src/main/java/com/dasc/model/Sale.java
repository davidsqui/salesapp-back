package com.dasc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class Sale implements Serializable {

	private static final long serialVersionUID = 2274294768834044387L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Double total;

	private LocalDateTime saleDate;

	@OneToMany(mappedBy = "sale", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<SaleDetail> details;

}
