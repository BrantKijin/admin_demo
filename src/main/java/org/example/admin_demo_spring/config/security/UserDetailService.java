package org.example.admin_demo_spring.config.security;

import java.util.Optional;

import org.example.admin_demo_spring.domain.members.entity.Members;
import org.example.admin_demo_spring.domain.members.service.MembersService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
	private final MembersService membersService;

	@Override
	public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
		Optional<Members> findOne = membersService.getByLoginId(insertedUserId);
		Members members = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));

		return User.builder()
			.credentialsExpired(members.isPasswordExpired())
			.username(members.getLoginId())
			.password(members.getPassword())
			.roles(String.valueOf(members.getRole()))
			.authorities(String.valueOf(members.getRole()))
			.build();
	}
}
