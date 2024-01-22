package org.example.admin_demo_spring.domain.member.service;

import java.util.Optional;

import org.example.admin_demo_spring.domain.member.dto.request.LoginRequestDto;
import org.example.admin_demo_spring.domain.member.dto.request.SignUpRequestDto;
import org.example.admin_demo_spring.domain.member.entity.Member;
import org.example.admin_demo_spring.domain.member.repository.MemberRepository;
import org.example.admin_demo_spring.security.UserRoleEnum;
import org.example.admin_demo_spring.security.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	/*회원 가입*/
	@Transactional
	public void signUp(SignUpRequestDto requestDto) {

		/*아이디*/
		String memberName = requestDto.getMemberName();
		/*패스워드*/
		String password = passwordEncoder.encode(requestDto.getPassword());
		/*유저 권한*/
		UserRoleEnum role = UserRoleEnum.valueOf(requestDto.getRole());

		Member member = new Member(memberName, password, role);
		memberRepository.save(member);

	}

	/*로그인*/
	@Transactional
	public void login(LoginRequestDto requestDto, HttpServletResponse response) {

		Optional<Member> optionalMember = memberRepository.findByMemberName(requestDto.getMemberName());

		if (optionalMember.isEmpty()) {
			log.warn("회원이 존재하지 않음");
			throw new IllegalArgumentException("회원이 존재하지 않음");
		}

		Member member = optionalMember.get();

		/*비밀번호 다름.*/
		if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
			log.warn("비밀번호가 일치하지 않습니다.");
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}


		/*토큰을 쿠키로 발급 및 응답에 추가*/
		Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER,
			jwtUtil.createToken(member.getMemberName(), member.getRole()));
		cookie.setMaxAge(60 * 60); // 1시간 동안 유효
		cookie.setPath("/");
		cookie.setDomain("localhost");
		cookie.setSecure(false);
		response.addCookie(cookie);

	}

}
