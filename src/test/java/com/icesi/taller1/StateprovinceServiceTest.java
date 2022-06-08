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
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.StateprovinceRepository;
import com.icesi.taller1.service.StateprovinceService;

@ContextConfiguration(classes = Taller1Application.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StateprovinceServiceTest {
	@Mock
	private StateprovinceDAO stateprovinceDAO;
	@Mock
	private CountryregionDAO countryregionDAO;
	@InjectMocks
	private StateprovinceService stateprovinceService;

	@Autowired
	public StateprovinceServiceTest(StateprovinceDAO stateprovinceDAO,
			CountryregionDAO countryregionDAO, StateprovinceService stateprovinceService) {
		super();
		this.stateprovinceDAO = stateprovinceDAO;
		this.countryregionDAO = countryregionDAO;
		this.stateprovinceService = stateprovinceService;
	}

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
			
			Stateprovince stateSave = stateprovinceService.save(state, 1);
			
			assertNull(stateSave);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y", state.getIsonlystateprovinceflag());
			assertEquals("Venezuela", state.getName());
			

		}
		
		@Test
		public void saveTestWrongStateProvinceCode() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongStateProvinceCodeEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongStateProvinceCodeNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagState() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagStateNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongFlagStateEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongName() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongNameEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongNameNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince stateAux = stateprovinceService.save(state, 1);
			
			assertNull(stateAux);
			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO, times(0)).save(state);
		}
		
		@Test
		public void saveTestWrongCountryRegion() {
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince stateAux = stateprovinceService.save(state, 10);
			
			assertNull(stateAux);
			
			
			verify(stateprovinceDAO, times(0)).save(state);
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

			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			assertEquals("12345", state.getStateprovincecode());
			assertEquals("Y",state.getIsonlystateprovinceflag());
			assertEquals("Venezuela",state.getName());
			assertEquals(country, country);
		}
		
		@Test
		public void updateTestWrongStateProvinceCode() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("123");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongStateProvinceCodeEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongStateProvinceCodeNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode(null);
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagState() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("YES");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagStateEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongFlagStateNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag(null);
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongName() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Cali");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongNameEmpty() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("");
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);			
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongNameNull() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName(null);
			
			Stateprovince saveState = stateprovinceService.update(state, 1);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(countryregionDAO, times(0)).findById(1);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
		@Test
		public void updateTestWrongCountryRegion() {
			Stateprovince state = new Stateprovince();
			state.setStateprovinceid(1);
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Venezuela");
			
			Stateprovince saveState = stateprovinceService.update(state, 100);
			
			assertNull(saveState);
			
			assertNotNull(stateprovinceDAO);
			verify(stateprovinceDAO,times(0)).save(state);
		}
		
	}
	
}
