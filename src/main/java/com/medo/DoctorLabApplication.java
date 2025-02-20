package com.medo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/solwyz", description = "Default Server URL") })
public class DoctorLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorLabApplication.class, args);
	}

}

