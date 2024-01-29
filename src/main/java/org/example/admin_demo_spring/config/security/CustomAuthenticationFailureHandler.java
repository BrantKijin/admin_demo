package org.example.admin_demo_spring.config.security;

import java.io.IOException;

import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
		if (exception instanceof CredentialsExpiredException) {
			String username = request.getParameter("loginId");
			request.getSession().setAttribute("username", username);
			// 비밀번호 만료 시 비밀번호 변경 페이지로 리다이렉트
			redirectStrategy.sendRedirect(request, response, "/password-expired");
		} else {
			// 다른 인증 실패 시 로그인 페이지로 리다이렉트 또는 에러 메시지 표시 등을 처리할 수 있습니다.
			redirectStrategy.sendRedirect(request, response, "/login?error");
		}
	}
}