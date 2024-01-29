package org.example.admin_demo_spring.domain.members.entity;

import java.time.LocalDateTime;

import org.example.admin_demo_spring.common.entity.BaseEntity;
import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.hibernate.annotations.Comment;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Comment("관리자 회원테이블")
@Table(name = "members")
public class Members extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Comment("로그인아이디")
	@Column(name = "login_id", length = 30, unique = true)
	private String loginId;

	@Comment("사용자")
	@Column(name = "member_name", length = 30, unique = true)
	private String memberName;

	@Comment("패스워드")
	@Column(name = "password", length = 100)
	private String password;

	@Comment("권한")
	@Column(name = "role", length = 30)
	@Enumerated(EnumType.STRING)
	private RoleStatus role;

	@Comment("비번수정일")
	@Column(name = "change_password_at")
	private LocalDateTime changePasswordAt;

	@Builder
	public Members( String loginId, String memberName, String password, RoleStatus role,
		LocalDateTime changePasswordAt) {

		this.loginId = loginId;
		this.memberName = memberName;
		this.password = password;
		this.role = role;
		this.changePasswordAt = changePasswordAt;
	}

	public void createVoiceUser(String loginId,String memberName, String password, RoleStatus role) {
		Members.builder()
			.loginId(loginId)
			.memberName(memberName)
			.password(password)
			.role(role)
			.build();
	}

	public void resetPassword(String password) {
		this.password = password;
		this.changePasswordAt = null;
	}

	public void changePassword(String password) {
		this.password = password;
		this.changePasswordAt = LocalDateTime.now();
	}

	public void changeDatePasswordAt() {
		this.changePasswordAt = LocalDateTime.now();
	}

	public void changeDateReset() {
		this.changePasswordAt = null;
	}

	public void changeRole(RoleStatus role) {
		this.role = role;
	}


	public boolean isPasswordExpired() {
		if (changePasswordAt == null) {
			return true; // 비밀번호 변경 일자가 없으면 만료되지 않음으로 처리
		}

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime passwordExpirationDate = changePasswordAt.plusMonths(3);

		return today.isAfter(passwordExpirationDate);
	}
}
