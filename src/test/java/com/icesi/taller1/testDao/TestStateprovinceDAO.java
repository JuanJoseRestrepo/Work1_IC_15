package com.icesi.taller1.testDao;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.taller1.boot.Taller1Application;
import com.icesi.taller1.dao.CountryregionDAO;
import com.icesi.taller1.dao.StateprovinceDAO;
import com.icesi.taller1.model.Stateprovince;

@ContextConfiguration(classes = Taller1Application.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestStateprovinceDAO {

	@Autowired
	private StateprovinceDAO stateprovinceDAO;
	
	@Autowired
	private CountryregionDAO countryrregionDAO;
	
	private Stateprovince stateprovince;
	
	void initDao() { 
		stateprovince = new Stateprovince();
		stateprovince.setName("Valle del Cauca");
		stateprovince.setStateprovincecode("12345");
	}
	
	
	
	
	
}
