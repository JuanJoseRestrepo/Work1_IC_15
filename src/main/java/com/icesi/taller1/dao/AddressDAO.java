package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Address;

@Repository
@Scope("singleton")
public class AddressDAO implements AddressDAOInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public AddressDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Address save(Address entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	@Transactional
	public Address update(Address entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public void delete(Address entity) {
		entityManager.remove(entity);
	}

	@Override
	@Transactional
	public Address findById(Integer codigo) {
		return entityManager.find(Address.class, codigo);
	}

	@Override
	@Transactional
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		return 	entityManager.createQuery(jpql,Address.class).getResultList();
	}

	@Override
	@Transactional
	public List<Address> getAddressByStateprovinceId(Integer id) {
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '" +id +"'";
        return entityManager.createQuery(jpql,Address.class).getResultList();
	}

	@Override
	@Transactional
	public  List<Address> getAddressByCity(String city) {
	       String jpql = "SELECT a FROM Address a WHERE a.city = '" + city + "'";
	       return entityManager.createQuery(jpql,Address.class).getResultList();
	}

	@Override
	@Transactional
	public List<Address> getListAddressByAlmostTwoHeadsBySales() {
		// TODO Auto-generated method stub
		return null;
	}

}
