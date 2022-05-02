package com.icesi.taller1.boot;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.icesi.taller1.model.Address;
import com.icesi.taller1.model.Countryregion;
import com.icesi.taller1.model.Salestaxrate;
import com.icesi.taller1.model.Stateprovince;
import com.icesi.taller1.repository.AddressRepository;
import com.icesi.taller1.repository.CountryregionRepository;
import com.icesi.taller1.repository.SalestaxrateRepository;
import com.icesi.taller1.repository.StateprovinceRepository;

@SpringBootApplication
@EnableJpaRepositories("com.icesi.taller1.repository")
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.icesi.taller1.model"})
@ComponentScan(basePackages = {"com.icesi.taller1.repository","com.icesi.taller1.controller","com.icesi.taller1.service","com.icesi.taller1.authentication"} )
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner add(CountryregionRepository crp, AddressRepository adr, StateprovinceRepository spr, SalestaxrateRepository srp) {
		return (args) -> {
			
			
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("250");
			cr.setName("Colombia");
			crp.save(cr);
			
			Stateprovince state = new Stateprovince();
			state.setStateprovincecode("12345");
			state.setIsonlystateprovinceflag("Y");
			state.setName("Valle del Cauca");
			state.setCountryregion(cr);
			spr.save(state);
			
			
			Address adres = new Address();
			adres.setAddressline1("Cra 29 #10b - 118");
			adres.setAddressline2("Cra 29 #10b - 119");
			adres.setCity("Cali");
			adres.setPostalcode("123456");
			adres.setStateprovince(state);
			adr.save(adres);
			
			Salestaxrate sales = new Salestaxrate();
			sales.setTaxrate(new BigDecimal("124567890.0987654321"));
			sales.setName("cinco");
			sales.setStateprovince(state);
			srp.save(sales);
			
			
		};
	}

}
