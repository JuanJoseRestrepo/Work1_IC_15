package com.icesi.taller1.testDelegate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.controller.DelegatedAdmin;
import com.icesi.taller1.controller.DelegatedUser;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;

@ContextConfiguration(classes = Taller1Application.class)
@SpringBootTest
public class AdminAndUserDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private DelegatedAdmin delegatedAdmin;
	@InjectMocks
	private DelegatedUser delegatedUser;
	
	private Address address;
	private Stateprovince stateprovince;
	private Countryregion countryregion;
	private Salestaxrate salestaxrate;
	
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		address = new Address();
		address.setAddressline1("Cra 29a #10b - 118");
		address.setCity("Cali");
		address.setAddressline2("Address2");
		address.setPostalcode("770014");
		
		stateprovince = new Stateprovince();
		stateprovince.setName("Rioacha");
		stateprovince.setStateprovincecode("12456");
		
		countryregion = new Countryregion();
		countryregion.setCountryregioncode("1234");
		countryregion.setName("Colombia");
		
		salestaxrate = new Salestaxrate();
		salestaxrate.setTaxrate(new BigDecimal("124567890.0987654321"));
		salestaxrate.setName("cinco");
		
	}
	
	
	@Test
	public void testGetAddress() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/addresses/", address,Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK));
		
		delegatedUser.save(address);
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/addresses/1",Address.class))
		.thenReturn(new ResponseEntity<Address>(address,HttpStatus.OK).getBody());
		
		Address add = delegatedUser.getAddress(1);
		
		assertEquals("Cra 29a #10b - 118",add.getAddressline1());
	}
	
	@Test
	public void testSaveAddress() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/addresses/", address,Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK));
		
		delegatedUser.save(address);
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/addresses/1",Address.class))
		.thenReturn(new ResponseEntity<Address>(address,HttpStatus.OK).getBody());
		
		assertEquals(delegatedUser.getAddress(1).getCity(), "Cali");
	}
	
	@Test
	public void testGetAllAddress() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/addresses/", address,Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK));
		
		delegatedUser.save(address);
		
		Address[] addresses = {address};
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/addresses",Address[].class))
		.thenReturn(new ResponseEntity<Address[]>(addresses,HttpStatus.OK).getBody());
		
		Iterable<Address> result = delegatedUser.getAllAddresses();
		
		int i = 0;
		for (Address t: result) {
			i++;
		}
		
		assertEquals(i,Arrays.asList(addresses).size());
	}
	
	
	@Test
	public void testEditAddress() {
		
		
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/addresses/", address,Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK));
		
		delegatedUser.save(address);
		
		address.setAddressline1("Catatumbo city");
		
		Mockito.when(restTemplate.patchForObject("http://localhost:8080/addresses/", address, Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK).getBody());	
				
		assertEquals("Catatumbo city",address.getAddressline1());
	
	}
	
	@Test
	public void testSaveStateprovince() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/provinces/", stateprovince,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(stateprovince,HttpStatus.OK));
		
		delegatedUser.save(stateprovince);
		

		Mockito.when(restTemplate.getForObject("http://localhost:8080/provinces/1",Stateprovince.class))
		.thenReturn(new ResponseEntity<Stateprovince>(stateprovince,HttpStatus.OK).getBody());
		
		assertEquals(delegatedUser.getStateprovince(1) , stateprovince);
		
	}
	
	@Test
	public void testGetStateprovicen() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/provinces/", stateprovince,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(stateprovince,HttpStatus.OK));
		
		delegatedUser.save(address);
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/provinces/1",Stateprovince.class))
		.thenReturn(new ResponseEntity<Stateprovince>(stateprovince,HttpStatus.OK).getBody());
		
		Stateprovince stateprovince = delegatedUser.getStateprovince(1);
		
		assertEquals("Rioacha",stateprovince.getName());
	}
	
	@Test
	public void testGetAllStateprovinces() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/provinces/", stateprovince,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(stateprovince,HttpStatus.OK));
		
		delegatedUser.save(stateprovince);
		
		Stateprovince[] states = {stateprovince};
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/provinces",Stateprovince[].class))
		.thenReturn(new ResponseEntity<Stateprovince[]>(states,HttpStatus.OK).getBody());
		
		Iterable<Stateprovince> result = delegatedUser.getAllStateprovinces();
		
		int i = 0;
		for (Stateprovince t: result) {
			i++;
		}
		
		assertEquals(i,Arrays.asList(states).size());
	}
	
	@Test
	public void testEditStateprovince() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/provinces/", stateprovince,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(stateprovince,HttpStatus.OK));
		
		delegatedUser.save(stateprovince);
		
		stateprovince.setName("Rioyork");
		
		Mockito.when(restTemplate.patchForObject("http://localhost:8080/provinces/", stateprovince, Stateprovince.class))
		.thenReturn(new ResponseEntity<>(stateprovince,HttpStatus.OK).getBody());	
				
		assertEquals("Rioyork",stateprovince.getName());
	}
	
	@Test
	public void testSaveCountryregion() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/countries/", countryregion,Countryregion.class))
		.thenReturn(new ResponseEntity<>(countryregion,HttpStatus.OK));
		
		delegatedAdmin.save(countryregion);
		

		Mockito.when(restTemplate.getForObject("http://localhost:8080/countries/1",Countryregion.class))
		.thenReturn(new ResponseEntity<Countryregion>(countryregion,HttpStatus.OK).getBody());
		
		assertEquals(delegatedAdmin.getCountryregion(1) , countryregion);
	}
	
	@Test
	public void testGetCountryregion() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/countries/", countryregion,Countryregion.class))
		.thenReturn(new ResponseEntity<>(countryregion,HttpStatus.OK));
		
		delegatedAdmin.save(countryregion);
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/countries/1",Countryregion.class))
		.thenReturn(new ResponseEntity<Countryregion>(countryregion,HttpStatus.OK).getBody());
		
		Countryregion add = delegatedAdmin.getCountryregion(1);
		
		assertEquals("Colombia",add.getName());
	}
	
	@Test
	public void testGetAllCountryregion() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/countries/", countryregion,Countryregion.class))
		.thenReturn(new ResponseEntity<>(countryregion,HttpStatus.OK));
		
		delegatedAdmin.save(countryregion);
		
		Countryregion[] countries = {countryregion};
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/countries",Countryregion[].class))
		.thenReturn(new ResponseEntity<Countryregion[]>(countries,HttpStatus.OK).getBody());
		
		Iterable<Countryregion> result = delegatedAdmin.getAllCountryregion();
		
		int i = 0;
		for (Countryregion t: result) {
			i++;
		}
		
		assertEquals(i,Arrays.asList(countries).size());
	}
	
	@Test
	public void testEditCountryregion() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/countries/", countryregion,Countryregion.class))
		.thenReturn(new ResponseEntity<>(countryregion,HttpStatus.OK));
		
		delegatedAdmin.save(countryregion);
		
		countryregion.setName("Calicalentura");
		
		Mockito.when(restTemplate.patchForObject("http://localhost:8080/countries/", countryregion, Countryregion.class))
		.thenReturn(new ResponseEntity<>(countryregion,HttpStatus.OK).getBody());	
				
		assertEquals("Calicalentura",countryregion.getName());
	}
	
	
	@Test
	public void testSaveSalestaxrate() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/sales/", salestaxrate,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(salestaxrate,HttpStatus.OK));
		
		delegatedAdmin.save(salestaxrate);
		

		Mockito.when(restTemplate.getForObject("http://localhost:8080/sales/1",Salestaxrate.class))
		.thenReturn(new ResponseEntity<Salestaxrate>(salestaxrate,HttpStatus.OK).getBody());
		
		assertEquals(delegatedAdmin.getSalestaxrate(1) , salestaxrate);
	}
	
	@Test
	public void testGetSalestaxrate() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/sales/", salestaxrate,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(salestaxrate,HttpStatus.OK));
		
		delegatedAdmin.save(salestaxrate);
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/sales/1",Salestaxrate.class))
		.thenReturn(new ResponseEntity<Salestaxrate>(salestaxrate,HttpStatus.OK).getBody());
		
		Salestaxrate sr = delegatedAdmin.getSalestaxrate(1);
		
		assertEquals("cinco",sr.getName());
	}
	
	@Test
	public void testGetAllSalestaxrate() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/sales/", salestaxrate,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(salestaxrate,HttpStatus.OK));
		
		delegatedAdmin.save(salestaxrate);
		
		Salestaxrate[] salestaxes = {salestaxrate};
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/sales/",Salestaxrate[].class))
		.thenReturn(new ResponseEntity<Salestaxrate[]>(salestaxes,HttpStatus.OK).getBody());
		
		Iterable<Salestaxrate> result = delegatedAdmin.getAllSalestaxrate();
		
		int i = 0;
		for (Salestaxrate t: result) {
			i++;
		}
		
		assertEquals(i,Arrays.asList(salestaxes).size());
	}
	
	@Test
	public void testEditSalestaxrate() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/sales/", salestaxrate,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(salestaxrate,HttpStatus.OK));
		
		delegatedAdmin.save(salestaxrate);
		
		salestaxrate.setName("cincos");
		
		Mockito.when(restTemplate.patchForObject("http://localhost:8080/sales/", salestaxrate, Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(salestaxrate,HttpStatus.OK).getBody());	
				
		assertEquals("cincos",salestaxrate.getName());
	}
	
}
