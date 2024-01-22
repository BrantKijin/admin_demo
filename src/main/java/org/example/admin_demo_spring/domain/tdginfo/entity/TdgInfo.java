package org.example.admin_demo_spring.domain.tdginfo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TDG_INFO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TdgInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APT_CD")
	private String aptCd;
	@Column(name = "APT_NM")
	private String aptNm;

	@Column(name = "APT_OUTPUT_NM")
	private String aptOutputNm;

	@Column(name = "APT_FORM")
	private String aptForm;

	@Column(name = "APT_NO_NUM")
	private Integer aptNoNum;
	@Column(name = "HOUSEHOLD_NUM")
	private Integer householdNum;

	@Column(name = "LEASE_CLASS")
	private String leaseClass;
	@Column(name = "APT_STATE")
	private String aptState;
	@Column(name = "FIRST_CONT_DATE")
	private Date firstContDate;
	@Column(name = "HAPSAN_YN")
	private String hapsanYn;
	@Column(name = "MEMO")
	private String memo;
	@Column(name = "REG_DATE")
	private Date regDate;
	@Column(name = "REG_USER_ID")
	private String regUserId;
	@Column(name = "UPD_DATE")
	private Date updDate;
	@Column(name = "UPD_USER_ID")
	private String updUserId;
	@Column(name = "COSM_CD")
	private Integer cosmCd;
	@Column(name = "NOTI_KIND")
	private String notiKind;
	@Column(name = "ADJU_KIND")
	private String adjuKind;
	@Column(name = "ETRT_COM")
	private String etrtCom;
	@Column(name = "IT_PROC_COM")
	private String itProcCom;
	@Column(name = "CONT_START_DT")
	private Date contStartDt;
	@Column(name = "CONT_END_DT")
	private Date contEndDt;
	@Column(name = "REQ_DT")
	private Date reqDt;
	@Column(name = "APT_NO_FR")
	private String aptNoFr;
	@Column(name = "APT_NO_TO")
	private String aptNoTo;
	@Column(name = "APT_ROOM_FR")
	private String aptRoomFr;
	@Column(name = "APT_ROOM_TO")
	private String aptRoomTo;
	@Column(name = "OFFICE_MNG_NO")
	private Integer officeMngNo;


	@Column(name = "ETC_NOTI_KIND")
	private String etcNotiKind;
	@Column(name = "DBID")
	private String dbId;
	@Column(name = "APT_TYPE")
	private String aptType;
	@Column(name = "CONV_YYMM")
	private String convYymm;
	@Column(name = "CONV_ETC1_YYMM")
	private String convEtc1Yymm;
	@Column(name = "OUTPUT_REQ")
	private String outputReq;
	@Column(name = "TEAM_CODE")
	private String teamCode;
	@Column(name = "PA_TA_CLOSE")
	private String paTaClose;

	@Column(name = "SDIV")
	private String sdiv;
	@Column(name = "SMILE_EDI")
	private String smileEdi;
	@Column(name = "RCPT_CREAT")
	private String rcptCreat;
	@Column(name = "RCPT_DIV")
	private String rcptDiv;
	@Column(name = "EXPORT_CLASS")
	private String exportClass;
	@Column(name = "REPORT_TYPE")
	private String reportType;
	@Column(name = "REPORT_PAGE")
	private Integer reportPage;
	@Column(name = "GRP_APT_CD")
	private String grpAptCd;
	@Column(name = "HANGEUL_YN")
	private String hangeulYn;
	@Column(name = "ACCT_STD_CD")
	private String acctStdCd;
	@Column(name = "APTI_USR_ID")
	private String aptiUsrId;
	@Column(name = "APTI_PW")
	private String aptiPw;
	@Column(name = "ACCT_DAY_CLOSE")
	private String acctDayClose;
	@Column(name = "PRT_APT_YN")
	private String prtAptYn;

}