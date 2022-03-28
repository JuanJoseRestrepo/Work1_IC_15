package com.icesi.taller1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import org.springframework.test.context.ContextConfiguration;

import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;
import com.icesi.taller1.service.SalestaxrateService;

@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class SalestaxrateServiceTest {
	
	@InjectMocks
	private SalestaxrateService salestaxrateService;
	@Mock
	private SalestaxrateRepository salestaxrateRepository;
	@Mock
	private StateprovinceRepository stateprovinceRepository;
	
	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			Stateprovince stateprovince1 = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(salestaxrateRepository.save(sales)).thenReturn(sales);
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNotNull(salesSave);
			assertEquals(new BigDecimal("124567890.0987654321"), sales.getTaxrate());
			assertEquals("cinco", sales.getName());
			assertEquals(stateprovince1.getStateprovinceid(), salesSave.getStateprovinceid());
			
			verify(stateprovinceRepository).findById(1);
			verify(salestaxrateRepository).save(sales);
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
			
			verify(stateprovinceRepository,times(0)).findById(1);
			verify(salestaxrateRepository, times(0)).save(sales);
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
			
			verify(stateprovinceRepository,times(0)).findById(1);
			verify(salestaxrateRepository, times(0)).save(sales);
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
			verify(stateprovinceRepository,times(0)).findById(1);
			verify(salestaxrateRepository, times(0)).save(sales);
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
			verify(stateprovinceRepository,times(0)).findById(1);
			verify(salestaxrateRepository, times(0)).save(sales);
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
			verify(stateprovinceRepository,times(0)).findById(1);
			verify(salestaxrateRepository, times(0)).save(sales);
	    }
	    
	    @Test
	    public void saveTestWrongStateProvince() {
	    	//Set up
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			//Method
			Salestaxrate salesSave = salestaxrateService.save(sales, 1);
			
			assertNull(salesSave);
			
			verify(salestaxrateRepository, times(0)).save(sales);
			
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
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(salesAux));
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateprovince1));
			when(salestaxrateRepository.save(sales)).thenReturn(sales);
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNotNull(salesSave);
			assertEquals(new BigDecimal("124567890.0987654321"), salesSave.getTaxrate());
			assertEquals("cinco", salesSave.getName());
			
			verify(salestaxrateRepository).findById(1);
			verify(stateprovinceRepository).findById(1);
			verify(salestaxrateRepository).save(sales);
		}
		
		@Test
		public void updateTestWrongTaxRate() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321").negate());
			sales.setName("cinco");
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNull(salesSave);
			
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
			
		}
		
		@Test
		public void updateTestWrongTaxRateNull() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(null);
			sales.setName("cinco");
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			
			assertNull(salesSave);
			
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
		}
		
		@Test
		public void updateTestWrongName() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("tres");
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			

			assertNull(salesSave);
			
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
			
		}
		
		@Test
		public void updateTestWrongNameNull() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName(null);
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			

			assertNull(salesSave);
			
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
		}
		
		@Test
		public void updateTestWrongEmpty() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("");
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesSave = salestaxrateService.update(sales, 1);
			

			assertNull(salesSave);
			
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
		}
		
		
		@Test
		public void updateTestWrongStateProvince() {
			Salestaxrate sales = new Salestaxrate();
			sales.setSalestaxrateid(1);
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			
			Salestaxrate saleAux = new Salestaxrate();
			
			when(salestaxrateRepository.findById(1)).thenReturn(Optional.of(saleAux));
			
			
			Salestaxrate salesUpdate = salestaxrateService.update(sales, 1);
			
			assertNull(salesUpdate);
			verify(salestaxrateRepository).findById(1);
			verify(salestaxrateRepository,times(0)).save(sales);
		}
		
	}
	

}
