package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Countryregion;

public interface CountryregionDAOInterface {

	public Countryregion save(Countryregion entity);
	public Countryregion update(Countryregion entity);
	public void delete(Countryregion entity);
	public Countryregion findById(Integer codigo);
	public List<Countryregion> findAll();
	
}
