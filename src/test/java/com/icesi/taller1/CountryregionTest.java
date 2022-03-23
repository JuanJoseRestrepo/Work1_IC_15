package com.icesi.taller1;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CountryregionTest {

	@Mock
	private CountryregionRepository countryregionRepository;
	
	@InjectMocks
	private CountryregionService countryregionService;
	
	@Nested
	@Tag("create2")
	class Create2 {
		
		@Test
		public void saveTestCorrect() {
			//Set up
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("code");
			cr.setName("cinco");
			
			when(countryregionRepository.save(cr)).thenReturn(cr);
			
			Countryregion save = countryregionService.save(cr);
			
			assertNotNull(save);
			assertEquals("code", save.getCountryregioncode());
			assertEquals("cinco", save.getName());
			
			verify(countryregionRepository).save(cr);
		}
	}
	
}
