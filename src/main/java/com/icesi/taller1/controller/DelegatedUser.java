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
import com.icesi.taller1.model.Stateprovince;

@Component
public class DelegatedUser {
	
	@Autowired
	private RestTemplate restTemplate;
	
	// State province
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Stateprovince getStateprovince(Integer id){
		String url = "http://localhost:8080/provinces/"+id;
		Stateprovince sp = restTemplate.getForObject(url, Stateprovince.class);
		return sp;
	}
	
	public Iterable<Stateprovince> getAllStateprovinces(){
		String url = "http://localhost:8080/provinces";
		Stateprovince[] sp = restTemplate.getForObject(url, Stateprovince[].class);
		return Arrays.asList(sp);
	}
	
	public String createStateprovince(Stateprovince sp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp,headers);
	      
	    return restTemplate.exchange(
	         "http://localhost:8080/provinces", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateStateprovince(Integer id, @Validated(BasicInfo.class) Stateprovince sp) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/provinces/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//Address
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public Address getAddress(Integer id){
		String url = "http://localhost:8080/addresses/"+id;
		Address a = restTemplate.getForObject(url, Address.class);
		return a;
	}
	
	public Iterable<Address> getAllAddresses(){
		String url = "http://localhost:8080/addresses";
		Address[] bea = restTemplate.getForObject(url, Address[].class);
		return Arrays.asList(bea);
	}
	
	public String createAddress(Address bea) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Address> entity = new HttpEntity<Address>(bea,headers);
	      
	    return restTemplate.exchange("http://localhost:8080/addresses", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateAddress(Integer id, @Validated(BasicInfo.class) Address a) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Address> entity = new HttpEntity<Address>(a,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/addresses/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//Countryregion
	public Iterable<Countryregion> getAllCountryregion(){
		String url = "http://localhost:8080/countries";
		Countryregion[] cr = restTemplate.getForObject(url, Countryregion[].class);
		return Arrays.asList(cr);
	}
	
	public void save(Address address) {
		restTemplate.postForEntity("http://localhost:8080/addresses/", address, Address.class);
	}
	
}
