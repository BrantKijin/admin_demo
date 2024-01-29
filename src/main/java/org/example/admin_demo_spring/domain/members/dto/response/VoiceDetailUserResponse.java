package org.example.admin_demo_spring.domain.members.dto.response;

import java.time.LocalDateTime;

import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.example.admin_demo_spring.domain.members.entity.Members;

import io.swagger.v3.oas.annotations.media.Schema;

public record VoiceDetailUserResponse(
	@Schema(description = "id", example = "id")
	Long id,
	@Schema(description = "로그인아이디", example = "로그인아이디")

	String loginId,
	@Schema(description = "권한", example = "권한")

	RoleStatus role,
	@Schema(description = "비번수정일", example = "비번수정일")
	LocalDateTime changePasswordAt


) {
	public static VoiceDetailUserResponse from(Members members) {
		return new VoiceDetailUserResponse(
			members.getId(),
			members.getLoginId(),
			members.getRole(),
			members.getChangePasswordAt()

		);
	}
}
