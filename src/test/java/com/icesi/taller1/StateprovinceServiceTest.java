package com.icesi.taller1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
		
		@Test
		public void saveTestWrongStateProvinceCode() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongStateProvinceCodeEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongStateProvinceCodeNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagState() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagStateNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagStateEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongName() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongNameEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongNameNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongCountryRegion() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			
			verify(stateprovinceRepository, times(0)).save(state);
		}
		
	}
	
	@Nested
	@Tag("update")
	class Update {
		
		@Test
		public void updateTestCorrect() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			Countryregion country = new Countryregion();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			when(countryregionRepository.findById(1)).thenReturn(Optional.of(country));
			when(stateprovinceRepository.save(state)).thenReturn(state);
			
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNotNull(saveState);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y",state.getIsonlystateprovinceflag());
			assertEquals("Venezuela",state.getName());
			assertEquals(country, state.getCountryregion());
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository).findById(1);
			verify(stateprovinceRepository).save(state);
		}
		
		@Test
		public void updateTestWrongStateProvinceCode() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongStateProvinceCodeEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongStateProvinceCodeNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagState() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagStateEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagStateNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongName() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongNameEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongNameNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(countryregionRepository, times(0)).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongCountryRegion() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = new Stateprovince();
			
			when(stateprovinceRepository.findById(1)).thenReturn(Optional.of(stateAux));
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			verify(stateprovinceRepository).findById(1);
			verify(stateprovinceRepository,times(0)).save(state);
		}
		
	}
	
}
