package com.dianabol.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.api.mkm.exceptions.MkmException;
import org.api.mkm.modele.Expansion;
import org.api.mkm.services.GameService;
import org.api.mkm.services.ProductServices;
import org.api.mkm.tools.MkmAPIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianabol.Backend.repo.ExpansionRepo;
import com.dianabol.Backend.repo.ProductRepo;

@Component
public class ExpansionService {

	private static final int MTG_GAME_ID = 1;
	ProductServices prodServices;
	@Autowired
	ProductRepo prodRepo;
	@Autowired
	ExpansionRepo expRepo;
	@Autowired
	ProductService prodService;
	
	GameService  gameService;

	public static final int M14_EXP_ID = 1449;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	public ExpansionService() {
		
		super();
		try {
			MkmAPIConfig.getInstance().init("L5xKR5b806xSqX8KgMZTkoY6eYrYOadS", "cpWhKWYTdXj87TcM84W7CsGtLOktyapM", "tLO1roHEsyrn97dS7Ude1S5Jy2izfO3g", "OwrNrSfY6OqBp5k0");
		} catch (MkmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prodServices = new ProductServices();//find products
		gameService = new GameService();//find products
		logger.debug("ExpansionService class instantiated");		
	}
	
	public Boolean persistPioneerExpansions() throws IOException {
		List<Expansion> listaPioneer= getPioneerExpansions();
		for(Expansion expa: listaPioneer) {
			prodService.AddExpansionToDB(expa.getIdExpansion());
		}
		
		return true;
		
	}
	
	
	public List<Expansion> getPioneerExpansions() throws IOException {
		List  <Expansion> expansionlist= gameService.listExpansion(MTG_GAME_ID);
		List  <Expansion> expansionlistUpdated = new ArrayList<Expansion>();
		for(Expansion expa: expansionlist) {
			if(expa.getIdExpansion()>=M14_EXP_ID) {
				expansionlistUpdated.add(expa);
			}
		}
		
		
		return expansionlistUpdated;
	}
	
	public boolean checkExpAlreadyExists(int idExpansion) {
		
		Optional<com.dianabol.Backend.model.Expansion> exp = expRepo.findById(idExpansion);
		return exp.isPresent();
		
	}

	/**
	 * metodo que devuelve la expansion por id checkeando la api de mkm
	 * @param idExpansion
	 * @return
	 */
	public com.dianabol.Backend.model.Expansion findExpansionById(int idExpansion) {
		//TODO
		return null;
	}

	public void persistExpansion(Integer idExpansion, String expansionName) {
		com.dianabol.Backend.model.Expansion exp= new com.dianabol.Backend.model.Expansion();
		exp.setIdExpansion(idExpansion);
		exp.setEnName(expansionName);
		expRepo.save(exp);
	}
	
}
