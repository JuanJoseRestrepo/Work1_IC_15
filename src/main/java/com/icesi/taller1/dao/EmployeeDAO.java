package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Employee;


@Repository
@Scope("singleton")
public class EmployeeDAO implements EmployeeDAOInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EmployeeDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Employee save(Employee entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public Employee update(Employee entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public void delete(Employee entity) {
		entityManager.remove(entity);
	}
	
	@Override
	@Transactional
	public Employee findById(Integer codigo) {
		return entityManager.find(Employee.class, codigo);
	}
	
	@Override
	@Transactional
	public List<Employee> findAll(){
		String jpql = "Select a from Person a";
		return entityManager.createQuery(jpql, Employee.class).getResultList();
	}
}
