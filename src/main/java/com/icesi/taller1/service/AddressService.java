package com.icesi.taller1.service;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.AddressDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Stateprovince;
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
	@Transactional
	public Address save(Address entity, Integer stateprovinceid) {
		Address sAddress = null;
		
		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (entity.getCity().length() >= 3);
		boolean postalcodeV = (entity.getPostalcode() != null) && (String.valueOf(entity.getPostalcode()).length() == 6);
		
		if(entity.getPostalcode() != null && !entity.getPostalcode().isBlank()){
			int number = Integer.parseInt(entity.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
			
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceDao.findById(stateprovinceid));
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				
				sAddress = this.addressDao.save(entity);
			}else {
				return sAddress = null;
			}
		}else {
			return sAddress = null;
		}
		
		
		
		
		return sAddress;
	}
	@Transactional
	public Address update(Address entity, Integer stateprovinceid) {

		boolean addressline1V = (entity.getAddressline1() != null) && (!entity.getAddressline1().isBlank());
		boolean cityV = (entity.getCity() != null) && (entity.getCity().length() >= 3);
		boolean postalcodeV = (entity.getPostalcode() != null) && (String.valueOf(entity.getPostalcode()).length() == 6);
		
		if(entity.getPostalcode() != null && !entity.getPostalcode().isBlank()){
			int number = Integer.parseInt(entity.getPostalcode());	
		}
		
		if(addressline1V && cityV && postalcodeV) {
		if(entity.getAddressid() != null) {
			Optional<Address> optinalEntity = Optional.ofNullable(this.addressDao.findById(entity.getAddressid()));
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceDao.findById(stateprovinceid));
			if(optinalEntity.isPresent() && optional.isPresent()) {
				entity.setStateprovince(optional.get());
				entity = addressDao.update(entity);
				
			}else {
				return entity = null;
			}
	      }
		}else {
			return entity = null;
		}
		
		return entity;
	} 
	@Transactional
	public Optional<Address> findById(Integer id) {
		return Optional.ofNullable(this.addressDao.findById(id));
	}
	@Transactional
	public Iterable<Address> findAll() {
		return addressDao.findAll();
	}
	@Transactional
	public Address getAddress(Integer id) {
		return addressDao.findById(id);
	}
	
	
	@Transactional
	public void saveAddress(Address address) {

		addressDao.save(address);

	}
	
}