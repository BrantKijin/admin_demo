package org.example.admin_demo_spring.config.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionExpirationTimeInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			long lastAccessedTime = session.getLastAccessedTime();
			int maxInactiveInterval = session.getMaxInactiveInterval();
			long expirationTimeMillis = lastAccessedTime + (maxInactiveInterval * 1000);
			request.setAttribute("sessionExpirationTime", expirationTimeMillis);
		}
		return true;
	}
}