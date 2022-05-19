package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Countryregion;

@Repository
@Scope("singleton")
public class CountryregionDAO implements CountryregionDAOInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public CountryregionDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Countryregion save(Countryregion entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	@Transactional
	public Countryregion update(Countryregion entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public void delete(Countryregion entity) {
		entityManager.remove(entity);
	}

	@Override
	@Transactional
	public Countryregion findById(Integer codigo) {
		return entityManager.find(Countryregion.class, codigo);
	}

	@Override
	@Transactional
	public List<Countryregion> findAll() {
		String jpql = "Select a from Countryregion a";
		return 	entityManager.createQuery(jpql,Countryregion.class).getResultList();
	}

}
