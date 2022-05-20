package com.icesi.taller1.dao;

import java.util.List;

import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.model.sales.Salesterritory;

public interface SalestaxrateDAOInterface {

	public List<Salestaxrate> getSalestaxrateByStateprovince(Integer id);
	public List<Salestaxrate> getSalestaxrateByName(String name);
	public Salestaxrate save(Salestaxrate entity);
	public Salestaxrate update(Salestaxrate entity);
	public void delete(Salestaxrate entity);
	public Salestaxrate findById(Integer codigo);
	public List<Salestaxrate> findAll();
}
