package org.example.admin_demo_spring.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.example.admin_demo_spring.security.jwt.JwtUtil;
import org.example.admin_demo_spring.security.jwt.filter.JwtAuthFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
	private final JwtUtil jwtUtil;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// resources 자원 접근 허용
		return (web) -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(
					Arrays.asList(
						// "https://devxpvoice.xperp.co.kr" CORS 에러 방지 필요하면 추가 필요
						)
				);
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(List.of("Authorization"));
				return config;
			}))
			/* csrf 설정 해제. */
			.csrf(AbstractHttpConfigurer::disable)
			/*JWT 사용 위해 기존의 세션 방식 인증 해제*/
			.sessionManagement(
				session ->
					//스프링 시큐리티가 필요 시 생성(default)
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// .invalidSessionUrl("/session-check")
			)
			/*JwtAuthFilter에 jwtUtil을 전달하여 UsernamePasswordAuthenticationFilter전에 필터로 등록한다.*/
			.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

			.authorizeHttpRequests((request) -> request


				.requestMatchers("/admin/**")
				.hasAuthority(UserRoleEnum.ADMIN.name())


				.requestMatchers("/static/**").permitAll() //정적 리소스 명시적으로 허용

				.requestMatchers("/signup").permitAll()
				/*회원가입 요청은 무조건 허용*/
				.requestMatchers("/api/signup").permitAll()
				/*로그인 요청 허용*/
				.requestMatchers("/api/login").permitAll()

				/*나머지 요청은 전부 인증되어야 함*/
				.anyRequest().authenticated()
			)

			.formLogin(login -> login
				.loginPage("/login")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/")
			);

		return http.build();
	}

	

}
