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
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public Salestaxrate getSalestaxrateByStateprovince(Integer id) {
		String jpql = "SELECT sr FROM Salestaxrate sr WHERE sr.stateprovince.stateprovinceid =:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Salestaxrate salestaxrate = (Salestaxrate) query.getSingleResult();
		return salestaxrate;
	}

	@Override
	public Salestaxrate getSalestaxrateByName(String name) {
		String jpql = "SELECT sr FROM Salestaxrate sr WHERE sr.name =:name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		Salestaxrate salestaxrate = (Salestaxrate) query.getSingleResult();
		return salestaxrate;
	}
}
