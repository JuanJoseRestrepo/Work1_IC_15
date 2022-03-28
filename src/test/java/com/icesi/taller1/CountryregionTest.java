package com.icesi.taller1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.service.CountryregionService;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(MockitoExtension.class)
public class CountryregionTest {

	@Mock
	private CountryregionRepository countryregionRepository;
	
	@InjectMocks
	private CountryregionService countryregionService;
	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("1234");
			cr.setName("Colombia");
			
			when(countryregionRepository.save(cr)).thenReturn(cr);
			
			Countryregion save = countryregionService.save(cr);
			
			assertNotNull(save);
			assertEquals("1234", save.getCountryregioncode());
			assertEquals("Colombia", save.getName());
			
			verify(countryregionRepository).save(cr);
		}
		
		@Test
		public void saveTestWrongCode() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("");
			cr.setName("Colombia");

			//Method
			Countryregion save = countryregionService.save(cr);
			
	
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
			
		}
		
		@Test
		public void saveTestWrongCodeToMuch() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("012345");
			cr.setName("Colombia");

			//Method
			Countryregion save = countryregionService.save(cr);
			
	
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void saveTestWrongCodeNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode(null);
			cr.setName("Colombia");

			//Method
			Countryregion save = countryregionService.save(cr);
			
	
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void saveTestWrongName() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("1234");
			cr.setName("Cali");
			
			//Method
			Countryregion save = countryregionService.save(cr);
			
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
			
		}
		
		@Test
		public void saveTestWrongNameNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("1234");
			cr.setName(null);
			
			//Method
			Countryregion save = countryregionService.save(cr);
			
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void saveTestWrongEmpty() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("1234");
			cr.setName("");
			
			//Method
			Countryregion save = countryregionService.save(cr);
			
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void saveTestWrongAllParameters() {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode(null);
			cr.setName(null);
			
			//Method
			Countryregion save = countryregionService.save(cr);
			
			//Asserts
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
	}
	
	@Nested
	@Tag("update")
	class Update {
		
		@Test
		public void updateTestCorrect() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName("Colombia");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			when(countryregionRepository.save(cr)).thenReturn(cr);
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNotNull(save);
			assertEquals("1234", save.getCountryregioncode());
			assertEquals("Colombia", save.getName());
		
			verify(countryregionRepository).save(cr);
		}
		
		
		@Test
		public void updateTestWrongCode() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("");
			cr.setName("Colombia");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongCodeToMuch() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("012345");
			cr.setName("Colombia");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongCodeNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode(null);
			cr.setName("Colombia");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongName() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName("Cali");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
			
		}
		
		@Test
		public void updateTestWrongNameNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName(null);
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongNameEmpty() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName("");
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongAllParameters() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode(null);
			cr.setName(null);
			
			Countryregion crAux = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(crAux));
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionRepository, times(0)).save(cr);
		}
		
		
	}
	
}
