package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Stateprovince;

public interface StateprovinceDAOInterface {

	public List<Stateprovince> getStateprovinceById(Integer id);
	public List<Stateprovince> getStateprovinceByTerritoryId(Integer id);
	public List<Stateprovince> getStateprovinceByName(String name);
	public Stateprovince save(Stateprovince entity);
	public Stateprovince update(Stateprovince entity);
	public void delete(Stateprovince entity);
	public Stateprovince findById(Integer codigo);
	public List<Stateprovince> findAll();
}
