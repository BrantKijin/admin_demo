package org.example.admin_demo_spring.domain.tdginfo.service;

import org.example.admin_demo_spring.domain.tdginfo.dto.response.TdgInfoResponse;

public interface TdgInfoService {
	TdgInfoResponse findAptCd(String aptCd);
	TdgInfoResponse findQueryDslAptCd(String aptCd);
}
