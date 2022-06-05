package com.icesi.taller1.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.icesi.taller1.model.Person;

public interface PersonDAOInterface {

	Person save(Person entity);

	Person update(Person entity);

	void delete(Person entity);

	Person findById(Integer codigo);

	List<Person> findAll();

}