package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Stateprovince;

public interface StateprovinceDAOInterface {

	public Stateprovince getStateprovinceById(Integer id);
	public Stateprovince getStateprovinceByTerritoryId(Integer id);
	public Stateprovince getStateprovinceByName(String name);
	public void save(Stateprovince entity);
	public void update(Stateprovince entity);
	public void delete(Stateprovince entity);
	public Stateprovince findById(Integer codigo);
	public List<Stateprovince> findAll();
}
