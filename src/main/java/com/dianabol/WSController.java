package com.dianabol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.api.mkm.exceptions.MkmException;
import org.api.mkm.modele.Expansion;
import org.api.mkm.modele.Product.PRODUCT_ATTS;
import org.api.mkm.services.ArticleService;
import org.api.mkm.services.CartServices;
import org.api.mkm.services.ProductServices;
import org.api.mkm.tools.MkmAPIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dianabol.Backend.model.Product;
import com.dianabol.Service.ExpansionService;
import com.dianabol.Service.ProductService;




@RestController
public class WSController {

	  @Autowired
	  ProductService prodService;
	  @Autowired
	  ExpansionService expService;
	  
	  private Logger logger = LogManager.getLogger(this.getClass());
	  
	  @GetMapping("/persistirExpa")
	  public Boolean persistExpansion(@RequestParam(value="expansion",required = true) int expansion) {	  
		  try {
			prodService.AddExpansionToDB(expansion);
		} catch (IOException e) {
			logger.error("error: "+ e.getMessage());
		}
		  
	   return true;
	  }
	  
	  @GetMapping("/getPioneerExpas")
	  public List<Expansion> getPioneerExpansion() {	  
		try {
			return expService.getPioneerExpansions();
		}
		 catch (IOException e) {
				logger.error("error: "+ e.getMessage());
				  return null;
			} 
	 
	  }
	  
	  @GetMapping("/persistPioneerExpas")
	  public boolean persistExpansion() {	  
		try {
			return expService.persistPioneerExpansions();
		}
		 catch (IOException e) {
				logger.error("error: "+ e.getMessage());
				  return false;
			} 
	 
	  }

	  @GetMapping("/persistPriceGuideFromProductList")
	  public boolean persistPriceGuideFromProductList(@RequestParam(value="product",required = true) int product) {	  
		try {
			List lista = new ArrayList<Integer>();
			lista.add(product);
			prodService.persistPriceGuideFromProductList(lista);
			return true;
		}
		 catch (IOException e) {
				logger.error("error: "+ e.getMessage());
				  return false;
			} 
	 
	  }
	 
}
