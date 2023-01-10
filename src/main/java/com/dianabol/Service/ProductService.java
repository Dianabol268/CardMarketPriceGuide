package com.dianabol.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.api.mkm.exceptions.MkmException;
import org.api.mkm.modele.Expansion;
import org.api.mkm.modele.Product;
import org.api.mkm.services.ProductServices;
import org.api.mkm.tools.MkmAPIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianabol.Backend.model.PriceGuide;
import com.dianabol.Backend.repo.ExpansionRepo;
import com.dianabol.Backend.repo.PriceGuideRepo;
import com.dianabol.Backend.repo.ProductRepo;

@Component
public class ProductService {
		
	ProductServices prodServices;
	//repos
	@Autowired
	ProductRepo prodRepo;
	@Autowired
	PriceGuideRepo pgRepo;
	
	@Autowired
	ExpansionService expService;
	@Autowired
	ExpansionRepo expRepo;
	private Logger logger = LogManager.getLogger(this.getClass());
	
		public ProductService() {
			super();
			try {
				MkmAPIConfig.getInstance().init("L5xKR5b806xSqX8KgMZTkoY6eYrYOadS", "cpWhKWYTdXj87TcM84W7CsGtLOktyapM", "tLO1roHEsyrn97dS7Ude1S5Jy2izfO3g", "OwrNrSfY6OqBp5k0");
			} catch (MkmException e) {
				logger.error("error creating ProductService + "+ e.getMessage());
			}
			prodServices = new ProductServices();//find products
			logger.debug("ProductService class instantiated");
		}
	
		/**
		 * metodo que te persiste la lista de objetos a persistir de una lista de productos por product id
		 * @param productID
		 * @throws IOException 
		 */
		public List<PriceGuide> persistPriceGuideFromProductList(List<Integer> productIDList) throws IOException {
			
			List<PriceGuide> listaReturn= new ArrayList<PriceGuide>();
			for(int prodid : productIDList) {
				
				Product prodMkm = getProductFromMKM(prodid);
				org.api.mkm.modele.PriceGuide mkmPric = prodMkm.getPriceGuide();
				
				//obtenemos el producto tambien de nuestra bd para referenciarlo
				Optional<com.dianabol.Backend.model.Product> prodDBop = prodRepo.findById(prodid);
				com.dianabol.Backend.model.Product prodDB = null;
				if(prodDBop!= null ) {
					prodDB = prodDBop.get();
				}
				if(mkmPric!=null && prodDB!= null) {
					PriceGuide pg = new PriceGuide();
					pg.setAVG(mkmPric.getAVG());
					pg.setDate(LocalDate.now());
					pg.setLOW(mkmPric.getLOW());
					pg.setLOWEX(mkmPric.getLOWEX());
					pg.setLOWFOIL(mkmPric.getLOWFOIL());
					//para el producto, tenemos que usar el de nuestra bd
					pg.setProduct(prodDB);
					pg.setSELL(mkmPric.getSELL());
					pg.setTREND(mkmPric.getTREND());
					listaReturn.add(pg);
				}
			}
			pgRepo.saveAll(listaReturn);
			return listaReturn;
			
		}
		
		/**
		 * metodo que pasandole un productId te devuelve la informacion del product
		 * @param productId
		 * @return
		 * @throws IOException
		 */
		public Product getProductFromMKM(int productId) throws IOException {
			Product productOpt = prodServices.getProductById(productId);			
			
			return productOpt;
		}

	public void getInfo() throws IOException {
		Product productOpt = prodServices.getProductById(productId);

		return productOpt;
	}
		
		public void AddExpansionToDB(Integer idExpansion) throws IOException {
		  //first we have to check the expansion isnt already added
		  if(expService.checkExpAlreadyExists(idExpansion)) {
			  //if already exist we dont create anything
			  logger.debug("Expansion id: " + idExpansion.toString() + " already exists so we are ot adding to db again");
		  }
		  else{
			  logger.debug("Expansion id: " + idExpansion + " not exists in db... creating");
			  //adding expansion
			  //in order to search we only need to fill the ExpansionID
			  Expansion exp = new Expansion();
			  exp.setIdExpansion(idExpansion);
			  
			  //adding products from expansion
			  List<Product>  li= obtainProductsFromExpansion(exp);
			  if(!li.isEmpty()) {
				  expService.persistExpansion(idExpansion, li.get(0).getExpansionName());
				  persistProducts(li);
			  }
			
		  }
		  
		  
		  
		}

	private void persistProducts(List<Product> li) {

		List listaEntity = new ArrayList<com.dianabol.Backend.model.Product>(); 
		for(Product product: li) {
			//transformamos el producto a nuestra entity
			com.dianabol.Backend.model.Product prodEntity = new com.dianabol.Backend.model.Product();
			prodEntity.setEnName(product.getEnName());
			prodEntity.setExpansionName(product.getExpansionName());
			prodEntity.setNumber(product.getNumber());
			prodEntity.setRarity(product.getRarity());
			prodEntity.setIdProduct(product.getIdProduct());
			listaEntity.add(prodEntity);			
			logger.debug("Product saved: " + product.getIdProduct());		
		}
		prodRepo.saveAll(listaEntity);
		logger.debug("persisted data successfull");
		
	}


	public List<org.api.mkm.modele.Product> obtainProductsFromExpansion(org.api.mkm.modele.Expansion expansion) throws IOException{
		
	
			return prodServices.getProductByExpansion(expansion);
	
		
	}
}
