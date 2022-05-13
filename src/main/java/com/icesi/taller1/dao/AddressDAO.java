package com.icesi.taller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Stateprovince;

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
	public Address save(Address entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public void update(Address entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Address entity) {
		entityManager.remove(entity);
	}

	@Override
	public Address findById(Integer codigo) {
		return entityManager.find(Address.class, codigo);
	}

	@Override
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Address getAddressByStateprovinceId(Integer id) {
		String jpql = "SELECT ad FROM Address ad WHERE ad.stateprovince.stateprovinceid =:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Address address = (Address) query.getSingleResult();
		return address;
	}

	@Override
	public Address getAddressByCity(String city) {
		String jpql = "SELECT ad FROM Address ad WHERE ad.city =:city";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("city", city);
		Address address = (Address) query.getSingleResult();
		return address;
	}

	@Override
	public List<Address> getListAddressByAlmostTwoHeadsBySales() {
		// TODO Auto-generated method stub
		return null;
	}

}
