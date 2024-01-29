package org.example.admin_demo_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "메인화면 API(Home)")
@Controller
@RequestMapping("/")
public class HomeController {
	/**
	 * 배치관리자 메인화면
	 */

	@Operation(summary = " ")
	@GetMapping()
	public String mainView() {
		return "home/main";
	}

	@Operation(summary = " ")
	@GetMapping("home/function")
	public String minaView() {

		return "home/main-function";
	}

}
