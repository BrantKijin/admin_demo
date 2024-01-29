package org.example.admin_demo_spring.domain.members.service;

import java.util.List;
import java.util.Optional;

import org.example.admin_demo_spring.common.dto.page.PageRequestDTO;
import org.example.admin_demo_spring.common.dto.page.PageResultDTO;
import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserAddRequest;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserInitPasswordRequest;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserUpdatePasswordRequest;
import org.example.admin_demo_spring.domain.members.dto.request.VoiceUserUpdateRequest;
import org.example.admin_demo_spring.domain.members.dto.response.SignInResponse;
import org.example.admin_demo_spring.domain.members.dto.response.VoiceDetailUserResponse;
import org.example.admin_demo_spring.domain.members.dto.response.VoiceUserResponse;
import org.example.admin_demo_spring.domain.members.entity.Members;


public interface MembersService {

	Optional<Members> getByLoginId(String loginId);

	Long join(String loginId, String password, RoleStatus role);

	SignInResponse login(String loginId, String password);

	PageResultDTO<VoiceUserResponse, Members> getUserInfoList(PageRequestDTO requestDTO);

	void addUser(VoiceUserAddRequest request);

	void passwordReset(Long id);

	void passwordAllReset(Long id);

	void passwordInitChange(Long id, VoiceUserInitPasswordRequest request);

	void changePassword(Long id, VoiceUserUpdatePasswordRequest request);

	void changeRoleInstallCompany(Long id, VoiceUserUpdateRequest request);

	String getInstallCompanyName(String loginID);

	String getLastUserId();

	VoiceDetailUserResponse getUserDetailInfo(Long id);

	List<String> getInstallCompanyNameList();

	Long getVoiceUserId(String userName);

}
