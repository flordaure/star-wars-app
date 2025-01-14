package com.conexaproject.star_wars_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@OpenAPIDefinition(servers = {@Server(url = "${base.url}")})
@ComponentScan(basePackages = {"com.conexaproject.star_wars_app"})
public class StarWarsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsAppApplication.class, args);
	}

}
