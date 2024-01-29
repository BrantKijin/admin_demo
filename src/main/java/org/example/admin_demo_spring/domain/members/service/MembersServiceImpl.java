package org.example.admin_demo_spring.domain.members.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
import org.example.admin_demo_spring.domain.members.repository.MembersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MembersServiceImpl implements MembersService {
	private final MembersRepository membersRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<Members> getByLoginId(String loginId) {
		Optional<Members> byLoginId = membersRepository.findByLoginId(loginId);

		if(byLoginId.isPresent()){
			return byLoginId;
		}
		System.out.println(byLoginId);
		return null;
	}

	@Override
	@Transactional
	public Long join(String loginId, String password, RoleStatus role, String memberName) {

		String encodePw = passwordEncoder.encode(password);
		Members member = Members.builder()
			.loginId(loginId)
			.password(encodePw)
			.role(RoleStatus.EMPTY)
			.memberName(memberName)
			.build();
		validateDuplicateMember(member);
		membersRepository.save(member);
		return member.getId();
	}

	@Override
	public SignInResponse login(String loginId, String password) {
		return null;
	}

	@Override
	public PageResultDTO<MembersResponse, Members> getUserInfoList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable();
		Page<Members> result = membersRepository.getUserPageList(pageable);
		Function<Members, MembersResponse> fn = (MembersResponse::from);
		return new PageResultDTO<>(result, fn);
	}


	@Override
	@Transactional
	public void passwordReset(Long id) {
		Optional<Members> byId = membersRepository.findById(id);
		if (byId.isPresent()) {
			Members voiceUsers = byId.get();
			rolePasswordInit(voiceUsers);
		}
	}

	private void rolePasswordInit(Members members) {
		if (members.getRole().equals(RoleStatus.ADMIN)) {
			String loginId = members.getLoginId();
			String capitalizedLoginId = Character.toUpperCase(loginId.charAt(0)) + loginId.substring(1);
			members.resetPassword(passwordEncoder.encode(capitalizedLoginId + "!@"));
		} else {
			members.resetPassword(passwordEncoder.encode("a123456789"));
		}
	}

	@Override
	@Transactional
	public void passwordAllReset(Long id) {
		Optional<Members> byId = membersRepository.findById(id);
		if (byId.isPresent()) {
			Members members = byId.get();
			if (members.getRole().equals(RoleStatus.ADMIN)) {
				String loginId = members.getLoginId();
				String capitalizedLoginId = Character.toUpperCase(loginId.charAt(0)) + loginId.substring(1);
				members.resetPassword(passwordEncoder.encode(capitalizedLoginId + "a123456789"));
			}
			members.changeDateReset();
		}
	}

	@Override
	@Transactional
	public void passwordInitChange(Long id, VoiceUserInitPasswordRequest request) {
		membersRepository.findByIdAndLoginId(id,
				request.loginId())
			.map(voiceUsers -> {
				if (request.isContinueUse()) {
					voiceUsers.changeDatePasswordAt();
				} else {
					voiceUsers.changePassword(passwordEncoder.encode(request.password()));
				}
				return voiceUsers;
			})
			.orElseThrow(() -> new RuntimeException("패스워드 초기화 가능 적용대상이 아닙니다"));
	}

	@Override
	@Transactional
	public void changePassword(Long id, VoiceUserUpdatePasswordRequest request) {
		membersRepository.findById(id).ifPresent(voiceUsers -> {
			if (request.threeMonthsUsing()) {
				// 3개월 유지 라면 날짜만 갱신
				voiceUsers.changeDatePasswordAt();
			} else {
				boolean matches = passwordEncoder.matches(request.password(), voiceUsers.getPassword());
				if (matches) {
					voiceUsers.changePassword(passwordEncoder.encode(request.changePassword()));
				} else {
					throw new RuntimeException("비밀번호를 확인해주세요");
				}
			}

		});
	}

	@Override
	@Transactional
	public void changeRoleAndName(Long id, MemberUpdateRequest request) {
		membersRepository.findById(id).ifPresent(members -> {
			if (members.getChangePasswordAt() == null) {
				this.rolePasswordInit(members);
			}
			members.changeRole(request.roleStatus());
			members.changeName(request.memberName());
		});
	}



	@Override
	public MembersDetailResponse getUserDetailInfo(Long id) {
		return membersRepository.findById(id)
			.map(MembersDetailResponse::from)
			.orElse(null);

	}


	@Override
	public Long getVoiceUserId(String loginId) {
		Members voiceUsers = membersRepository.findByLoginId(loginId)
			.orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
		return voiceUsers.getId();
	}

	private void validateDuplicateMember(Members members) {
		membersRepository.findByLoginId(members.getLoginId())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
}
