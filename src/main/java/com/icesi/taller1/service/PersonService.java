package com.icesi.taller1.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.PersonDAO;
import com.icesi.taller1.model.Person;

@Service
public class PersonService {
	
	private PersonDAO personDAO;

	@Autowired
	public PersonService(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@Transactional
	public Person save(Person person) {		
		Person aux = null;
		boolean one = (person.getFirstname() != null) && (person.getLastname() != null);

		if (one) {
			aux = this.personDAO.save(person);	
		}else {
			return aux = null;
		}
		
		return aux;	
	}
	
	@Transactional
	public Iterable<Person> findAll() {
		return personDAO.findAll();
	}
	
	@Transactional
	public void delete(Person person) {
		personDAO.delete(person);

	}
	
	@Transactional
	public Person findById(Integer id) {
		return personDAO.findById(id);
	}
	
	@Transactional
	public void update(Person person) {
		Person modPerson = personDAO.findById(person.getBusinessentityid());
		modPerson.setFirstname(person.getFirstname());
		modPerson.setLastname(person.getLastname());
		//modPerson.setTitle(person.getTitle());
		//modPerson.setBusinessentity(.getById(person.getBusinessentity().getBusinessentityid()));

		personDAO.save(modPerson);
	}
	
	@Transactional
	public Iterable<Person> findAllById(long id) {
		ArrayList<Person> persons = (ArrayList<Person>) personDAO.findAll();
		return persons;
	}
	
}
