package org.example.admin_demo_spring.controller;

import org.example.admin_demo_spring.common.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/v1/tdginfo")
	public ApiResponse getInstallStatus(){
		return ApiResponse.success("22");
	}
}
