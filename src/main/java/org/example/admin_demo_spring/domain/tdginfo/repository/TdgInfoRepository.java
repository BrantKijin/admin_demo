package org.example.admin_demo_spring.domain.tdginfo.repository;

import org.example.admin_demo_spring.domain.tdginfo.entity.TdgInfo;
import org.example.admin_demo_spring.domain.tdginfo.repository.querydsl.TdgInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TdgInfoRepository extends JpaRepository<TdgInfo, Long>, TdgInfoRepositoryCustom {

	TdgInfo findByAptCd(String aptCd);
}