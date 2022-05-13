package com.icesi.taller1.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.AddressDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.AddressRepository;
import com.icesi.taller1.repository.StateprovinceRepository;



@Service
public class AddressService{
	
	private AddressDAO addressDao;
	private StateprovinceDAO stateprovinceDao;
	
	//Constructor
	@Autowired
	public AddressService(AddressDAO addressDao, StateprovinceDAO stateprovinceDao) {
		this.addressDao = addressDao;
		this.stateprovinceDao = stateprovinceDao;
	}
	
	//Methods
	//Main Methods---
	public Address save(Address entity, Integer stateprovinceid) {
		Address sAddress = null;
		
		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (entity.getCity().length() >= 3);
		boolean postalcodeV = (entity.getPostalcode() != null) && (String.valueOf(entity.getPostalcode()).length() == 6);
		
		if(entity.getPostalcode() != null && !entity.getPostalcode().isBlank()){
			int number = Integer.parseInt(entity.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
			
			Optional<Stateprovince> optional = Optional.of(this.stateprovinceDao.findById(stateprovinceid));
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				
				sAddress = this.addressDao.save(entity);
			}
		}
		
		
		
		
		return sAddress;
	}
	
	public Address update(Address entity, Integer stateprovinceid) {
		Address entityActual = null;
		
		if(entity.getAddressid() != null) {
			Optional<Address> optinalEntity = Optional.of(addressDao.findById(entity.getAddressid()));
			if(optinalEntity.isPresent()) {
				entityActual = save(entity, stateprovinceid);
			}
		}
		
		return entityActual;
	}
	
	public Optional<Address> findById(Integer id) {
		return Optional.of(addressDao.findById(id));
	}
	
	public Iterable<Address> findAll() {
		return addressDao.findAll();
	}
	
	public Address getAddress(Integer id) {
		return addressDao.findById(id);
	}
	
}