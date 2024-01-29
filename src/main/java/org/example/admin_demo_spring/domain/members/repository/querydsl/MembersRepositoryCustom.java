package org.example.admin_demo_spring.domain.members.repository.querydsl;

import java.util.Optional;

import org.example.admin_demo_spring.domain.members.entity.Members;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembersRepositoryCustom {

	Page<Members> getUserPageList(Pageable pageable);

	Long getCountAndExceptRoleUploadVoiceId();

}
