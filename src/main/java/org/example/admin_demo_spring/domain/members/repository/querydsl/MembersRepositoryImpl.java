package org.example.admin_demo_spring.domain.members.repository.querydsl;

import java.util.Optional;

import org.example.admin_demo_spring.domain.members.entity.Members;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MembersRepositoryImpl implements MembersRepositoryCustom {
	private final JPAQueryFactory queryFactory;



	@Override
	public Page<Members> getUserPageList(Pageable pageable) {
		return null;
	}

	@Override
	public Long getCountAndExceptRoleUploadVoiceId() {
		return null;
	}

	//
	// QVoiceUsers voiceUsers = QVoiceUsers.voiceUsers;
	//
	// @Override
	// public Optional<Members> findByLoginId(String loginId) {
	//
	// 	return queryFactory.select(voiceUsers)
	// 		.from(voiceUsers)
	// 		.where(voiceUsers.loginId.eq(loginId))
	// 		.stream().findFirst();
	// }
	//
	// @Override
	// public Page<VoiceUsers> getUserPageList(Pageable pageable) {
	// 	List<VoiceUsers> contents = queryFactory.select(voiceUsers)
	// 		.from(voiceUsers)
	// 		.where(voiceUsers.categoryStatus.eq(CategoryStatus.HOME_VOICE))
	// 		.orderBy(voiceUsers.id.asc())
	// 		.offset(pageable.getOffset())
	// 		.limit(pageable.getPageSize())
	// 		.fetch();
	//
	// 	JPAQuery<VoiceUsers> countQuery = queryFactory.select(voiceUsers)
	// 		.where(voiceUsers.categoryStatus.eq(CategoryStatus.HOME_VOICE))
	// 		.from(voiceUsers);
	//
	// 	return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.stream().count());
	// }
	//
	// @Override
	// public Long getCountAndExceptRoleUploadVoiceId() {
	// 	return queryFactory.select(voiceUsers)
	// 		.from(voiceUsers)
	// 		.where(voiceUsers.categoryStatus.eq(CategoryStatus.HOME_VOICE))
	// 		.stream().count();
	// }
}
