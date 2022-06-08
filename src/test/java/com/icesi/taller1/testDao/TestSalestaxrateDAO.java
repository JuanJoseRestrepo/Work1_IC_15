package com.icesi.taller1.testDao;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

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
import com.icesi.taller1.dao.AddressDAO;
import com.icesi.taller1.dao.SalestaxrateDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.model.sales.Salesterritory;
import com.icesi.taller1.repository.SalesTerritoryRepository;
import com.icesi.taller1.service.SalesterritoryService;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestSalestaxrateDAO {
	
	 @Autowired
	 private SalestaxrateDAO salestaxrateDAO;
	 
	 @Autowired
	 private StateprovinceDAO stateprovinceDAO;
	 
	 private Salestaxrate salestaxrate;
	 
	 //private Salesterritory salesterritory;

	 @Autowired
	 public TestSalestaxrateDAO(SalestaxrateDAO salestaxrateDAO, StateprovinceDAO stateprovinceDAO) {
		super();
		this.salestaxrateDAO = salestaxrateDAO;
		this.stateprovinceDAO = stateprovinceDAO;
	}


	void initDao() {
			salestaxrate = new Salestaxrate();
			salestaxrate.setTaxrate(new BigDecimal("124567890.0987654321"));
			salestaxrate.setName("cinco");
			
	 }
	 
	 
	 @Nested
		@Tag("TestSalestaxrate")
		class DaoTestSalestaxrate{
		 
		 @Test
		 @Order(1)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void saveTestSalestaxrate() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 assertTrue(salestaxrateDAO.findById(salestaxrate.getSalestaxrateid()).equals(salestaxrate));
			 
		 }
		 
		 @Test
		 @Order(2)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void updateTestSalestaxrateDAO() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			 salestaxrate.setTaxrate(new BigDecimal("9087654321.0987654321"));
			 salestaxrate.setName("casita");
			 
			 salestaxrateDAO.update(salestaxrate);
			 
			 Salestaxrate salestaxrateChanged = salestaxrateDAO.findById(salestaxrate.getSalestaxrateid());
			 
				assertAll(                
						() -> assertEquals(salestaxrateChanged.getTaxrate(),new BigDecimal("9087654321.0987654321")),
						() -> assertEquals(salestaxrateChanged.getName(),"casita")
						);
		 }
		 
		 @Test
		 @Order(3)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void deleteTestSalestaxrateDAO() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 salestaxrateDAO.delete(salestaxrate);
			 
			 Salestaxrate delete = salestaxrateDAO.findById(salestaxrate.getSalestaxrateid());

			 assertNull(delete);
		
		 }
		 
		 @Test
		 @Order(4)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void findAllTestSalestaxrateDAO() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			assertEquals(salestaxrateDAO.findAll().size(), 1);

		 }
		 
		 @Test
		 @Order(5)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void getSalestaxrateByStateprovinceTestDAO() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 assertNotNull(stateprovinceDAO);
			 
			 Stateprovince state = new Stateprovince();
			 stateprovinceDAO.save(state);
				
			 salestaxrate.setStateprovince(state);
				
			 salestaxrateDAO.save(salestaxrate);
			 
			 
			 List<Salestaxrate> lisSales =  salestaxrateDAO.getSalestaxrateByStateprovince(state.getStateprovinceid());
			 
			 assertEquals(lisSales.size(), 1);
			 
		 }
		 
		 @Test
		 @Order(6)
		 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		 void getSalestaxrateByNameTestDAO() {
			 initDao();
			 assertNotNull(salestaxrateDAO);
			 salestaxrateDAO.save(salestaxrate);
			 
			 assertEquals(salestaxrateDAO.getSalestaxrateByName(salestaxrate.getName()).size(),1);
			 
		 }
		 
		 
	 }

}
