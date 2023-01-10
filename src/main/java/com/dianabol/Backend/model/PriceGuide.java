package com.dianabol.Backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PriceGuide {

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRICEGUIDE") 
	private int id;
	

	@Column
	private Double SELL;
	
	@Column
	private Double LOW;
	
	@Column
	private Double LOWEX;
	
	@Column
	private Double LOWFOIL;
	
	@Column
	private Double AVG;
	
	@Column
	private Double TREND;
	
	@Column
	private Double TRENDFOIL;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getSELL() {
		return SELL;
	}

	public void setSELL(Double sELL) {
		SELL = sELL;
	}

	public Double getLOW() {
		return LOW;
	}

	public void setLOW(Double lOW) {
		LOW = lOW;
	}

	public Double getLOWEX() {
		return LOWEX;
	}

	public void setLOWEX(Double lOWEX) {
		LOWEX = lOWEX;
	}

	public Double getLOWFOIL() {
		return LOWFOIL;
	}

	public void setLOWFOIL(Double lOWFOIL) {
		LOWFOIL = lOWFOIL;
	}

	public Double getAVG() {
		return AVG;
	}

	public void setAVG(Double aVG) {
		AVG = aVG;
	}

	public Double getTREND() {
		return TREND;
	}

	public void setTREND(Double tREND) {
		TREND = tREND;
	}

	public Double getTRENDFOIL() {
		return TRENDFOIL;
	}

	public void setTRENDFOIL(Double tRENDFOIL) {
		TRENDFOIL = tRENDFOIL;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(nullable = false)
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	
	
}
