package org.example.admin_demo_spring.domain.members.dto.response;



import org.example.admin_demo_spring.common.enums.RoleStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
	@Schema(description = "회원 이름", example = "콜라곰")
	String name,
	@Schema(description = "회원 유형", example = "USER")
	RoleStatus type,
	String token
) {
}
