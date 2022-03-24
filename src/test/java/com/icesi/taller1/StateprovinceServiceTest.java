package com.icesi.taller1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.test.context.ContextConfiguration;

import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.StateprovinceRepository;
import com.icesi.taller1.service.StateprovinceService;

@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class StateprovinceServiceTest {
	@Mock
	private StateprovinceRepository stateprovinceRepository;
	@Mock
	private CountryregionRepository countryregionRepository;
	@InjectMocks
	private StateprovinceService stateprovinceService;

	
	@Nested
	@Tag("create")
	class Create {
		
		@Test
		public void saveTestCorrect() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Countryregion country = new Countryregion();
			
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(country));
			when(stateprovinceRepository.save(state)).thenReturn(state);
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNotNull(stateSave);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y", state.getIsonlystateprovinceflag());
			assertEquals("Venezuela", state.getName());
			
			verify(countryregionRepository).findById(1);
			verify(stateprovinceRepository).save(state);
		}
		
	}
}
