package com.icesi.taller1.testDao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.model.Countryregion;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestCountryregionDAO {
	
	@Autowired
	private CountryregionDAO countryregionDAO;
	
	private Countryregion countryregion;
	
	@Autowired
	public TestCountryregionDAO(CountryregionDAO countryregionDAO) {
		super();
		this.countryregionDAO = countryregionDAO;
	}

	void initDao() { 
		countryregion = new Countryregion();
		countryregion.setCountryregioncode("1234");
		countryregion.setName("Colombia");
	}
	
	@Nested
	@Tag("TestCountryregion")
	class DaoTestCountryregion{
		
	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTestCountryregion() {
		initDao();
		assertNotNull(countryregionDAO);
		countryregionDAO.save(countryregion);
		assertTrue(countryregionDAO.findById(countryregion.getCountryregionid()).equals(countryregion));
	}	
		
	@Test
	@Order(2)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTestCountryregion() {
		initDao();
		assertNotNull(countryregionDAO);
		
		countryregionDAO.save(countryregion);
		
		countryregion.setName("Bogota");
    	countryregion.setCountryregioncode("1344");
  
    	countryregionDAO.update(countryregion);
    	
    	Countryregion countryregionEdited = countryregionDAO.findById(countryregion.getCountryregionid());
    	
    	assertAll(
    			() -> assertEquals(countryregionEdited.getName(),"Bogota"),
    			() -> assertEquals(countryregionEdited.getCountryregioncode(),"1344")
    	);
    	
	}
	@Test
	@Order(3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTestCountryregion() {
		initDao();
		countryregionDAO.save(countryregion);
		
		countryregionDAO.delete(countryregion);
		
		assertNull(countryregionDAO.findById(countryregion.getCountryregionid()));
		
	}
	
	@Test
	@Order(4)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllTestCountryregion() {
		initDao();
		countryregionDAO.save(countryregion);
		
		assertEquals(countryregionDAO.findAll().size(), 2);
	}
	
	@Test
	@Order(5)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findCountryIdTestCountryregion() {
		initDao();
		countryregionDAO.save(countryregion);
		assertTrue(countryregionDAO.findById(countryregion.getCountryregionid()).equals(countryregion));
	}
	
  }
	
}
