package com.icesi.taller1.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.icesi.taller1.repository")
@EnableAutoConfiguration
@EntityScan(basePackages= {"com.icesi.taller1.model"})
@ComponentScan(basePackages = {"com.icesi.taller1.repository","com.icesi.taller1.service"} )
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}

}
