package org.example.admin_demo_spring.domain.members.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberInitPasswordRequest(

	@Schema(description = "비번", example = "123123@#$!2")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{6,12}$", message = "변경할 비밀번호 형식이 올바르지 않습니다")
	String password,

	@NotBlank
	@Schema(description = "로그인아이디", example = "xpvoice1")
	String loginId,

	@Schema(description = "3개월유지", example = "true")
	Boolean isContinueUse

) {
}