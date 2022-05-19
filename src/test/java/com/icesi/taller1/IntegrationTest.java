package com.icesi.taller1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.AddressRepository;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;
import com.icesi.taller1.service.AddressService;
import com.icesi.taller1.service.CountryregionService;
import com.icesi.taller1.service.SalestaxrateService;
import com.icesi.taller1.service.StateprovinceService;

@ContextConfiguration(classes = Taller1Application.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IntegrationTest {
	
	//Aux
	private AddressRepository addressRepository;
	private CountryregionRepository countryregionRepository;
	private SalestaxrateRepository salestaxrateRepository;
	private StateprovinceRepository stateprovinceRepository;
	
	//Service
	private AddressService addressService;
	private CountryregionService countryregionService;
	private SalestaxrateService salestaxrateService;
	private StateprovinceService stateprovinceService;
	
	@Autowired
	public IntegrationTest(AddressRepository addressRepository, CountryregionRepository countryregionRepository,
			SalestaxrateRepository salestaxrateRepository, StateprovinceRepository stateprovinceRepository,
			AddressService addressService, CountryregionService countryregionService,
			SalestaxrateService salestaxrateService, StateprovinceService stateprovinceService) {
		super();
		this.addressRepository = addressRepository;
		this.countryregionRepository = countryregionRepository;
		this.salestaxrateRepository = salestaxrateRepository;
		this.stateprovinceRepository = stateprovinceRepository;
		this.addressService = addressService;
		this.countryregionService = countryregionService;
		this.salestaxrateService = salestaxrateService;
		this.stateprovinceService = stateprovinceService;
	}
	
	@Nested
	@Tag("SaveAddress")
	@TestMethodOrder(OrderAnnotation.class)
	public class SaveAddress {
		
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 5", addressSave.getAddressline1());
			assertEquals("Bogota", addressSave.getCity());
			assertEquals("760009", addressSave.getPostalcode());
			
			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
			
			assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
			//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongAddressline1() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1(null);
			address1.setCity("Bogota");
			address1.setPostalcode("7600089");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(3)
		public void saveTestWrongCity() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("a");
			address1.setPostalcode("76000");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		
		@Test
		@Order(4)
		public void saveTestWrongPostalcode() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("7600099");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(5)
		public void saveTestWrongStateprovince() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.save(address1, 20);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(6)
		public void saveTestWrongCityNull() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity(null);
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
			
		}
		
		@Test
		@Order(7)
		public void saveTestWrongCityEmpty() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(8)
		public void saveTestWrongPostalCodeNull() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode(null);
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(9)
		public void saveTestWrongPostalCodeEmpty() {
			//Set up
			Address address1 = new Address();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("");
			
			//Method
			Address addressSave =  addressService.save(address1, 1);
			
			//Asserts
			assertNull(addressSave);
		}
		
	}
	
	@Nested
	@Tag("UpdateAddress")
	@TestMethodOrder(OrderAnnotation.class)
	class UpdateAddress{
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			//Method
			Address addressSave =  addressService.update(address1, 1);
			
			//Asserts
			assertNotNull(addressSave);
			assertEquals("Calle 5", addressSave.getAddressline1());
			assertEquals("Bogota", addressSave.getCity());
			assertEquals("760009", addressSave.getPostalcode());

			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
			
			assertEquals(addressSave.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
			//assertEquals(stateprovinceSave.get().getAddresses().get(0).getAddressid(), addressSave.getAddressid());
			
		}
		
		@Test
		@Order(2)
		public void updateTestWrongAddressline1() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1(null);
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			//Asserts
			assertEquals(addressSave, null);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongCity() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("a");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 200);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(4)
		public void updateTestWrongPostalcode() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("7600099");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(5)
		public void updateTestWrongStateprovince() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 20);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(6)
		public void updateTestWrongNotFound() {
			//Set up
			Address address1 = new Address();
			address1.setAddressid(2);
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 200);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(7)
		public void updateTestWrongCityNull() {
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity(null);
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(8)
		public void updateTestWrongCityEmpty() {
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("");
			address1.setPostalcode("760009");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(9)
		public void updateTestWrongPostalCodeNull() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode(null);
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
		@Test
		@Order(10)
		public void updateTestWrongPostalCodeEmpty() {
			//Set up
			Address address1 = addressService.findById(1).get();
			address1.setAddressline1("Calle 5");
			address1.setCity("Bogota");
			address1.setPostalcode("");
			
			//Method
			Address addressSave =  addressService.update(address1, 2);
			
			//Asserts
			assertNull(addressSave);
		}
		
	}
	
	@Nested
	@Tag("SaveSalestaxrate")
	@TestMethodOrder(OrderAnnotation.class)
	public class SaveSalestaxrate{
		@Test
		@Order(1)
		public void saveTestCorrect() {
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			Salestaxrate saveSales = salestaxrateService.save(sales, 1);
			
			assertNotNull(saveSales);
			assertEquals(new BigDecimal("124567890.0987654321"), sales.getTaxrate());
			assertEquals("cinco", sales.getName());
			
			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
			
			assertEquals(saveSales.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongTaxRate() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(3)
		public void saveTestWrongTaxRateNull() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(null);
			sales.setName("cinco");
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(4)
		public void saveTestWrongName() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("tres");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(5)
		public void saveTestWrongNameNull() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName(null);
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(6)
		public void saveTestWrongNameEmpty() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(7)
		public void saveTestWrongStateprovince() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 300);
			
			assertNull(salesSave);
		}
		
	}
	
	@Nested
	@Tag("UpdateSalestaxrate")
	@TestMethodOrder(OrderAnnotation.class)
	public class UpdateSalestaxrate{
		
		@Test
		@Order(1)
		public void updateTestCorrect() {
			Salestaxrate sales = salestaxrateService.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			Stateprovince stateprovince1 = new Stateprovince();
			stateprovinceRepository.save(stateprovince1);
			
			Salestaxrate saveSales = salestaxrateService.update(sales, 1);
			
			assertNotNull(saveSales);
			assertEquals(new BigDecimal("124567890.0987654321"), sales.getTaxrate());
			assertEquals("cinco", sales.getName());
			
			Optional<Stateprovince> stateprovinceSave = stateprovinceRepository.findById(1);
			
			assertEquals(saveSales.getStateprovince().getStateprovinceid(), stateprovinceSave.get().getStateprovinceid());
		}
		
		@Test
		@Order(2)
		public void updateTestWrongTaxRate() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 2);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongTaxRateNull() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(null);
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 2);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(4)
		public void updateTestWrongName() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("tres");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 2);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(5)
		public void updateTestWrongNameNull() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName(null);
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 2);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(6)
		public void updateTestWrongNameEmpty() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 2);
			
			assertNull(salesSave);
		}
		
		@Test
		@Order(7)
		public void updateTestWrongStateprovince() {
			//Set up
			Salestaxrate sales = salestaxrateRepository.findById(1).get();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 300);
			
			assertNull(salesSave);
		}
		
	}
	
	@Nested
	@Tag("SaveStateprovince")
	@TestMethodOrder(OrderAnnotation.class)
	public class SaveStateprovince{
		
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set up
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Countryregion country = new Countryregion();
			countryregionRepository.save(country);
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNotNull(stateSave);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y", state.getIsonlystateprovinceflag());
			assertEquals("Venezuela", state.getName());
			
			Optional<Countryregion> countryregionSave = countryregionRepository.findById(1);
			
			assertEquals(stateSave.getCountryregion().getCountryregionid(), countryregionSave.get().getCountryregionid());
			
		}
		
		@Test
		@Order(2)
		public void saveTestWrongStateprovinceCode() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("1");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(3)
		public void saveTestWrongStateprovinceCodeEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(4)
		public void saveTestWrongStateprovinceCodeNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(5)
		public void saveTestWrongStateFlag() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(6)
		public void saveTestWrongFlagEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(7)
		public void saveTestWrongFlagNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(8)
		public void saveTestWrongName() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(9)
		public void saveTestWrongNameEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(10)
		public void saveTestWrongNameNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(11)
		public void saveTestWrongCountryRegion() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 300);
			
			assertNull(stateSave);
		}
		
	}
	
	@Nested
	@Tag("UpdateStateprovince")
	@TestMethodOrder(OrderAnnotation.class)
	public class UpdateStateprovince{
		
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set up
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Countryregion country = new Countryregion();
			countryregionRepository.save(country);
			
			assertNotNull(state);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y", state.getIsonlystateprovinceflag());
			assertEquals("Venezuela", state.getName());
			
			Optional<Countryregion> countryregionSave = countryregionRepository.findById(2);
			
			
		}
		
		@Test
		@Order(2)
		public void updateTestWrongStateprovinceCode() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongStateprovinceCodeEmpty() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(4)
		public void updateTestWrongStateprovinceCodeNull() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(5)
		public void updateTestWrongStateFlag() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(6)
		public void updateTestWrongStateFlagEmpty() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(7)
		public void updateTestWrongStateFlagNull() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(8)
		public void updateTestWrongName() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(9)
		public void updateTestWrongNameEmpty() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(10)
		public void updateTestWrongNameNull() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince stateSave = stateprovinceService.save(state, 2);
			
			assertNull(stateSave);
		}
		
		@Test
		@Order(11)
		public void updateTestWrongCountryRegion() {
			Stateprovince state = stateprovinceService.findById(1).get();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Colombia");
			
			Stateprovince stateSave = stateprovinceService.save(state, 200);
			
			assertNull(stateSave);
		}
		
	}
	
	@Nested
	@Tag("SaveCountry")
	@TestMethodOrder(OrderAnnotation.class)
	public class SaveCountry{
		
		@Test
		@Order(1)
		public void saveTestCorrect() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode("1234");
			country.setName("Colombia");
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNotNull(countrySave);
			assertEquals("1234", country.getCountryregioncode());
			assertEquals("Colombia", country.getName());
		}
		
		@Test
		@Order(2)
		public void saveTestWrongCountryCode() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode("");
			country.setName("Colombia");
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
			
		}
		
		@Test
		@Order(3)
		public void saveTestWrongCountryCodeNull() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode(null);
			country.setName("Colombia");
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
		}
		
		@Test
		@Order(4)
		public void saveTestWrongName() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode("1234");
			country.setName("Cali");
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
		}
		
		@Test
		@Order(5)
		public void saveTestWrongNameNull() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode("1234");
			country.setName(null);
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
		}
		
		
		@Test
		@Order(6)
		public void saveTestWrongNameEmpty() {
			//Set up
			Countryregion country = new Countryregion();
			country.setCountryregioncode("1234");
			country.setName("");
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
		}
		
		@Test
		@Order(7)
		public void saveTestWrongAll() {
			Countryregion country = new Countryregion();
			country.setCountryregioncode(null);
			country.setName(null);
			
			//Method
			Countryregion countrySave = countryregionService.save(country);
			
			assertNull(countrySave);
		}
		
	}
	
	@Nested
	@Tag("UpdateCountry")
	@TestMethodOrder(OrderAnnotation.class)
	public class UpdateCountry{
		
		@Test
		@Order(1)
		public void updateTestCorrect() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode("1234");
			country.setName("Colombia");
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNotNull(countryAux);
			assertEquals("1234", country.getCountryregioncode());
			assertEquals("Colombia", country.getName());
		}
		
		@Test
		@Order(2)
		public void updateTestWrongCountryCode() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode("");
			country.setName("Colombia");
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
		@Test
		@Order(3)
		public void updateTestWrongCountryCodeNull() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode(null);
			country.setName("Colombia");
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
		@Test
		@Order(4)
		public void updateTestWrongName() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode("1234");
			country.setName("Cali");
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
		@Test
		@Order(5)
		public void updateTestWrongNameNull() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode("1234");
			country.setName(null);
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
		@Test
		@Order(6)
		public void updateTestWrongNameEmpty() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode("1234");
			country.setName("");
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
		@Test
		@Order(7)
		public void updateTestWrongAll() {
			//Set up
			Countryregion country = countryregionRepository.findById(1).get();
			country.setCountryregioncode(null);
			country.setName(null);
			
			Countryregion countryAux = countryregionService.update(country);
			
			assertNull(countryAux);
		}
		
	}
	
	
}
	
