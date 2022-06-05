package com.icesi.taller1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.dao.AddressDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.service.AddressService;


@ContextConfiguration(classes = Taller1Application.class )
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;
	@Mock
	private AddressDAO addressDAO;
	@Mock
	private StateprovinceDAO stateprovinceDAO;
	
	@Autowired
	public AddressServiceTest(AddressService addressService,AddressDAO addressRepository, StateprovinceDAO stateprovinceRepository) {
		this.addressService = addressService;
		this.addressDAO = addressRepository;
		this.stateprovinceDAO = stateprovinceRepository;
	}
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 15 #121-25", addressSave.getAddressline1());
			assertEquals("Cali", addressSave.getCity());
			assertEquals("760008", addressSave.getPostalcode());
			assertEquals(stateprovince1, stateprovince1);
			
		}
		
		@Test
		public void saveTestWrongAddressline1() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1(null);
			address1.setCity("Cali");
			address1.setPostalcode("760008");
				
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}

		
		@Test
		public void saveTestWrongCity() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("a");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongCityNull() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity(null);
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongCityEmpty() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongPostalcode() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("7600088");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void saveTestWrongPostalCodeNull() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode(null);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);	
		}
		
		@Test
		public void saveTestWrongPostalCodeEmpty() {
			//Set Up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);	
		}
		@Test
		public void saveTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.save(address1, 10);
			
			//Asserts
			assertNull(addressSave);
			
			//Verify that the method have been called with this args
			verify(addressDAO, times(0)).save(address1);
		}
		
	}
	
	
	@Nested
	@Tag("update")
	class Update {
		
		@Test
		public void updateTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");

			
			Stateprovince stateprovince1 = new Stateprovince();
			address1.setStateprovince(stateprovince1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNotNull(address1);
			assertEquals("Calle 15 #121-25", address1.getAddressline1());
			assertEquals("Cali", address1.getCity());
			assertEquals("760008", address1.getPostalcode());
			assertEquals(stateprovince1, stateprovince1);
		}
		
		@Test
		public void updateTestWrongAddressline1() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1(null);
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		
		@Test
		public void updateTestWrongCity() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("a");
			address1.setPostalcode("760008");
							
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			
			assertNotNull(addressDAO);			
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongCityNull() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity(null);
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			
			assertNotNull(addressDAO);			
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongCityEmpty() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);			
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongPostalcode() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("76000");
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongPostalCodeToMuch() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("7600088");
					
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongPostalCodeNull() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode(null);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongPostalCodeEmpty() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("");
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
			assertNotNull(addressDAO);
			verify(stateprovinceDAO, times(0)).findById(1);
			verify(addressDAO, times(0)).save(address1);
		}
		
		@Test
		public void updateTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(1);
			address1.setAddressline1("Calle 15 #121-25");
			address1.setCity("Cali");
			address1.setPostalcode("760008");
			
			//Method
			Address addressSave =  addressService.update(address1, 10);
			
			//Asserts
			assertNull(addressSave);

		}
		
	}
	
	
	
}
