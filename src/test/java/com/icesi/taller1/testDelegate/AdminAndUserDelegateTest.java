package com.icesi.taller1.testDelegate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		Mockito.when(restTemplate.postForEntity("http://localhost:8080/addresses/", address,Address.class))
		.thenReturn(new ResponseEntity<>(address,HttpStatus.OK));
		
		delegatedUser.save(address);
		
	}
	
}
