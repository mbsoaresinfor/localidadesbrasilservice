package br.com.mbs.localidadesbrasilservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BeanConfiguration {

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();		
//		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		return restTemplate;
	}

}
