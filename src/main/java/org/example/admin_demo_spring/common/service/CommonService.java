package org.example.admin_demo_spring.common.service;

import java.util.Collection;
import java.util.List;

import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;



@Service
public class CommonService {

	public String getRole(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.toList();
		return roles.isEmpty() ? null : roles.get(0);
	}

	public String getRoleAdminElseNull(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.toList();

		if (roles.contains(RoleStatus.ADMIN.name())) {
			return RoleStatus.ADMIN.name();
		} else {
			return null;
		}
	}

	/**
	 * 관리자만 통과 시킨다
	 */
	public void checkAdminPermission(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.toList();
		String role = roles.isEmpty() ? null : roles.get(0);

		if (!(RoleStatus.ADMIN.name().equals(role))) {
			throw new RuntimeException("권한이 없습니다");
		}
	}

}
