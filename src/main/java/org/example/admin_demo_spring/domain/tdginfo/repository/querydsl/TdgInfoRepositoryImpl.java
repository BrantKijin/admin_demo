package org.example.admin_demo_spring.domain.tdginfo.repository.querydsl;

import org.example.admin_demo_spring.domain.tdginfo.entity.TdgInfo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TdgInfoRepositoryImpl implements TdgInfoRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	QTdgInfo tdgInfo = QTdgInfo.tdgInfo;

	@Override
	public TdgInfo findByQueryDslAptCd(String aptCd) {
		return queryFactory.select(tdgInfo)
			.from(tdgInfo)
			.where(eqAptCd(aptCd))
			.fetchOne();
	}

	private BooleanExpression eqAptCd(String aptCd) {
		if (StringUtils.isNullOrEmpty(aptCd)) {
			return null;
		}
		return tdgInfo.aptCd.eq(aptCd.toUpperCase());
	}
}
