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

import com.icesi.taller1.model.BasicInfo;
import com.icesi.taller1.model.Countryregion;
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
	public Salestaxrate getSalestaxrate(long id) {
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
	
	public Stateprovince getStateprovince(long id) {
		String url = "http://localhost:8080/states/" + id;
		Stateprovince sp = restTemplate.getForObject(url, Stateprovince.class);
		return sp;
	}
	
	public Iterable<Stateprovince> getAllStateprovince(){
		String url = "http://localhost:8080/states/";
		Stateprovince[] sp = restTemplate.getForObject(url, Stateprovince[].class);
		return Arrays.asList(sp);
	}
	
	public String createStateprovince(Stateprovince sp) {
		String url = "http://localhost:8080/states/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp, headers);
		
		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}
	
	
	public String updateStateprovince(long id, @Validated(BasicInfo.class) Stateprovince sp) {
		String url = "http://localhost:8080/states/";
		
		HttpHeaders headers = new  HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp, headers);
		
		return restTemplate.exchange(url + id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
}
