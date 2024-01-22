package org.example.admin_demo_spring.domain.member.repository;

import java.util.Optional;

import org.example.admin_demo_spring.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByMemberName(String memberName);
}