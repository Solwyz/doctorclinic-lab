package com.solwyz.doctorlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/doctorclinic", description = "Default Server URL") })
public class DoctorLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorLabApplication.class, args);
	}

	@Bean(name = "neocorpBCryptPasswordEncoder")
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
