package com.icesi.taller1.testDao;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.model.sales.Salesorderheader;
import com.icesi.taller1.repository.SalesorderheaderRepository;



@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestAddressDAO {
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private StateprovinceDAO stateprovinceDAO;
	
	private SalesorderheaderRepository salesorderheaderRepository;
	
	private Address address;
	
	@Autowired
	public TestAddressDAO(AddressDAO addressDAO, StateprovinceDAO stateprovinceDAO,
			SalesorderheaderRepository salesorderheaderRepository) {
		super();
		this.addressDAO = addressDAO;
		this.stateprovinceDAO = stateprovinceDAO;
		this.salesorderheaderRepository = salesorderheaderRepository;
	}
	
	void initDao() {
		address = new Address();
		address.setAddressline1("Cra 29a #10b - 118");
		address.setCity("Cali");
		address.setAddressline2("Address2");
		address.setPostalcode("770014");
	}


	@Nested
	@Tag("Test")
	class DaoTestAddress{
		@Test
		@Order(1)
		void saveTestDaoAddress() {
				initDao();
				assertNotNull(addressDAO);
				addressDAO.save(address);
				assertTrue(addressDAO.findById(address.getAddressid()).equals(address));
		}
		
		@Test
		@Order(2)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void updateTestDaoAddress() {
				initDao();
				assertNotNull(addressDAO);
				addressDAO.save(address);
				
				address.setAddressline1("Calle 15 #121-25");
				address.setCity("Cali");
				address.setPostalcode("760008");
				
				addressDAO.update(address);
				
				Address changed = addressDAO.findById(address.getAddressid());
				
				assertAll(                
						() -> assertEquals(changed.getAddressline1(),"Calle 15 #121-25"),
						() -> assertEquals(changed.getCity(),"Cali"),                 
						() -> assertEquals(changed.getPostalcode(),"760008")
						);
		}
		
		@Test
		@Order(3)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void deleteTestDaoAddress() {
			initDao();
			assertNotNull(addressDAO);
			addressDAO.save(address);
			addressDAO.delete(address);
			
			Address delete = addressDAO.findById(address.getAddressid());
			
			assertNull(delete);
		}
		
		
		@Test
		@Order(4)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAllTestDaoAddress() {
			initDao();
			assertNotNull(addressDAO);
			addressDAO.save(address);
			
			assertEquals(addressDAO.findAll().size(), 1);
			
		}
		
		
		@Test
		@Order(5)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findStateProvinceTestDaoAddress() {
			initDao();
			assertNotNull(addressDAO);
			assertNotNull(stateprovinceDAO);
			
			Stateprovince state = new Stateprovince();
			stateprovinceDAO.save(state);
			
			address.setStateprovince(state);
			
			addressDAO.save(address);
			
			List<Address> listAddress = addressDAO.getAddressByStateprovinceId(state.getStateprovinceid());
			
			assertEquals(listAddress.size(), 1);
		}
		
		@Test
		@Order(6)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findCityTestDaoAddress() {
			initDao();
			assertNotNull(addressDAO);
			addressDAO.save(address);
			
			Address aux = new Address();
			aux.setAddressline1("Calle 15 #121-25");
			aux.setCity("Cali");
			aux.setPostalcode("760008");
			
			addressDAO.save(aux);
			
			List<Address> listAddress = addressDAO.getAddressByCity("Cali");
			
			assertEquals(listAddress.size(), 2);
	
		}
		
		@Test
		@Order(7)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAddressTestDaoAddress() {
			initDao();
			assertNotNull(addressDAO);
			addressDAO.save(address);
			
			assertEquals(addressDAO.findById(address.getAddressid()), address);
		}
		
		@Test
		@Order(8)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void getListAddressByAlmostTwoHeadsBySales() {
			initDao();
			assertNotNull(addressDAO);
			Salesorderheader salesorder1 = new Salesorderheader();
			Salesorderheader salesorder2 = new Salesorderheader();
			
			salesorder1.setShiptoaddress(address);
			salesorder2.setShiptoaddress(address);
			
			salesorderheaderRepository.save(salesorder1);
			salesorderheaderRepository.save(salesorder2);
			
			addressDAO.save(address);
			
			List<Address> address = addressDAO.getListAddressByAlmostTwoHeadsBySales();
			
			assertEquals(1, address.size());
			
		}
		
	}
	
		

}