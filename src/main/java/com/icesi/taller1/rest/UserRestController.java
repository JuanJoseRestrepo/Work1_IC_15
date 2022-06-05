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

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.BasicInfo;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.service.AddressService;
import com.icesi.taller1.service.CountryregionService;
import com.icesi.taller1.service.StateprovinceService;

@RestController
public class UserRestController {

	
	private StateprovinceService stateprovinceService;
	private CountryregionService countryregionService;
	private AddressService addressService;
	
	@Autowired
	public UserRestController(StateprovinceService stateprovinceService, CountryregionService countryregionService,
			AddressService addressService) {
		this.stateprovinceService = stateprovinceService;
		this.countryregionService = countryregionService;
		this.addressService = addressService;
	}
	
	//Province
	//---------------------------------------------------------------------------------
	
	@RequestMapping(value="/provinces/{id}", method=RequestMethod.GET)
	public ResponseEntity<Stateprovince> getStateprovince(@PathVariable(value="id") Integer id){
		Stateprovince sp = stateprovinceService.findById(id).get();
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.OK);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/provinces", method=RequestMethod.GET)
	public ResponseEntity<Stateprovince> listStateprovinces(){
		List<Stateprovince> entities = ((List<Stateprovince>) stateprovinceService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/provinces", method =RequestMethod.POST)
	public ResponseEntity<Stateprovince> createStateProvince(@Validated(BasicInfo.class) @RequestBody Stateprovince sp){
		stateprovinceService.save(sp, null);
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.CREATED);
	}
	
	@PutMapping("/provinces/{id}")
	public ResponseEntity<Stateprovince> updateStateProvince(@PathVariable(value="id")Integer id, @Validated(BasicInfo.class) @RequestBody Stateprovince sp){
		stateprovinceService.update(sp, id);
		return ResponseEntity.ok(sp);
	}
	
	//Address
	//-----------------------------------------------------------------------------------
	@RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable(value = "id") Integer id) {
		Address a = addressService.findById(id).get();
		return new ResponseEntity<Address>(a, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public ResponseEntity<Address> listEntitiesAddresses() {
		List<Address> entities = ((List<Address>) addressService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addresses", method = RequestMethod.POST)
	public ResponseEntity<Address> createEntityAddress(@Validated(BasicInfo.class) @RequestBody Address a) {
		addressService.save(a, null);
		return new ResponseEntity<Address>(a, HttpStatus.CREATED);
	}
	
	@PutMapping("/baddresses/{id}")
	public ResponseEntity<Address> updateEntityAddress(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody Address a) {

		addressService.update(a, id);
		return ResponseEntity.ok(a);
	}
}
