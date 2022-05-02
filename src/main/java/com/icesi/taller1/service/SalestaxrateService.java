package com.icesi.taller1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;

@Service
public class SalestaxrateService {

	//MainRepo
	private SalestaxrateRepository salestaxrateRepository;
	//OtherRepos
	private StateprovinceRepository stateprovinceRepository;
	
	//Constructor
	@Autowired
	public SalestaxrateService(SalestaxrateRepository salestaxrateRepository,StateprovinceRepository stateprovinceRepository) {
		this.salestaxrateRepository = salestaxrateRepository;
		this.stateprovinceRepository = stateprovinceRepository;
	}
	

	public Salestaxrate save(Salestaxrate entity, Integer stateprovinceid) {
		
		Salestaxrate aux1 = null;
		
		boolean one = (entity.getTaxrate() != null) && (entity.getTaxrate().doubleValue() >= 0);
		boolean two =  (entity.getName() != null) && entity.getName().length() >= 5;
		

		
		if(one && two) {
			
			Optional<Stateprovince> optional = this.stateprovinceRepository.findById(stateprovinceid);
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				aux1 = this.salestaxrateRepository.save(entity);
			}
			
		}
		
		
		return aux1;
	}
	
	public Salestaxrate update(Salestaxrate entity, Integer stateprovinceid) {
		
		Salestaxrate entidadActual = null;
		
		if(entity.getSalestaxrateid() != null) {
			Optional<Salestaxrate> optinalEntity = salestaxrateRepository.findById(entity.getSalestaxrateid());
			if(optinalEntity.isPresent()) {
				entidadActual = save(entity,stateprovinceid);
			}
		}
		
		
		return entidadActual;
		
	}
	
	public Optional<Salestaxrate> findById(Integer id) {
		return salestaxrateRepository.findById(id);
	}
	
	public Iterable<Salestaxrate> findAll() {
		return salestaxrateRepository.findAll();
	}
	
	public Salestaxrate getSalestaxrate(Integer id) {
		
		return salestaxrateRepository.getById(id);
	}

	
}
