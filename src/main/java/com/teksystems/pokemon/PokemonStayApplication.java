package com.teksystems.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PokemonStayApplication extends SpringBootServletInitializer {

	@Override	//TODO is this necessary?
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PokemonStayApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PokemonStayApplication.class, args);
	}

}