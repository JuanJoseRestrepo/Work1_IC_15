package com.icesi.taller1.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.AddressRepository;
import com.icesi.taller1.repository.StateprovinceRepository;



@Service
public class AddressService{
	
	//MainRepo
	private AddressRepository addressRepository;
	//OtherRepos
	private StateprovinceRepository stateprovinceRepository;
	
	//Constructor
	@Autowired
	public AddressService(AddressRepository addressRepository, StateprovinceRepository stateprovinceRepository) {
		this.addressRepository = addressRepository;
		this.stateprovinceRepository = stateprovinceRepository;
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
			
			Optional<Stateprovince> optional = this.stateprovinceRepository.findById(stateprovinceid);
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				
				sAddress = this.addressRepository.save(entity);
			}
		}
		
		
		
		
		return sAddress;
	}
	
	public Address update(Address entity, Integer stateprovinceid) {
		Address entityActual = null;
		
		if(entity.getAddressid() != null) {
			Optional<Address> optinalEntity = addressRepository.findById(entity.getAddressid());
			if(optinalEntity.isPresent()) {
				entityActual = save(entity, stateprovinceid);
			}
		}
		
		return entityActual;
	}
	
	public Optional<Address> findById(Integer id) {
		return addressRepository.findById(id);
	}
	
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public Address getAddress(Integer id) {
		return addressRepository.getById(id);
	}
	
}