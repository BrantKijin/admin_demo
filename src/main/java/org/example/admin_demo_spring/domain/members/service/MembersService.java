package org.example.admin_demo_spring.domain.members.service;

import java.util.List;
import java.util.Optional;

import org.example.admin_demo_spring.common.dto.page.PageRequestDTO;
import org.example.admin_demo_spring.common.dto.page.PageResultDTO;
import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserAddRequest;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserInitPasswordRequest;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserUpdatePasswordRequest;
import org.example.admin_demo_spring.domain.members.dto.request.MemberUpdateRequest;
import org.example.admin_demo_spring.domain.members.dto.response.SignInResponse;
import org.example.admin_demo_spring.domain.members.dto.response.MembersDetailResponse;
import org.example.admin_demo_spring.domain.members.dto.response.MembersResponse;
import org.example.admin_demo_spring.domain.members.entity.Members;


public interface MembersService {

	Optional<Members> getByLoginId(String loginId);

	Long join(String loginId, String password, RoleStatus role,String memberName);

	SignInResponse login(String loginId, String password);

	PageResultDTO<MembersResponse, Members> getUserInfoList(PageRequestDTO requestDTO);


	void passwordReset(Long id);

	void passwordInitChange(Long id, VoiceUserInitPasswordRequest request);

	void changePassword(Long id, VoiceUserUpdatePasswordRequest request);

	void changeRoleAndName(Long id, MemberUpdateRequest request);


	MembersDetailResponse getUserDetailInfo(Long id);


	Long getVoiceUserId(String userName);
	void passwordAllReset(Long id);
}
