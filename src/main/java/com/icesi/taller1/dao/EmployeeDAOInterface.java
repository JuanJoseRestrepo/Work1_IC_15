package com.icesi.taller1.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.icesi.taller1.model.Employee;

public interface EmployeeDAOInterface {

	Employee save(Employee entity);

	Employee update(Employee entity);

	void delete(Employee entity);

	Employee findById(Integer codigo);

	List<Employee> findAll();

}