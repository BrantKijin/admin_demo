package org.example.admin_demo_spring.security;

import org.example.admin_demo_spring.domain.member.entity.Member;
import org.example.admin_demo_spring.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberName(memberName)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

		return new UserDetailsImpl(member, member.getPassword(), member.getMemberName());
	}

}