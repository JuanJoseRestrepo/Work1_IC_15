package com.icesi.taller1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.icesi.taller1.model.BasicInfo;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.service.CountryregionService;
import com.icesi.taller1.service.SalestaxrateService;
import com.icesi.taller1.service.StateprovinceService;

@RestController
public class AdminRestController {

	private CountryregionService countryregionService;
	private SalestaxrateService salestaxrateService;
	private StateprovinceService stateprovinceService;
	@Autowired
	public AdminRestController(CountryregionService countryregionService, SalestaxrateService salestaxrateService,
			StateprovinceService stateprovinceService) {
		this.countryregionService = countryregionService;
		this.salestaxrateService = salestaxrateService;
		this.stateprovinceService = stateprovinceService;
	}
	
	//COUNTRYREGION
	//--------------------------------------------------------------------------------------------------
	

	@RequestMapping(value = "/countries/{id}", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getCountryregion(@PathVariable(value = "id") Integer id){
		Countryregion cr = countryregionService.findById(id).get();
		return new ResponseEntity<Countryregion>(cr, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<Countryregion> getAllCountryregion() {
		List<Countryregion> countries = (List<Countryregion>) countryregionService.findAll();
		return new ResponseEntity(countries, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.POST)
	public ResponseEntity<Countryregion> createCountryregion(@Validated(BasicInfo.class) @RequestBody Countryregion cr){
		countryregionService.save(cr);
		return new ResponseEntity<Countryregion>(cr, HttpStatus.OK);
	}
	
	@PutMapping("/countries/{id}")
	public ResponseEntity<Countryregion> updateCountryregion(@Validated(BasicInfo.class) @RequestBody Countryregion cr){
		
		countryregionService.update(cr);
		
		return ResponseEntity.ok(cr);
	}
	
	
	
	//SALESTAXRATE
	//--------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
	public ResponseEntity<Salestaxrate> getSalestaxrate(@PathVariable(value = "id") Integer id){
		Salestaxrate sr = salestaxrateService.findById(id).get();
		return new ResponseEntity<Salestaxrate>(sr, HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public ResponseEntity<Salestaxrate> getAllSalestaxrate() {
		List<Salestaxrate> salestaxrates = (List<Salestaxrate>) salestaxrateService.findAll();
		return new ResponseEntity(salestaxrates, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	public ResponseEntity<Salestaxrate> createSalestaxrate(@Validated(BasicInfo.class) @RequestBody Salestaxrate sr){
		
		salestaxrateService.saveSalestaxrate(sr);
		
		return new ResponseEntity<Salestaxrate>(sr, HttpStatus.OK);
	}
	
	@PutMapping("/sales/{id}")
	public ResponseEntity<Salestaxrate> updateSalestaxrate(@Validated(BasicInfo.class) @RequestBody Salestaxrate sr){
		
		salestaxrateService.update(sr, sr.getStateprovince().getStateprovinceid());
		
		return ResponseEntity.ok(sr);
	}
	
	//STATEPROVINCE Only one method for call
	//----------------------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ResponseEntity<Stateprovince> getAllStatesprovinces() {
		List<Stateprovince> stateprovinces = (List<Stateprovince>) stateprovinceService.findAll();
		return new ResponseEntity(stateprovinces, HttpStatus.OK);
	}
}
