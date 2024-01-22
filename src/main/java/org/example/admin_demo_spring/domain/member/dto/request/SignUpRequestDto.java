package org.example.admin_demo_spring.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
	private String memberName;
	private String password;
	private String role;
}