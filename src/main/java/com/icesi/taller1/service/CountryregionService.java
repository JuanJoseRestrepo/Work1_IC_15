package com.icesi.taller1.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.repository.CountryregionRepository;

@Service
public class CountryregionService {

	private CountryregionDAO countryregionDAO;
	
	@Autowired
	public CountryregionService(CountryregionDAO countryregionDAO) {
		this.countryregionDAO = countryregionDAO;
	}
	
	@Transactional
	public Countryregion save(Countryregion cr) {
		
		Countryregion aux = null;
		boolean one = (cr.getCountryregioncode() != null) && (cr.getCountryregioncode().length()>= 1 && cr.getCountryregioncode().length() <=4);
		boolean two = (cr.getName() != null) && (cr.getName().length() >= 5);
		
		
		if (one && two) {
			aux = this.countryregionDAO.save(cr);	
		}else {
			return aux = null;
		}
		
		return aux;	
		
	}
	@Transactional
	public Countryregion update(Countryregion entity) {
		boolean one = (entity.getCountryregioncode() != null) && (entity.getCountryregioncode().length()>= 1 && entity.getCountryregioncode().length() <=4);
		boolean two = (entity.getName() != null) && (entity.getName().length() >= 5);
		
		
		if (one && two) {
		if(entity.getCountryregionid() != null) {
			Optional<Countryregion> optinalEntity = Optional.ofNullable(countryregionDAO.findById(entity.getCountryregionid()));
			if(optinalEntity.isPresent()) {
				entity = this.countryregionDAO.update(entity);
			}else {
				entity = null;
			}
		}
	}else {
		entity = null;
	}
		
		return entity;
	}
	@Transactional
	public Optional<Countryregion> findById(Integer id) {
		return Optional.ofNullable(countryregionDAO.findById(id));
	}
	@Transactional
	public Iterable<Countryregion> findAll() {
		return countryregionDAO.findAll();
	}
	@Transactional
	public Countryregion getCountryRegion(Integer id) {
		return countryregionDAO.findById(id);
	}
	
}
