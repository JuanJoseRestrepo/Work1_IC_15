package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void save(Countryregion entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Countryregion entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Countryregion entity) {
		entityManager.remove(entity);
	}

	@Override
	public Countryregion findById(Integer codigo) {
		return entityManager.find(Countryregion.class, codigo);
	}

	@Override
	public List<Countryregion> findAll() {
		String jpql = "Select a from Countryregion a";
		return 	entityManager.createQuery(jpql,Countryregion.class).getResultList();
	}

}
