package org.example.admin_demo_spring.common.enums;

import lombok.Getter;

@Getter
public enum RoleStatus {
	MEMBER("맴버"),
	ADMIN("모든권한"),
	EMPTY("미선택");
	private String title;

	RoleStatus(String title) {
		this.title = title;
	}

}
