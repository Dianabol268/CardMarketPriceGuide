package com.dianabol.Backend.model;

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
public class Product {

	

	@Id    
	private int idProduct;
	
	@Column
	private String enName;
	
	@Column
	private String number;
	
	@Column
	private String expansionName;
	
	@Column
	private String rarity;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PriceGuide> PriceGuide;
	
	
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if( number== null){
			this.number = "";
		}
		this.number = number;
	}

	public String getExpansionName() {
		return expansionName;
	}

	public void setExpansionName(String expansionName) {
		this.expansionName = expansionName;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
//	@Column
////	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<PriceGuide> priceGuide;
}
