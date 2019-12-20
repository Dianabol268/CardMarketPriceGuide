package com.dianabol.Backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dianabol.Backend.model.Expansion;
import com.dianabol.Backend.model.Product;

public interface ExpansionRepo extends JpaRepository<Expansion, Integer>{

	
}
