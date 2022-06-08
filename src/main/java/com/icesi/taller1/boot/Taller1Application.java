package com.icesi.taller1.boot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Employee;
import com.icesi.taller1.model.Person;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.model.sales.Salesorderheader;
import com.icesi.taller1.repository.AddressRepository;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.EmployeeRepository;
import com.icesi.taller1.repository.PersonRepository;
import com.icesi.taller1.repository.SalesorderheaderRepository;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;


@SpringBootApplication
@EnableJpaRepositories("com.icesi.taller1.repository")
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.icesi.taller1.model", "com.icesi.taller1.model.sales", "com.icesi.taller1.purchase"})
@ComponentScan(basePackages = {"com.icesi.taller1.repository","com.icesi.taller1.controller","com.icesi.taller1.service","com.icesi.taller1.authentication", "com.icesi.taller1.dao", "com.icesi.taller1.rest"} )
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(CountryregionRepository crp, AddressRepository adr, StateprovinceRepository spr, SalestaxrateRepository srp,
			PersonRepository pr, EmployeeRepository ep, SalesorderheaderRepository shr) {
		return (args) -> {
			
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("250");
			cr.setName("Colombia");
			crp.save(cr);
			
			Countryregion country = new Countryregion();
			country.setCountryregioncode("130");
			country.setName("Cucuta");
			crp.save(country);
			
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Valle del Cauca");
			state.setCountryregion(cr);
			spr.save(state);
			
			
			Stateprovince estado = new Stateprovince();
			estado.setStateprovincecode("12346");
			estado.setIsonlystateprovinceflag("N");
			estado.setName("Colombia");
			estado.setCountryregion(cr);
			spr.save(estado);
			
			Address adres = new Address();
			adres.setAddressline1("Cra 29 #10b - 118");
			adres.setAddressline2("Cra 29 #10b - 119");
			adres.setCity("Cali");
			adres.setPostalcode("123456");
			adres.setStateprovince(state);
			Salesorderheader salesorder1 = new Salesorderheader();
			Salesorderheader salesorder2 = new Salesorderheader();
			Salesorderheader salesorder3 = new Salesorderheader();
			
			salesorder1.setShiptoaddress(adres);
			salesorder2.setShiptoaddress(adres);
			salesorder3.setShiptoaddress(adres);
			
			adr.save(adres);
			
			shr.save(salesorder1);
			shr.save(salesorder2);
			shr.save(salesorder3);
			
			Address adres2 = new Address();
			adres2.setAddressline1("Calle 18 #100-20");
			adres2.setAddressline2("Address2");
			adres2.setCity("Cundinamarca");
			adres2.setPostalcode("245987");
			adres2.setStateprovince(estado);
			Salesorderheader salesorder4 = new Salesorderheader();
			Salesorderheader salesorder5 = new Salesorderheader();
			Salesorderheader salesorder6 = new Salesorderheader();
			
			salesorder4.setShiptoaddress(adres2);
			salesorder5.setShiptoaddress(adres2);
			salesorder6.setShiptoaddress(adres2);
			
			adr.save(adres2);
			
			shr.save(salesorder4);
			shr.save(salesorder5);
			shr.save(salesorder6);
			
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			sales.setStateprovince(state);
			srp.save(sales);
			
			Person persona = new Person();
			persona.setFirstname("Juan Jose");
			persona.setLastname("Restrepo");
			pr.save(persona);
			
			
			Employee empleado = new Employee();
			empleado.setNationalidnumber("1005976323");
			empleado.setJobtitle("Ing de sistemas");
			empleado.setPerson(persona);
			ep.save(empleado);
		};
	}
	
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
