package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Stateprovince;

@Repository
@Scope("singleton")
public class StateprovinceDAO implements StateprovinceDAOInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public StateprovinceDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void save(Stateprovince entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Stateprovince entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Stateprovince entity) {
		entityManager.remove(entity);	
	}

	@Override
	public Stateprovince findById(Integer codigo) {
		return entityManager.find(Stateprovince.class, codigo);
	}

	@Override
	public List<Stateprovince> findAll() {
		String jpql = "Select a from Stateprovince a";
		return 	entityManager.createQuery(jpql,Stateprovince.class).getResultList();	
	}

	@Override
	public List<Stateprovince> getStateprovinceById(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.countryregion.countryregionid = '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Override
	public List<Stateprovince> getStateprovinceByTerritoryId(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.territoryid =: '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	@Override
	public List<Stateprovince> getStateprovinceByName(String name) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+name+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}
}
