package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Salestaxrate;

public interface SalestaxrateDAOInterface {

	public Salestaxrate getSalestaxrateByStateprovince(Integer id);
	public Salestaxrate getSalestaxrateByName(String name);
	public void save(Salestaxrate entity);
	public void update(Salestaxrate entity);
	public void delete(Salestaxrate entity);
	public Salestaxrate findById(Integer codigo);
	public List<Salestaxrate> findAll();
	
}
