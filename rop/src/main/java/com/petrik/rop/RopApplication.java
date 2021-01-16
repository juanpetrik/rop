package com.petrik.rop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class RopApplication {

	public static void main(String[] args) {
		// run
		SpringApplication.run(RopApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		//
		return new ModelMapper();
	}

}
