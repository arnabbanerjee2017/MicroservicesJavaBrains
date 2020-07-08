package com.arnab.movie.info.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurations {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
