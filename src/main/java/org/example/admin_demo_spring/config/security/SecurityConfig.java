package org.example.admin_demo_spring.config.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;



import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


	private final CustomAuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 암호 설정
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인가 정책
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(
					Arrays.asList(
						"https://devxpvoice.xperp.co.kr"
						, "https://xpvoice.xperp.co.kr"
						, "http://211.252.81.158"          // XP보이스 CS 프로그램
						, "https://211.252.81.158"         // XP보이스 CS 프로그램
						, "https://aegis-hub.apti.co.kr" // 아파트 아이 PROD
						, "http://34.22.86.88"           // 아프타 아이 DEV

					)
				);
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(List.of("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}))

			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(
				session ->
					//스프링 시큐리티가 필요 시 생성(default)
					session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				// .invalidSessionUrl("/session-check")
			)
			.authorizeHttpRequests((request) -> request

				// .requestMatchers("/board/manager/**")
				// .hasAnyAuthority(RoleStatus.ADMIN.name())
				//
				// .requestMatchers("/voice-board/manager/**")
				// .hasAnyAuthority(RoleStatus.ADMIN.name())

				// .requestMatchers("/admin/banner/**")
				// .hasAuthority(RoleStatus.ADMIN.name())
				// .requestMatchers("/admin/popup/**")
				// .hasAuthority(RoleStatus.ADMIN.name())
				// .requestMatchers("/admin/login-info/**")
				// .hasAuthority(RoleStatus.ADMIN.name())
				// .requestMatchers("/admin/**")

				.requestMatchers("/static/**").permitAll() //정적 리소스 명시적으로 허용
				.anyRequest()
				.permitAll()
			)

			.formLogin(login -> login
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("loginId")
				.passwordParameter("password")
				.failureHandler(authenticationFailureHandler)

				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/")
			);
		//.formLogin(withDefaults())

		return http.build();
	}
}
