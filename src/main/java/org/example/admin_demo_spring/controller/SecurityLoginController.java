package org.example.admin_demo_spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.example.admin_demo_spring.common.dto.response.ApiResponse;
import org.example.admin_demo_spring.domain.members.dto.request.MemberInitPasswordRequest;
import org.example.admin_demo_spring.domain.members.service.MembersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Tag(name = "스프링 시큐리티 관련 API(Security) ")
@Controller
@RequiredArgsConstructor
public class SecurityLoginController {
	private final MembersService membersService;

	@GetMapping("/login")
	public String loginPage(Model model) {

		return "login/login";
	}

	@Operation(summary = "비밀번호 만료시 페이지")
	@GetMapping("/password-expired")
	public String passwordExpired(HttpServletRequest request, Model model) {
		String username = (String)request.getSession().getAttribute("username");

		Long voiceUserId = membersService.getVoiceUserId(username);
		model.addAttribute("userIdx", voiceUserId); //유저 고유 인덱스
		model.addAttribute("loginId", username); // 유저아이디
		// 지금은 username 인데 조회해서 아이디를 내려주도록 변경하자
		//TODO 비밀번호 변경페이지로 아이디와 내려가야한다 
		return "login/pw_change";
	}

	@Operation(summary = "비밀번호 만료시 변경 로직")
	@ResponseBody
	@PostMapping("/login-info/password-init/{id}")
	public ApiResponse userPasswordInitChange(@PathVariable("id") Long id
		, @Validated @RequestBody MemberInitPasswordRequest request
	) {

		membersService.passwordInitChange(id, request);
		return ApiResponse.success(true);
	}

	// 세션 만료 페이지
	@GetMapping("/session-check")
	public String sessionDate() {
		return "login/session-error";
	}

	@GetMapping("/redirect")
	@ResponseBody
	public String redirectToCurrentPage(HttpServletRequest request, HttpSession session) {


		long lastAccessedTime = session.getLastAccessedTime();
		int maxInactiveIntervalSeconds = session.getMaxInactiveInterval();

		long expirationTimeMillis = lastAccessedTime + (maxInactiveIntervalSeconds * 1000L);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul")); // 한국 시간대로 설정
		Date expirationDate = new Date(expirationTimeMillis);
		String formattedExpirationTime = sdf.format(expirationDate);

		return formattedExpirationTime;
	}

}
