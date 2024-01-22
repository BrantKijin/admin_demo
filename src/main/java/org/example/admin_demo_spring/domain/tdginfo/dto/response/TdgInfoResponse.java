package org.example.admin_demo_spring.domain.tdginfo.dto.response;

import org.example.admin_demo_spring.domain.tdginfo.entity.TdgInfo;

public record TdgInfoResponse(
	String aptCd,
	String aptNm,
	String aptOutputNm,
	String aptForm,
	Integer aptNoNum,
	Integer householdNum

) {
	public static TdgInfoResponse from(TdgInfo tdgInfo){
		return new TdgInfoResponse(
			tdgInfo.getAptCd(),
			tdgInfo.getAptNm(),
			tdgInfo.getAptOutputNm(),
			tdgInfo.getAptForm(),
			tdgInfo.getAptNoNum(),
			tdgInfo.getHouseholdNum()
		);
	}
}
