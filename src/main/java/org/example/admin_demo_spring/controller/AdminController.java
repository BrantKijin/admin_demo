package org.example.admin_demo_spring.controller;

import org.example.admin_demo_spring.common.dto.page.PageRequestDTO;
import org.example.admin_demo_spring.common.dto.page.PageResultDTO;
import org.example.admin_demo_spring.common.dto.response.ApiResponse;
import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.example.admin_demo_spring.common.service.CommonService;
import org.example.admin_demo_spring.domain.members.dto.request.MemberUpdatePasswordRequest;
import org.example.admin_demo_spring.domain.members.dto.request.MemberUpdateRequest;
import org.example.admin_demo_spring.domain.members.dto.response.MembersResponse;
import org.example.admin_demo_spring.domain.members.entity.Members;
import org.example.admin_demo_spring.domain.members.service.MembersService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "관리자용 API(Admin)")
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
	private final MembersService membersService;
	private final CommonService commonService;
	@Operation(summary = "로그인 정보 조회 페이징")
	@GetMapping("/login-info")
	public void getUserInfoList(PageRequestDTO pageRequestDTO, Model model
		, Authentication authentication
	) {
		commonService.checkAdminPermission(authentication);
		PageResultDTO<MembersResponse, Members> userInfoList = membersService.getUserInfoList(
			pageRequestDTO);
		model.addAttribute("result", userInfoList);
	}

	@Operation(summary = "회원가입")
	@PostMapping("join")
	public void join(
		@RequestParam(value = "loginId", required = false) String loginId,
		@RequestParam(value = "password", required = false) String password,
		@RequestParam(value = "memberName", required = false) String memberName,
		@RequestParam(value = "role", required = false) RoleStatus role
	) {
		membersService.join(loginId, password, role,memberName);
	}

	@Operation(summary = "로그인 정보 상세 조회")
	@GetMapping("/login-info/detail/{id}")
	@ResponseBody
	public ApiResponse getUserInfoDetail(
		@PathVariable("id") Long id,
		Authentication authentication
	) {
		commonService.checkAdminPermission(authentication);
		return ApiResponse.success(membersService.getUserDetailInfo(
			id));
	}

	@Operation(summary = "로그인 정보 수정 (권한,이름,비번)")
	@ResponseBody
	@PostMapping("/login-info/role/{id}")
	public ApiResponse userRoleChange(@PathVariable("id") Long id, @RequestBody MemberUpdateRequest request,
		Authentication authentication) {
		commonService.checkAdminPermission(authentication);
		membersService.changeRoleAndName(id, request);
		return ApiResponse.success(true);
	}
	@Operation(summary = "로그인 비밀번호 변경 패스워드 비교 변경")
	@ResponseBody
	@PostMapping("/login-info/password/{id}")
	public ApiResponse userPasswordChange(@PathVariable("id") Long id,
		@Validated @RequestBody MemberUpdatePasswordRequest request
		, Authentication authentication
	) {
		commonService.checkAdminPermission(authentication);
		membersService.changePassword(id, request);
		return ApiResponse.success(true);
	}
	@Operation(summary = "로그인 비밀번호 초기화 아이디별로 초기화 하드 코딩")
	@ResponseBody
	@PostMapping("/login-info/password-reset/{id}")
	public ApiResponse userPasswordReset(@PathVariable("id") Long id
		, Authentication authentication
	) {
		commonService.checkAdminPermission(authentication);
		membersService.passwordReset(id);
		return ApiResponse.success(true);
	}




}
