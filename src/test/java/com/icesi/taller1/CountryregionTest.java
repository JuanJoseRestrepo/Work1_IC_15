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
import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.service.CountryregionService;

@ContextConfiguration(classes = Taller1Application.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CountryregionTest {

	@Mock
	private CountryregionDAO countryregionDAO;
	
	@InjectMocks
	private CountryregionService countryregionService;
	
	
	@Autowired
	public CountryregionTest(CountryregionDAO countryregionDAO, CountryregionService countryregionService) {
		super();
		this.countryregionDAO = countryregionDAO;
		this.countryregionService = countryregionService;
	}

	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("1234");
			cr.setName("Colombia");
			
			
			Countryregion save = countryregionService.save(cr);
			
			assertNotNull(save);
			assertEquals("1234", save.getCountryregioncode());
			assertEquals("Colombia", save.getName());
			
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
			
			verify(countryregionDAO, times(0)).save(cr);
			
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
			
			verify(countryregionDAO, times(0)).save(cr);
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
			
			verify(countryregionDAO, times(0)).save(cr);
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
			
			verify(countryregionDAO, times(0)).save(cr);
			
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
			
			verify(countryregionDAO, times(0)).save(cr);
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
			
			verify(countryregionDAO, times(0)).save(cr);
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
			
			verify(countryregionDAO, times(0)).save(cr);
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
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNotNull(save);
			assertEquals("1234", save.getCountryregioncode());
			assertEquals("Colombia", save.getName());

		}
		
		
		@Test
		public void updateTestWrongCode() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("");
			cr.setName("Colombia");
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongCodeToMuch() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("012345");
			cr.setName("Colombia");
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongCodeNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode(null);
			cr.setName("Colombia");
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongName() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName("Cali");
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
			
		}
		
		@Test
		public void updateTestWrongNameNull() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName(null);
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongNameEmpty() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode("1234");
			cr.setName("");
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		@Test
		public void updateTestWrongAllParameters() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(1);
			cr.setCountryregioncode(null);
			cr.setName(null);
			
			
			Countryregion save = countryregionService.update(cr);
			
			assertNull(save);
			
			verify(countryregionDAO, times(0)).save(cr);
		}
		
		
	}
	
}
