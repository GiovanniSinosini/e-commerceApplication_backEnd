package com.giosinosini.springboot3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giosinosini.springboot3.domain.Payment_BankSlip;
import com.giosinosini.springboot3.domain.Payment_Card;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(Payment_BankSlip.class);
				objectMapper.registerSubtypes(Payment_Card.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}