package com.icesi.taller1.testDao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.model.sales.Salesterritory;
import com.icesi.taller1.repository.SalesTerritoryRepository;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestStateprovinceDAO {

	@Autowired
	private StateprovinceDAO stateprovinceDAO;
	
	@Autowired
	private CountryregionDAO countryrregionDAO;
	@Autowired
	private SalesTerritoryRepository salesterritoryRepository;
	
	private Stateprovince stateprovince;
	
	@Autowired
	public TestStateprovinceDAO(StateprovinceDAO stateprovinceDAO, 
			CountryregionDAO countryrregionDAO,SalesTerritoryRepository salesterritoryRepository) {
		super();
		this.stateprovinceDAO = stateprovinceDAO;
		this.countryrregionDAO = countryrregionDAO;
		this.salesterritoryRepository = salesterritoryRepository;
	}
	void initDao() { 
		stateprovince = new Stateprovince();
		stateprovince.setName("Valle del Cauca");
		stateprovince.setStateprovincecode("12345");
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findIdTestStateprovinceDAO() {
		initDao();
		assertNotNull(stateprovinceDAO);
		stateprovinceDAO.save(stateprovince);
		
		assertEquals(stateprovinceDAO.findById(stateprovince.getStateprovinceid()),stateprovince);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTestStateprovince(){
		initDao();
		assertNotNull(stateprovinceDAO);
		stateprovinceDAO.save(stateprovince);

		assertTrue(stateprovinceDAO.findById(stateprovince.getStateprovinceid()).equals(stateprovince));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTestStateprovince() {
		initDao();
		assertNotNull(stateprovinceDAO);
		stateprovinceDAO.save(stateprovince);
		stateprovince.setName("Antioquia");
		stateprovince.setStateprovincecode("98765");

		stateprovinceDAO.update(stateprovince);

		Stateprovince edited = stateprovinceDAO.findById(stateprovince.getStateprovinceid());


		assertAll(
				() -> assertEquals(edited.getName(),"Antioquia"),
				() -> assertEquals(edited.getStateprovincecode(),"98765")
				);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTestStateprovince() {
		initDao();
		assertNotNull(stateprovinceDAO);
		stateprovinceDAO.save(stateprovince);

		stateprovinceDAO.delete(stateprovince);

		assertNull(stateprovinceDAO.findById(stateprovince.getStateprovinceid()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllAddressesTestStateprovince() {
		initDao();
		assertNotNull(stateprovinceDAO);
		stateprovinceDAO.save(stateprovince);

		assertEquals(stateprovinceDAO.findAll().size(), 2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getNameTestStateprovince() {
		initDao();
		stateprovinceDAO.save(stateprovince);

		stateprovince.setName("Valle del Cauca");
		stateprovince.setStateprovincecode("22333");

		stateprovinceDAO.save(stateprovince);

		List<Stateprovince> results = stateprovinceDAO.getStateprovinceByName("Valle del Cauca");
		assertEquals(stateprovinceDAO.findAll().size(), 2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getCountryregionTestStateprovince() {
		initDao();
		Countryregion cr = new Countryregion();
		cr = new Countryregion();
		cr.setName("Colombia");
		cr.setCountryregioncode("C12");

		countryrregionDAO.save(cr);

		stateprovince.setCountryregion(cr);
		stateprovinceDAO.save(stateprovince);

		stateprovince = new Stateprovince();
		stateprovince.setName("Antioquia");
		stateprovince.setStateprovincecode("98765");
		
		stateprovince.setCountryregion(cr);
		stateprovinceDAO.save(stateprovince);
		
		List<Stateprovince> results = stateprovinceDAO.getStateprovinceById(cr.getCountryregionid());
		assertEquals(results.size(), 2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getTerritoryidTestStateprovince() {
		initDao();
		Salesterritory st = new Salesterritory();
		st = new Salesterritory();
    	st.setName("Zona del pacifico");
    	
    	salesterritoryRepository.save(st);
    	
    	stateprovince.setTerritoryid(st.getTerritoryid());
    	stateprovinceDAO.save(stateprovince);
		
    	stateprovince = new Stateprovince();
    	stateprovince.setName("Antioquia");
    	stateprovince.setStateprovincecode("98765");
    	stateprovince.setTerritoryid(st.getTerritoryid());
		
    	stateprovinceDAO.save(stateprovince);
		
		List<Stateprovince> results = stateprovinceDAO.getStateprovinceByTerritoryId(st.getTerritoryid());
		assertEquals(results.size(), 2);
	}
	
	
	
}
