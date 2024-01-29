package org.example.admin_demo_spring.domain.members.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record VoiceUserUpdatePasswordRequest(

	@Schema(description = "비번", example = "123123@#$!2")
	String password,
	@Schema(description = "변경할비번", example = "123123@#$!2")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{6,12}$", message = "변경할 비밀번호 형식이 올바르지 않습니다")
	String changePassword,

	boolean threeMonthsUsing
) {
}
