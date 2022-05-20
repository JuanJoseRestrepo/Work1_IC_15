package com.icesi.taller1.testDao;

import java.math.BigDecimal;

import org.junit.jupiter.api.Nested;
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
import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.dao.SalestaxrateDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.StateprovinceRepository;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestIntegrationDAO {

	@Autowired
	private StateprovinceDAO stateprovinceDAO;
	@Autowired
	private CountryregionDAO countryrregionDAO;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private SalestaxrateDAO salestaxrateDAO;
	
	@Autowired
	private StateprovinceRepository stateprovinceRepository;
	
	private Salestaxrate salestaxrate;
	
	private Stateprovince stateprovince;
	
	private Address address;
	
	private Countryregion countryregion;
	
	@Autowired
	public TestIntegrationDAO(StateprovinceDAO stateprovinceDAO,CountryregionDAO countryrregionDAO,
			AddressDAO addressDAO,SalestaxrateDAO salestaxrateDAO) {
		this.addressDAO = addressDAO;
		this.stateprovinceDAO = stateprovinceDAO;
		this.countryrregionDAO = countryrregionDAO;
		this.salestaxrateDAO = salestaxrateDAO;
	}
	
	public void initDao() {
		salestaxrate = new Salestaxrate();
		salestaxrate.setTaxrate(new BigDecimal("124567890.0987654321"));
		salestaxrate.setName("cinco");

		stateprovince = new Stateprovince();
		stateprovince.setName("Valle del Cauca");
		stateprovince.setStateprovincecode("12345");
		
		address = new Address();
		address.setAddressline1("Cra 29a #10b - 118");
		address.setCity("Cali");
		address.setAddressline2("Address2");
		address.setPostalcode("770014");

		countryregion = new Countryregion();
		countryregion.setCountryregioncode("1234");
		countryregion.setName("Colombia");
	}
	
	
	@Nested
	@Tag("TestCountryregion")
	class DaoTestCountryregion{
		
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void saveTestCorrect() {
			initDao();
			
			stateprovinceDAO.save(stateprovince);
			
			Address addressSave = addressDAO.save(address);
			
			
		}
		
	}
	
}
