package com.icesi.taller1.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.SalestaxrateDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;

@Service
public class SalestaxrateService {

	//MainRepo
	private SalestaxrateDAO salestaxrateDAO;
	//OtherRepos
	private StateprovinceDAO stateprovinceRepository;
	
	//Constructor
	@Autowired
	public SalestaxrateService(SalestaxrateDAO salestaxrateRepository,StateprovinceDAO stateprovinceRepository) {
		this.salestaxrateDAO = salestaxrateRepository;
		this.stateprovinceRepository = stateprovinceRepository;
	}
	
	@Transactional
	public Salestaxrate save(Salestaxrate entity, Integer stateprovinceid) {
		
		Salestaxrate aux1 = null;
		
		boolean one = (entity.getTaxrate() != null) && (entity.getTaxrate().doubleValue() >= 0);
		boolean two =  (entity.getName() != null) && entity.getName().length() >= 5;
		

		
		if(one && two) {
			
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceRepository.findById(stateprovinceid));
			
			if(optional.isPresent()) {
				
				entity.setStateprovince(optional.get());
				aux1 = this.salestaxrateDAO.save(entity);
			}else {
				return aux1 = null;
			}
			
		}else {
			return aux1 = null;
		}
		
		
		return aux1;
	}
	
	@Transactional
	public Salestaxrate update(Salestaxrate entity, Integer stateprovinceid) {
		
		boolean one = (entity.getTaxrate() != null) && (entity.getTaxrate().doubleValue() >= 0);
		boolean two =  (entity.getName() != null) && entity.getName().length() >= 5;

		if(one && two) {
		if(entity.getSalestaxrateid() != null) {
			Optional<Stateprovince> optional = Optional.ofNullable(this.stateprovinceRepository.findById(stateprovinceid));
			Optional<Salestaxrate> optinalEntity = Optional.ofNullable(this.salestaxrateDAO.findById(entity.getSalestaxrateid()));
			if(optinalEntity.isPresent() && optional.isPresent()) {
				entity.setStateprovince(optional.get());
				entity = this.salestaxrateDAO.update(entity);
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
	public Optional<Salestaxrate> findById(Integer id) {
		return Optional.ofNullable(salestaxrateDAO.findById(id));
	}
	@Transactional
	public Iterable<Salestaxrate> findAll() {
		return salestaxrateDAO.findAll();
	}
	@Transactional
	public Salestaxrate getSalestaxrate(Integer id) {
		return salestaxrateDAO.findById(id);
	}

	
}
