package org.example.admin_demo_spring.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("배치 관리자 페이지 API")
				.description("관리자 페이지 회원을 관리 및 권한 배치 스케줄을 관리합니다.")
				.version("1.0.0"));
	}
}