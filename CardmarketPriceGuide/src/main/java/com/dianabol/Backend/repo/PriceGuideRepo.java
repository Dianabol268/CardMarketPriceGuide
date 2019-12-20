package com.dianabol.Backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dianabol.Backend.model.PriceGuide;
import com.dianabol.Backend.model.Product;

public interface PriceGuideRepo extends JpaRepository<PriceGuide, Integer>{

}
