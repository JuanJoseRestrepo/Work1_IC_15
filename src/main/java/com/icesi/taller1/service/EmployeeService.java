package com.icesi.taller1.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.taller1.dao.EmployeeDAO;
import com.icesi.taller1.model.Employee;

@Service
public class EmployeeService {
	
	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeService(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Transactional
	public Employee save(Employee cr) {
		Employee a =employeeDAO.save(cr);		
		return a;	
		
	}
	@Transactional
	public Employee update(Employee entity) {
		boolean one = (entity.getNationalidnumber() != null) && (entity.getJobtitle()!=null);
		boolean two = (entity.getPerson() != null);
		
		
		if (one && two) {
		if(entity.getBusinessentityid() != null) {
			Optional<Employee> optinalEntity = Optional.ofNullable(employeeDAO.findById(entity.getBusinessentityid()));
			if(optinalEntity.isPresent()) {
				entity = this.employeeDAO.update(entity);
			}else {
				entity = null;
			}
		}
	}else {
		entity = null;
	}
		
		return entity;
	}
	@Transactional
	public Optional<Employee> findById(Integer id) {
		return Optional.ofNullable(employeeDAO.findById(id));
	}
	@Transactional
	public Iterable<Employee> findAll() {
		return employeeDAO.findAll();
	}
	@Transactional
	public Employee getEmployee(Integer id) {
		return employeeDAO.findById(id);
	}
	
	@Transactional
	public void deleteEmployee(Employee emplo) {
		employeeDAO.delete(emplo);
	}

}
