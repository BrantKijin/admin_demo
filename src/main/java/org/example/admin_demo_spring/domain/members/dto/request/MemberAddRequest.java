package org.example.admin_demo_spring.domain.members.dto.request;



import org.example.admin_demo_spring.common.enums.RoleStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberAddRequest(
	@Schema(description = "권한", example = "권한")
	RoleStatus roleStatus


) {
}
