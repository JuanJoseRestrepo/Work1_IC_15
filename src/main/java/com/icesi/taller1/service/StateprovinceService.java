package com.icesi.taller1.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.StateprovinceRepository;

@Service
public class StateprovinceService {
	
	private StateprovinceDAO stateprovinceDAO;
	private CountryregionDAO countryregionDAO;
	
	@Autowired
	public StateprovinceService(StateprovinceDAO stateprovinceDAO,
			CountryregionDAO countryregionDAO) {
		this.stateprovinceDAO = stateprovinceDAO;
		this.countryregionDAO = countryregionDAO;
	}
	
	@Transactional
	public Stateprovince save(Stateprovince entity, Integer countryregionid) {
		
		Stateprovince aux = null;
		boolean one = (entity.getStateprovincecode() != null) && (String.valueOf(entity.getStateprovincecode()).length() >= 5);
		boolean two = (entity.getIsonlystateprovinceflag() != null) && (entity.getIsonlystateprovinceflag().equals("Y") || entity.getIsonlystateprovinceflag().equals("N"));
		boolean three = (entity.getName() != null) && entity.getName().length() >= 5;
		
		if(entity.getStateprovincecode() != null && !entity.getStateprovincecode().isBlank()) {
			int number = Integer.parseInt(entity.getStateprovincecode());
		}
		
		if(one && two && three) {
			Optional<Countryregion> optional = Optional.ofNullable(this.countryregionDAO.findById(countryregionid));
			
			if(optional.isPresent()) {
				
				entity.setCountryregion(optional.get());
				aux = this.stateprovinceDAO.save(entity);
			}else {
				return aux = null;
			}
			
		}else {
			return aux = null;
		}
		
		return aux;
		
	}
	
	
	@Transactional
	public Stateprovince update(Stateprovince entity, Integer countryregionid) {
		
		boolean one = (entity.getStateprovincecode() != null) && (String.valueOf(entity.getStateprovincecode()).length() >= 5);
		boolean two = (entity.getIsonlystateprovinceflag() != null) && (entity.getIsonlystateprovinceflag().equals("Y") || entity.getIsonlystateprovinceflag().equals("N"));
		boolean three = (entity.getName() != null) && entity.getName().length() >= 5;
		
		if(entity.getStateprovincecode() != null && !entity.getStateprovincecode().isBlank()) {
			int number = Integer.parseInt(entity.getStateprovincecode());
		}
		
		if(one && two && three) {
		if(entity.getStateprovinceid() != null) {
			Optional<Stateprovince> optinalEntity = Optional.ofNullable(stateprovinceDAO.findById(entity.getStateprovinceid()));
			Optional<Countryregion> optional = Optional.ofNullable(this.countryregionDAO.findById(countryregionid));
			if(optinalEntity.isPresent() && optional.isPresent()) {
				entity.setCountryregion(optional.get());
				entity = this.stateprovinceDAO.update(entity);
			}else {
				return entity = null;
			}
		 }
	   }else {
			return entity = null;
		}
		
		return entity;
		
	}
	@Transactional
	public Optional<Stateprovince> findById(Integer id) {
		return Optional.ofNullable(stateprovinceDAO.findById(id));
	}
	@Transactional
	public Iterable<Stateprovince> findAll() {
		return stateprovinceDAO.findAll();
	}
	@Transactional
	public Stateprovince getStateProvince(Integer id) {
		
		return stateprovinceDAO.findById(id);
	}
	
	@Transactional
	public void saveStateprovinces(Stateprovince sp) {

		stateprovinceDAO.save(sp);
	}

}
