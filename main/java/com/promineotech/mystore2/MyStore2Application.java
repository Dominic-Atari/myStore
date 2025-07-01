package com.promineotech.mystore2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;


@SpringBootApplication
@ComponentScan(basePackages = {"com.promineotech.mystore2", "mystore2.resetPassword"})
public class MyStore2Application {

	public static void main(String[] args) {
		
		SpringApplication.run(MyStore2Application.class, args);
	
		
		}
	
	@Bean
	public LayoutDialect layoutDialect() {
	  return new LayoutDialect();
	
	}

}
