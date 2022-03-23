package com.icesi.taller1.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.StateprovinceRepository;

@Service
public class StateprovinceService {
	
	private StateprovinceRepository stateprovinceRepository;
	private CountryregionRepository countryregionRepository;
	
	@Autowired
	public StateprovinceService(StateprovinceRepository stateprovinceRepository,
			CountryregionRepository countryregionRepository) {
		this.stateprovinceRepository = stateprovinceRepository;
		this.countryregionRepository = countryregionRepository;
	}
	
	@Transactional
	public Stateprovince save(Stateprovince entity, Integer countryregionid) {
		
		Stateprovince aux = null;
		boolean one = entity.getStateprovincecode().length() >= 5;
		boolean two = entity.getIsonlystateprovinceflag().equals("Y") || entity.getIsonlystateprovinceflag().equals("N");
		boolean three = entity.getName().length() >= 5;
		
		if(one && two && three) {
			Optional<Countryregion> optional = this.countryregionRepository.findById(countryregionid);
			
			if(optional.isPresent()) {
				
				entity.setCountryregion(optional.get());
				aux = this.stateprovinceRepository.save(entity);
			}else {
				throw new IllegalArgumentException("No se estan haciendo las validaciones correctas");
			}
			
		}else {
			throw new IllegalArgumentException("No existe");
		}
		
		return aux;
		
	}
	
	@Transactional
	public void update(Stateprovince sp) {
		
		boolean one = sp.getStateprovincecode().length() >= 5;
		boolean two = sp.getIsonlystateprovinceflag().equals("Y") || sp.getIsonlystateprovinceflag().equals("N");
		boolean three = sp.getName().length() >= 5;
		
		if (one && two && three) {
			
			Stateprovince modSP = stateprovinceRepository.getById(sp.getStateprovinceid());
			modSP.setCountryregion(countryregionRepository.getById(sp.getCountryregion().getCountryregionid()));
			modSP.setStateprovincecode(sp.getStateprovincecode());
			modSP.setIsonlystateprovinceflag(sp.getIsonlystateprovinceflag());
			modSP.setName(sp.getName());
			
			stateprovinceRepository.save(modSP);
		}else {
			throw new IllegalArgumentException("No se estan haciendo las validaciones correctas");
		}
		
		
	}
	

}
