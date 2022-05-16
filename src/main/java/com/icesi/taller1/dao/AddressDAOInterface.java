package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Address;

public interface AddressDAOInterface {

	public List<Address> getListAddressByAlmostTwoHeadsBySales();
	public List<Address> getAddressByStateprovinceId(Integer id);
	public List<Address> getAddressByCity(String city);
	public Address save(Address entity);
	public void update(Address entity);
	public void delete(Address entity);
	public Address findById(Integer codigo);
	public List<Address> findAll();
	
}
