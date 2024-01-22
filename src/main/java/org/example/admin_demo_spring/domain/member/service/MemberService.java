package org.example.admin_demo_spring.domain.member.service;

import org.example.admin_demo_spring.domain.member.dto.request.LoginRequestDto;
import org.example.admin_demo_spring.domain.member.dto.request.SignUpRequestDto;

import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

	/*회원 가입*/
	public void signUp(SignUpRequestDto requestDto);

	/*로그인*/
	public void login(LoginRequestDto requestDto, HttpServletResponse response);

}
