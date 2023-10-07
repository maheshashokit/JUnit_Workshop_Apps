package com.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "vendor Rest API Development", version = "1.0.0", description = "AshokIT Developed vendor Rest API", termsOfService = "All Rights are Reserved....", contact = @Contact(name = "Mahesh", email = "mahesh.ashokit@gmail.com"), license = @License(name = "License", url = "www.ashokitech.com")))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
