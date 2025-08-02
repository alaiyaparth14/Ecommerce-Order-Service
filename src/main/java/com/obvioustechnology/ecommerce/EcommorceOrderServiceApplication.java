package com.obvioustechnology.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title="Ecommerce Order Service Rest Api Documentation",
				description="Ecommerce Order Service Rest Api",
				version="v1",				contact=@Contact(
				name="Parth Alaiya",
				email = "thakkarparth496@gmail.com"
		)
		)
)
@SpringBootApplication
public class EcommorceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommorceOrderServiceApplication.class, args);
	}

}
