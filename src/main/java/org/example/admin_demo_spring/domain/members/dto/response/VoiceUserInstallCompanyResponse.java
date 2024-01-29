package org.example.admin_demo_spring.domain.members.dto.response;



import org.example.admin_demo_spring.domain.members.entity.Members;

import io.swagger.v3.oas.annotations.media.Schema;

public record VoiceUserInstallCompanyResponse(
	@Schema(description = "로그인아이디", example = "로그인아이디")
	String loginId


) {
	public static VoiceUserInstallCompanyResponse from(Members members) {
		return new VoiceUserInstallCompanyResponse(
			members.getLoginId()

		);
	}
}
