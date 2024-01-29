package org.example.admin_demo_spring.domain.members.dto.response;

import java.time.LocalDateTime;

import org.example.admin_demo_spring.domain.members.entity.Members;



import io.swagger.v3.oas.annotations.media.Schema;

public record MembersResponse(
	@Schema(description = "id", example = "id")
	Long id,
	@Schema(description = "로그인아이디", example = "로그인아이디")

	String loginId,
	@Schema(description = "권한", example = "권한")

	String role,

	@Schema(description = "이름", example = "김기술")

	String memberName,
	@Schema(description = "비번수정일", example = "비번수정일")
	LocalDateTime changePasswordAt



) {
	public static MembersResponse from(Members members) {
		return new MembersResponse(
			members.getId(),
			members.getLoginId(),
			members.getRole().getTitle(),
			members.getMemberName(),
			members.getChangePasswordAt()

		);
	}
}
