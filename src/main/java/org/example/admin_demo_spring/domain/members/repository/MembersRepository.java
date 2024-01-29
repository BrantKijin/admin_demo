package org.example.admin_demo_spring.domain.members.repository;


import java.util.Optional;

import org.example.admin_demo_spring.common.enums.RoleStatus;
import org.example.admin_demo_spring.domain.members.entity.Members;
import org.example.admin_demo_spring.domain.members.repository.querydsl.MembersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;




public interface MembersRepository extends JpaRepository<Members, Long>, MembersRepositoryCustom {


	Optional<Members> findByIdAndChangePasswordAtIsNullAndLoginId(Long id,
		String loginId);

	Optional<Members> findByIdAndLoginId(Long id, String loginId);

	Optional<Members> findByLoginIdAndRole(String loginId, RoleStatus roleStatus);

	Optional<Members> findByLoginId(String loginId);

}
