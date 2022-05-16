package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;

@Repository
@Scope("singleton")
public class SalestaxrateDAO implements SalestaxrateDAOInterface {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public SalestaxrateDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void save(Salestaxrate entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(Salestaxrate entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(Salestaxrate entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Salestaxrate findById(Integer codigo) {
		return entityManager.find(Salestaxrate.class, codigo);
	}

	@Override
	public List<Salestaxrate> findAll() {
		String jpql = "Select a from Salestaxrate a";
		return 	entityManager.createQuery(jpql,Salestaxrate.class).getResultList();	
	}

	@Override
	public List<Salestaxrate> getSalestaxrateByStateprovince(Integer id) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.stateprovince.stateprovinceid = '"+id+"'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}

	@Override
	public List<Salestaxrate> getSalestaxrateByName(String name) {
		String jpql = "SELECT str FROM Salestaxrate str WHERE str.name = '"+ name + "'";
		return entityManager.createQuery(jpql,Salestaxrate.class).getResultList();
	}
}
