package com.icesi.taller1.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.BasicInfo;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Employee;
import com.icesi.taller1.model.Person;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;


@Component
public class DelegatedAdmin {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//COUNTRY REGION
	//------------------------------------------------------------------------
	public Countryregion getCountryregion(Integer id) {
		String url = "http://localhost:8080/countries/" + id;
		Countryregion cr = restTemplate.getForObject(url, Countryregion.class);
		return cr;
	}
	
	
	public Iterable<Countryregion> getAllCountryregion(){
		String url = "http://localhost:8080/countries";
		Countryregion[] cr = restTemplate.getForObject(url, Countryregion[].class);
		return Arrays.asList(cr);
	}
	
	
	public String createCountryregion(Countryregion cr) {
		String url = "http://localhost:8080/countries/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(cr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updateCountryregion(long id, @Validated(BasicInfo.class) Countryregion cr) {
		String url = "http://localhost:8080/countries/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Countryregion> entity = new HttpEntity<Countryregion>(cr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	
	//SALESTAXRATE
	//------------------------------------------------------------------------
	public Salestaxrate getSalestaxrate(Integer id) {
		String url = "http://localhost:8080/sales/" + id;
		Salestaxrate sr = restTemplate.getForObject(url, Salestaxrate.class);
		return sr;
	}
	
	public Iterable<Salestaxrate> getAllSalestaxrate(){
		String url = "http://localhost:8080/sales/";
		Salestaxrate[] sr = restTemplate.getForObject(url, Salestaxrate[].class);
		return Arrays.asList(sr);
	}
	
	
	public String createSalestaxrate(Salestaxrate sr) {
		String url = "http://localhost:8080/sales/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(sr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updateSalestaxrate(long id, @Validated(BasicInfo.class) Salestaxrate sr) {
		String url = "http://localhost:8080/sales/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Salestaxrate> entity = new HttpEntity<Salestaxrate>(sr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	
	//STATEPROVINCE
	//------------------------------------------------------------------------
	public Iterable<Stateprovince> getAllStateprovince(){
		String url = "http://localhost:8080/states";
		Stateprovince[] sp = restTemplate.getForObject(url, Stateprovince[].class);
		return Arrays.asList(sp);
	}
	
	public Iterable<Stateprovince> findAll() {
		// TODO Auto-generated method stub
		Stateprovince[] sps = restTemplate.getForObject("http://localhost:8080/states", Stateprovince[].class);
		return Arrays.asList(sps);
	}
	
	
	//PERSON
	//------------------------------------------------------------------------------------------
	public Person getPerson(Integer id) {
		String url = "http://localhost:8080/persons/" + id;
		Person p = restTemplate.getForObject(url, Person.class);
		System.out.println(p.getFirstname());
		return p;
		
	}
	
	
	public Iterable<Person> getAllPerson(){
		String url = "http://localhost:8080/persons";
		Person[] p = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(p);
	}
	
	
	public String createPerson(Person cr) {
		String url = "http://localhost:8080/persons/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(cr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updatePerson(Integer id, @Validated(BasicInfo.class) Person cr) {
		String url = "http://localhost:8080/persons/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(cr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	public void deletePerson(Person per){
		String url1 = "http://localhost:8080/persons/";
		String url2 = "/persons/";
		restTemplate.delete(url1+per.getBusinessentityid());
	}
	
	//EMPLOYEE
	//---------------------------------------------------------------------------------------------
	
	public Employee getEmployee(Integer id) {
		String url = "http://localhost:8080/employees/" + id;
		Employee p = restTemplate.getForObject(url, Employee.class);
		return p;
	}
	
	
	public Iterable<Employee> getAllEmployee(){
		String url = "http://localhost:8080/employees";
		Employee[] p = restTemplate.getForObject(url, Employee[].class);
		return Arrays.asList(p);
	}
	
	
	public String createEmploye(Employee cr) {
		String url = "http://localhost:8080/employees/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(cr, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	public String updateEmployee(Integer id, @Validated(BasicInfo.class) Employee cr) {
		String url = "http://localhost:8080/employees/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(cr, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	public void deleteEmployee(Integer id){
		String url1 = "http://localhost:8080/employees/";
		restTemplate.delete(url1+id, Employee.class);

	}
	
	
	public void deletePerson(Integer id){
		String url1 = "http://localhost:8080/persons/";
		restTemplate.delete(url1+id, Person.class);

	}
	
	public Iterable<Address> findAddressesWithSalesorderheader() {
		String url = "http://localhost:8080/query/";
		Address[] sps = restTemplate.getForObject(url, Address[].class);
		return Arrays.asList(sps);
	}
	
	
}
