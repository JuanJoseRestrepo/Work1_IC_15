package com.icesi.taller1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.icesi.taller1.model.sales.Salesterritory;
import com.icesi.taller1.repository.SalesTerritoryRepository;

public class SalesterritoryService {
	
	private SalesTerritoryRepository repo;

	@Autowired
	public SalesterritoryService(SalesTerritoryRepository repo) {
		super();
		this.repo = repo;
	}
	
	public Salesterritory save(Salesterritory s) {
		return repo.save(s);
	}
	
	public Optional<Salesterritory> findById(Integer id) {
		return repo.findById(id);
	}
	
	public Iterable<Salesterritory> findAll() {
		return repo.findAll();
	}
}
