package org.example.admin_demo_spring.config;

import org.example.admin_demo_spring.config.security.interceptor.SessionExpirationTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionExpirationTimeInterceptor());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}