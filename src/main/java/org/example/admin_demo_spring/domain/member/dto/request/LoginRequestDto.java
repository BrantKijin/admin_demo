package org.example.admin_demo_spring.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
	private String memberName;
	private String password;
}