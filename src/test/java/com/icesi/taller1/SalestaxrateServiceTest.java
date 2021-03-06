package com.icesi.taller1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import com.icesi.taller1.dao.SalestaxrateDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;
import com.icesi.taller1.service.SalestaxrateService;

@ContextConfiguration(classes = Taller1Application.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SalestaxrateServiceTest {
	
	@InjectMocks
	private SalestaxrateService salestaxrateService;
	@Mock
	private SalestaxrateDAO salestaxrateDAO;
	@Mock
	private StateprovinceDAO stateprovinceDAO;
	
	
	@Autowired
	public SalestaxrateServiceTest(SalestaxrateService salestaxrateService,
			SalestaxrateDAO salestaxrateDAO,StateprovinceDAO stateprovinceDAO) {
		
		this.salestaxrateService = salestaxrateService;
		this.salestaxrateDAO = salestaxrateDAO;
		this.stateprovinceDAO = stateprovinceDAO;
		
	}
	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNotNull(salesSave);
			assertEquals(new BigDecimal("124567890.0987654321"), sales.getTaxrate());
			assertEquals("cinco", sales.getName());
			assertEquals(salesSave.getStateprovince().getStateprovinceid(),salesSave.getStateprovince().getStateprovinceid());
			
		}
		
		
		@Test
		public void saveTestWrongTaxRate() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			
			verify(stateprovinceDAO,times(0)).findById(1);
			verify(salestaxrateDAO, times(0)).save(sales);
		}
		
		@Test
		public void saveTestWrongTaxRateNull() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(null);
			sales.setName("cinco");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			
			verify(stateprovinceDAO,times(0)).findById(1);
			verify(salestaxrateDAO, times(0)).save(sales);
		}

		
	    @Test
	    public void saveTestWrongName() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("tres");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			verify(stateprovinceDAO,times(0)).findById(1);
			verify(salestaxrateDAO, times(0)).save(sales);
	    }
	    
	    @Test
	    public void saveTestWrongNameEmpty() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("");
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			verify(stateprovinceDAO,times(0)).findById(1);
			verify(salestaxrateDAO, times(0)).save(sales);
	    }
	    
	    @Test
	    public void saveTestWrongNameNull() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName(null);
			
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			verify(stateprovinceDAO,times(0)).findById(1);
			verify(salestaxrateDAO, times(0)).save(sales);
	    }
	    
	    @Test
	    public void saveTestWrongStateProvince() {
	    	//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 10);
			
			assertNull(salesSave);
			
			verify(salestaxrateDAO, times(0)).save(sales);
			
	    }
		
	}
	
	@Nested
	@Tag("update")
	class Update {
		
		@Test
		public void updateTestCorrect() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			
			Salestaxrate salesAux = new Salestaxrate();
			Stateprovince stateprovince1 = new Stateprovince();
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNotNull(sales);
			assertEquals(new BigDecimal("124567890.0987654321"), sales.getTaxrate());
			assertEquals("cinco", sales.getName());
			}
		
		@Test
		public void updateTestWrongTaxRate() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("cinco");
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNull(salesSave);
			
			assertNotNull(salestaxrateDAO);
			verify(salestaxrateDAO,times(0)).save(sales);
			
		}
		
		@Test
		public void updateTestWrongTaxRateNull() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(null);
			sales.setName("cinco");

			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNull(salesSave);
			
			assertNotNull(salestaxrateDAO);
			verify(salestaxrateDAO,times(0)).save(sales);
		}
		
		@Test
		public void updateTestWrongName() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("tres");
		
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			

			assertNull(salesSave);
			
			assertNotNull(salestaxrateDAO);			
			verify(salestaxrateDAO,times(0)).save(sales);
			
		}
		
		@Test
		public void updateTestWrongNameNull() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName(null);

			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNull(salesSave);
			
			assertNotNull(salestaxrateDAO);
			verify(salestaxrateDAO,times(0)).save(sales);
		}
		
		@Test
		public void updateTestWrongEmpty() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("");
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			

			assertNull(salesSave);
			
			assertNotNull(salestaxrateDAO);
			verify(salestaxrateDAO,times(0)).save(sales);
		}
		
		
		@Test
		public void updateTestWrongStateProvince() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			Salestaxrate salesUpdate = salestaxrateService.update(sales, 10);
			
			assertNull(salesUpdate);

		}
		
	}
	

}
