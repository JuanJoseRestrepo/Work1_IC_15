package com.icesi.taller1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.repository.CountryregionRepository;

@Service
public class CountryregionService {

	private CountryregionRepository countryregionRepository;
	
	@Autowired
	public CountryregionService(CountryregionRepository countryregionRepository) {
		this.countryregionRepository = countryregionRepository;
	}
	
	
	public Countryregion save(Countryregion cr) {
		
		Countryregion aux = null;
		boolean one = (cr.getCountryregioncode() != null) && (cr.getCountryregioncode().length()>= 1 && cr.getCountryregioncode().length() <=4);
		boolean two = (cr.getName() != null) && (cr.getName().length() >= 5);
		
		
		if (one && two) {
			aux = this.countryregionRepository.save(cr);	
		}
		
		return aux;
		
	}
	
	public Countryregion update(Countryregion entity) {
		Countryregion entityActual = null;
		
		if(entity.getCountryregionid() != null) {
			Optional<Countryregion> optinalEntity = countryregionRepository.findById(entity.getCountryregionid());
			if(optinalEntity.isPresent()) {
				entityActual = save(entity);
			}
		}
		
		return entityActual;
	}
	
	
}
