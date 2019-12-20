package com.dianabol.Backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dianabol.Backend.model.Product;


public interface ProductRepo extends JpaRepository<Product, Integer>{

	
}
