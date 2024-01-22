package org.example.admin_demo_spring.domain.tdginfo.repository.querydsl;

import org.example.admin_demo_spring.domain.tdginfo.entity.TdgInfo;

public interface TdgInfoRepositoryCustom {
	TdgInfo findByQueryDslAptCd(String aptCd);
}
