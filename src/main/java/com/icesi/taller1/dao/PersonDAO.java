package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Person;

@Repository
@Scope("singleton")
public class PersonDAO implements PersonDAOInterface{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public PersonDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Person save(Person entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public Person update(Person entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public void delete(Person entity) {
		entityManager.remove(entity);
	}
	
	@Override
	@Transactional
	public Person findById(Integer codigo) {
		return entityManager.find(Person.class, codigo);
	}
	
	@Override
	@Transactional
	public List<Person> findAll(){
		String jpql = "Select a from Person a";
		return entityManager.createQuery(jpql, Person.class).getResultList();
	}
}
