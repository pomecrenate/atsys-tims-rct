package com.atsys.model;

import java.util.ArrayList;
import java.util.List;

public class TbUsrVo extends TbUsrBo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9130362562048488452L;
	
	private String authGrpCd;
	private String authGrpNm;
	private String gndrNm;

	private String sctrCdNm;	//조직명
	
	private String sctrNm;		//조직명
	private String cmndNm;		//부문명
	private String drctrNm;		//총국명
	private String breuNm;		//지국명
	private String dpartNm;		//부서명
	private String workDivNm;	//재직구분명
	
	private String jobLvlTypeCd;
	private String jobLvlTypeNm;
	
	private String ttrTypeCd;
	private String ttrTypeNm;
	private String ttrDgrCd;
	private String ttrDgrNm;
	private String ttrMajorCd;
	private String ttrMajorNm;
	
	private String crsCd;				//과정코드
	private String crsNm;
	private String crsDivCd;
	private String crsDivNm;
	
	private String gisuCd;				//차수코드
	private String gisuNm;
	private String gisuNo;
	private String gisuYear;
	
	private String gisuAtndCd;			//수강코드
	private String atndStatsCd;			//숭강상태코드
	private String appltUsrCd;			//수강신청자
	private String facDivCd;			//시설물분류코드
	private String facDivNm;			//시설물분류명
	private String totScore;
	
	private String failRsn;				//수강생 배정 실패 사유
	
	private String stats;
	private String offlineStartDt;
	private String offlineEndDt;
	private String stdyCode;
	private String gisuAtndApplnCd;
	private String fullStartDt;
	private String fullEndDt;
	private String fullStartEndDt;        //형식 :: 2018-01-01 ~ 2018-12-31
	
	private String totEduTime;
	private String appvlTime;
	private String cmptNo;
	private String totStdyRate;
	private String loginPwdChk;
	private String loginLimitChkYn;
	private String rvl;
	private String newLoginPwd;
	private String prsnInfoAgrYn;
	private String slpYn;
	private String sleWthdDiv;
	private String wthdCd;
	private String loginPwdCnfm;
	private String secessionRsn;
	private String secessionYn;
	private String connInfo;
	private String adminAcsYn;
	private String acsType;
	private String slpWthdCd;
	private String authExprtDt;
	private String eduGrpCd;
	private String loginIdOx;
	private String reveal; //개인정보조회 버튼 오픈/클로즈용
	private String tempLoginPwd; //임시비밀번호
	private String secessionExeDtm; //탈퇴처리일시
	private String atndHstCnt;//수강이력 count
	
	private String asgnYn; // 혼합과정배정여부
	private String emngDtm; // SAP의 배정정보 생성일자
//	private KakaotalkMessage kakaotalkMessage;
//	private TextMessage textMessage;
	
	private String prvsGisuNo;
	private String prvsGisuCd;
	private String prvsGisuAtndCd;
	private String frrdGisuCd;
	private String ntfDivCd;
	private String msgTypeCd;
	private String nteTitle;
	private String nteCont;
	private String ntfLinkUrl;
	private String appPushUseYn;
	private String imgPath;
	private String bldDivCd;
	private String tmptCd;
	private String title;
	private String cnts;
	private String srvPaperCd;	// 알림발송에 일반설문 코드
	private String usrCds;
	
	private List<String> tmpSheetLoginIdList;
	private List<String> tmpSheetHpList;
	private List<String> usrSheetLoginIdList;
	private List<String> failSheetLoginIdList;
	private List<TbUsrVo> tmpSheetList;
	
	// 앱푸쉬 알림 수신 여부
	private String cntsNtfRcvYn;
	private String stdyNtfRcvYn;
	private String srvcNtfRcvYn;
	
	// 탭검색 대상 회원코드
	private String tabCd;
	private String trgtUsrCd;
	
	// 회원상세 팝업
	private String orgnNm;
	private String orgnDiv;
	
	private String hdqrtCd;
	private String hdqrtNm;
	private String offcCd;
	private String offcNm;
	private String teamCd;
	private String teamNm;
	private String positNm;
	private String smsType;
	private String usrNmDec;	//마스킹해제 성명
	private String hpDec;		//마스킹해제 휴대폰번호
	private String emlDec;		//마스킹해제 이메일
	private String brthDtDec;	//마스킹해제 생년월일
	private String usrTypeNm;	//회원유형 명칭
	
	private String cpCd;
	private String cpNm;
	private String ttrCd;
	private String autoAppvlYn;
	private String oldTtrId;
	
	
	public String getUsrTypeNm() {
		return usrTypeNm;
	}
	public void setUsrTypeNm(String usrTypeNm) {
		this.usrTypeNm = usrTypeNm;
	}
	public String getHpDec() {
		return hpDec;
	}
	public void setHpDec(String hpDec) {
		this.hpDec = hpDec;
	}
	public String getEmlDec() {
		return emlDec;
	}
	public void setEmlDec(String emlDec) {
		this.emlDec = emlDec;
	}
	public void setTmpSheetHpList(List<String> tmpSheetHpList) {
		this.tmpSheetHpList = tmpSheetHpList;
	}
	public String getUsrNmDec() {
		return usrNmDec;
	}
	public void setUsrNmDec(String usrNmDec) {
		this.usrNmDec = usrNmDec;
	}
	// 알림창 첨부파일
	private List<TimsFileVo> fileList = new ArrayList<TimsFileVo>();
	// 알림창 첨부이미지
	private List<TimsFileVo> imgFileList = new ArrayList<TimsFileVo>();
	
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public List<TimsFileVo> getFileList() {
		List<TimsFileVo> ret = null;
		if(this.fileList != null) {
			ret = new ArrayList<TimsFileVo>();
			ret = this.fileList; 
		}
		return ret;
	}
	public void setFileList(List<TimsFileVo> fileList) {
		if(fileList != null) {
			this.fileList = new ArrayList<TimsFileVo>();
			this.fileList.addAll(fileList);
		}
	}
	public List<TimsFileVo> getImgFileList() {
		List<TimsFileVo> ret = null;
		if(this.imgFileList != null) {
			ret = new ArrayList<TimsFileVo>();
			ret = this.imgFileList; 
		}
		return ret;
	}
	public void setImgFileList(List<TimsFileVo> imgFileList) {
		if(imgFileList != null) {
			this.imgFileList = new ArrayList<TimsFileVo>();
			this.imgFileList.addAll(imgFileList);
		}
	}
	
	public String getHdqrtCd() {
		return hdqrtCd;
	}
	public void setHdqrtCd(String hdqrtCd) {
		this.hdqrtCd = hdqrtCd;
	}
	public String getHdqrtNm() {
		return hdqrtNm;
	}
	public void setHdqrtNm(String hdqrtNm) {
		this.hdqrtNm = hdqrtNm;
	}
	public String getOffcCd() {
		return offcCd;
	}
	public void setOffcCd(String offcCd) {
		this.offcCd = offcCd;
	}
	public String getOffcNm() {
		return offcNm;
	}
	public void setOffcNm(String offcNm) {
		this.offcNm = offcNm;
	}
	public String getTeamCd() {
		return teamCd;
	}
	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	public String getTeamNm() {
		return teamNm;
	}
	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}
	public String getPositNm() {
		return positNm;
	}
	public void setPositNm(String positNm) {
		this.positNm = positNm;
	}
	public String getUsrCds() {
		return usrCds;
	}
	public void setUsrCds(String usrCds) {
		this.usrCds = usrCds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrvPaperCd() {
		return srvPaperCd;
	}
	public void setSrvPaperCd(String srvPaperCd) {
		this.srvPaperCd = srvPaperCd;
	}
	public String getTabCd() {
		return tabCd;
	}
	public void setTabCd(String tabCd) {
		this.tabCd = tabCd;
	}
	public String getTrgtUsrCd() {
		return trgtUsrCd;
	}
	public void setTrgtUsrCd(String trgtUsrCd) {
		this.trgtUsrCd = trgtUsrCd;
	}
	public String getCntsNtfRcvYn() {
		return cntsNtfRcvYn;
	}
	public void setCntsNtfRcvYn(String cntsNtfRcvYn) {
		this.cntsNtfRcvYn = cntsNtfRcvYn;
	}
	public String getStdyNtfRcvYn() {
		return stdyNtfRcvYn;
	}
	public void setStdyNtfRcvYn(String stdyNtfRcvYn) {
		this.stdyNtfRcvYn = stdyNtfRcvYn;
	}
	public String getSrvcNtfRcvYn() {
		return srvcNtfRcvYn;
	}
	public void setSrvcNtfRcvYn(String srvcNtfRcvYn) {
		this.srvcNtfRcvYn = srvcNtfRcvYn;
	}
	public String getCnts() {
		return cnts;
	}
	public void setCnts(String cnts) {
		this.cnts = cnts;
	}
	public String getTmptCd() {
		return tmptCd;
	}
	public void setTmptCd(String tmptCd) {
		this.tmptCd = tmptCd;
	}
	public String getBldDivCd() {
		return bldDivCd;
	}
	public void setBldDivCd(String bldDivCd) {
		this.bldDivCd = bldDivCd;
	}
	
	
	public List<String> getTmpSheetLoginIdList() {
		List<String> ret = null;
		if(this.tmpSheetLoginIdList != null) {
			ret = new ArrayList<String>();
			ret = this.tmpSheetLoginIdList; 
		}
		return ret;
	}
	public void setTmpSheetLoginIdList(List<String> tmpSheetLoginIdList) {
		if(tmpSheetLoginIdList != null) {
			this.tmpSheetLoginIdList = new ArrayList<String>();
			this.tmpSheetLoginIdList.addAll(tmpSheetLoginIdList);
		}
	}
	public List<String> getUsrSheetLoginIdList() {
		List<String> ret = null;
		if(this.usrSheetLoginIdList != null) {
			ret = new ArrayList<String>();
			ret = this.usrSheetLoginIdList; 
		}
		return ret;
	}
	public void setUsrSheetLoginIdList(List<String> usrSheetLoginIdList) {
		if(usrSheetLoginIdList != null) {
			this.usrSheetLoginIdList = new ArrayList<String>();
			this.usrSheetLoginIdList.addAll(usrSheetLoginIdList);
		}
	}
	
	
	
	public List<String> getTmpSheetHpList() {
		List<String> ret = null;
		if(this.tmpSheetHpList != null) {
			ret = new ArrayList<String>();
			ret = this.tmpSheetHpList; 
		}
		return ret;
	}
	public void getTmpSheetHpList(List<String> tmpSheetHpList) {
		if(tmpSheetHpList != null) {
			this.tmpSheetHpList = new ArrayList<String>();
			this.tmpSheetHpList.addAll(tmpSheetHpList);
		}
	}
	
	
	
	public List<String> getFailSheetLoginIdList() {
		List<String> ret = null;
		if(this.failSheetLoginIdList != null) {
			ret = new ArrayList<String>();
			ret = this.failSheetLoginIdList; 
		}
		return ret;
	}
	public void setFailSheetLoginIdList(List<String> failSheetLoginIdList) {
		if(failSheetLoginIdList != null) {
			this.failSheetLoginIdList = new ArrayList<String>();
			this.failSheetLoginIdList.addAll(failSheetLoginIdList);
		}
	}
	

	public List<TbUsrVo> getTmpSheetList() {
		List<TbUsrVo> ret = null;
		if(this.tmpSheetList != null) {
			ret = new ArrayList<TbUsrVo>();
			ret = this.tmpSheetList; 
		}
		return ret;
	}

	public void setTmpSheetList(List<TbUsrVo> tmpSheetList) {
		if(tmpSheetList != null) {
			this.tmpSheetList = new ArrayList<TbUsrVo>();
			this.tmpSheetList.addAll(tmpSheetList);
		}
	}
	
	
	public String getWorkDivNm() {
		return workDivNm;
	}
	public void setWorkDivNm(String workDivNm) {
		this.workDivNm = workDivNm;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getAppPushUseYn() {
		return appPushUseYn;
	}
	public void setAppPushUseYn(String appPushUseYn) {
		this.appPushUseYn = appPushUseYn;
	}
	public String getOrgnNm() {
		return orgnNm;
	}
	public void setOrgnNm(String orgnNm) {
		this.orgnNm = orgnNm;
	}
	public String getNtfLinkUrl() {
		return ntfLinkUrl;
	}
	public void setNtfLinkUrl(String ntfLinkUrl) {
		this.ntfLinkUrl = ntfLinkUrl;
	}
	public String getPrvsGisuNo() {
		return prvsGisuNo;
	}
	public void setPrvsGisuNo(String prvsGisuNo) {
		this.prvsGisuNo = prvsGisuNo;
	}
	public String getPrvsGisuCd() {
		return prvsGisuCd;
	}
	public void setPrvsGisuCd(String prvsGisuCd) {
		this.prvsGisuCd = prvsGisuCd;
	}
	public String getPrvsGisuAtndCd() {
		return prvsGisuAtndCd;
	}
	public void setPrvsGisuAtndCd(String prvsGisuAtndCd) {
		this.prvsGisuAtndCd = prvsGisuAtndCd;
	}
	public String getFrrdGisuCd() {
		return frrdGisuCd;
	}
	public void setFrrdGisuCd(String frrdGisuCd) {
		this.frrdGisuCd = frrdGisuCd;
	}
	public String getNtfDivCd() {
		return ntfDivCd;
	}
	public void setNtfDivCd(String ntfDivCd) {
		this.ntfDivCd = ntfDivCd;
	}
	public String getMsgTypeCd() {
		return msgTypeCd;
	}
	public void setMsgTypeCd(String msgTypeCd) {
		this.msgTypeCd = msgTypeCd;
	}
	public String getNteTitle() {
		return nteTitle;
	}
	public void setNteTitle(String nteTitle) {
		this.nteTitle = nteTitle;
	}
	public String getNteCont() {
		return nteCont;
	}
	public void setNteCont(String nteCont) {
		this.nteCont = nteCont;
	}
	public String getAuthGrpNm() {
		return authGrpNm;
	}
	public void setAuthGrpNm(String authGrpNm) {
		this.authGrpNm = authGrpNm;
	}
	public String getAsgnYn() {
		return asgnYn;
	}
	public void setAsgnYn(String asgnYn) {
		this.asgnYn = asgnYn;
	}
	public String getEmngDtm() {
		return emngDtm;
	}
	public void setEmngDtm(String emngDtm) {
		this.emngDtm = emngDtm;
	}
	public String getAtndHstCnt() {
		return atndHstCnt;
	}
	public void setAtndHstCnt(String atndHstCnt) {
		this.atndHstCnt = atndHstCnt;
	}
	public String getLoginIdOx() {
		return loginIdOx;
	}
	public void setLoginIdOx(String loginIdOx) {
		this.loginIdOx = loginIdOx;
	}
	public String getSlpWthdCd() {
		return slpWthdCd;
	}
	public void setSlpWthdCd(String slpWthdCd) {
		this.slpWthdCd = slpWthdCd;
	}
	public String getTtrTypeCd() {
		return ttrTypeCd;
	}
	public void setTtrTypeCd(String ttrTypeCd) {
		this.ttrTypeCd = ttrTypeCd;
	}
	public String getGndrNm() {
		return gndrNm;
	}
	public void setGndrNm(String gndrNm) {
		this.gndrNm = gndrNm;
	}
	public String getJobLvlTypeCd() {
		return jobLvlTypeCd;
	}
	public void setJobLvlTypeCd(String jobLvlTypeCd) {
		this.jobLvlTypeCd = jobLvlTypeCd;
	}
	public String getJobLvlTypeNm() {
		return jobLvlTypeNm;
	}
	public void setJobLvlTypeNm(String jobLvlTypeNm) {
		this.jobLvlTypeNm = jobLvlTypeNm;
	}
	public String getTtrTypeNm() {
		return ttrTypeNm;
	}
	public void setTtrTypeNm(String ttrTypeNm) {
		this.ttrTypeNm = ttrTypeNm;
	}
	public String getTtrDgrCd() {
		return ttrDgrCd;
	}
	public void setTtrDgrCd(String ttrDgrCd) {
		this.ttrDgrCd = ttrDgrCd;
	}
	public String getTtrDgrNm() {
		return ttrDgrNm;
	}
	public void setTtrDgrNm(String ttrDgrNm) {
		this.ttrDgrNm = ttrDgrNm;
	}
	public String getTtrMajorCd() {
		return ttrMajorCd;
	}
	public void setTtrMajorCd(String ttrMajorCd) {
		this.ttrMajorCd = ttrMajorCd;
	}
	public String getTtrMajorNm() {
		return ttrMajorNm;
	}
	public void setTtrMajorNm(String ttrMajorNm) {
		this.ttrMajorNm = ttrMajorNm;
	}
	public String getCrsNm() {
		return crsNm;
	}
	public void setCrsNm(String crsNm) {
		this.crsNm = crsNm;
	}
	public String getGisuNm() {
		return gisuNm;
	}
	public void setGisuNm(String gisuNm) {
		this.gisuNm = gisuNm;
	}
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	public String getOfflineStartDt() {
		return offlineStartDt;
	}
	public void setOfflineStartDt(String offlineStartDt) {
		this.offlineStartDt = offlineStartDt;
	}
	public String getOfflineEndDt() {
		return offlineEndDt;
	}
	public void setOfflineEndDt(String offlineEndDt) {
		this.offlineEndDt = offlineEndDt;
	}
	public String getStdyCode() {
		return stdyCode;
	}
	public void setStdyCode(String stdyCode) {
		this.stdyCode = stdyCode;
	}
	public String getDpartNm() {
		return dpartNm;
	}
	public void setDpartNm(String dpartNm) {
		this.dpartNm = dpartNm;
	}
	public String getGisuAtndCd() {
		return gisuAtndCd;
	}
	public void setGisuAtndCd(String gisuAtndCd) {
		this.gisuAtndCd = gisuAtndCd;
	}
	public String getGisuAtndApplnCd() {
		return gisuAtndApplnCd;
	}
	public void setGisuAtndApplnCd(String gisuAtndApplnCd) {
		this.gisuAtndApplnCd = gisuAtndApplnCd;
	}
	public String getGisuNo() {
		return gisuNo;
	}
	public void setGisuNo(String gisuNo) {
		this.gisuNo = gisuNo;
	}
	public String getGisuYear() {
		return gisuYear;
	}
	public void setGisuYear(String gisuYear) {
		this.gisuYear = gisuYear;
	}
	public String getFullStartDt() {
		return fullStartDt;
	}
	public void setFullStartDt(String fullStartDt) {
		this.fullStartDt = fullStartDt;
	}
	public String getFullEndDt() {
		return fullEndDt;
	}
	public void setFullEndDt(String fullEndDt) {
		this.fullEndDt = fullEndDt;
	}
	public String getFullStartEndDt() {
		return fullStartEndDt;
	}
	public void setFullStartEndDt(String fullStartEndDt) {
		this.fullStartEndDt = fullStartEndDt;
	}
	public String getCrsDivCd() {
		return crsDivCd;
	}
	public void setCrsDivCd(String crsDivCd) {
		this.crsDivCd = crsDivCd;
	}
	public String getCrsDivNm() {
		return crsDivNm;
	}
	public void setCrsDivNm(String crsDivNm) {
		this.crsDivNm = crsDivNm;
	}
	public String getTotEduTime() {
		return totEduTime;
	}
	public void setTotEduTime(String totEduTime) {
		this.totEduTime = totEduTime;
	}
	public String getAppvlTime() {
		return appvlTime;
	}
	public void setAppvlTime(String appvlTime) {
		this.appvlTime = appvlTime;
	}
	public String getCmptNo() {
		return cmptNo;
	}
	public void setCmptNo(String cmptNo) {
		this.cmptNo = cmptNo;
	}
	public String getGisuCd() {
		return gisuCd;
	}
	public void setGisuCd(String gisuCd) {
		this.gisuCd = gisuCd;
	}
	public String getTotStdyRate() {
		return totStdyRate;
	}
	public void setTotStdyRate(String totStdyRate) {
		this.totStdyRate = totStdyRate;
	}
	public String getTotScore() {
		return totScore;
	}
	public void setTotScore(String totScore) {
		this.totScore = totScore;
	}
	public String getLoginPwdChk() {
		return loginPwdChk;
	}
	public void setLoginPwdChk(String loginPwdChk) {
		this.loginPwdChk = loginPwdChk;
	}
	public String getLoginLimitChkYn() {
		return loginLimitChkYn;
	}
	public void setLoginLimitChkYn(String loginLimitChkYn) {
		this.loginLimitChkYn = loginLimitChkYn;
	}
	public String getRvl() {
		return rvl;
	}
	public void setRvl(String rvl) {
		this.rvl = rvl;
	}
	public String getNewLoginPwd() {
		return newLoginPwd;
	}
	public void setNewLoginPwd(String newLoginPwd) {
		this.newLoginPwd = newLoginPwd;
	}
	public String getPrsnInfoAgrYn() {
		return prsnInfoAgrYn;
	}
	public void setPrsnInfoAgrYn(String prsnInfoAgrYn) {
		this.prsnInfoAgrYn = prsnInfoAgrYn;
	}
	public String getSlpYn() {
		return slpYn;
	}
	public void setSlpYn(String slpYn) {
		this.slpYn = slpYn;
	}
	public String getSleWthdDiv() {
		return sleWthdDiv;
	}
	public void setSleWthdDiv(String sleWthdDiv) {
		this.sleWthdDiv = sleWthdDiv;
	}
	public String getWthdCd() {
		return wthdCd;
	}
	public void setWthdCd(String wthdCd) {
		this.wthdCd = wthdCd;
	}
	public String getLoginPwdCnfm() {
		return loginPwdCnfm;
	}
	public void setLoginPwdCnfm(String loginPwdCnfm) {
		this.loginPwdCnfm = loginPwdCnfm;
	}
	public String getSecessionRsn() {
		return secessionRsn;
	}
	public void setSecessionRsn(String secessionRsn) {
		this.secessionRsn = secessionRsn;
	}
	public String getSecessionYn() {
		return secessionYn;
	}
	public void setSecessionYn(String secessionYn) {
		this.secessionYn = secessionYn;
	}
	public String getConnInfo() {
		return connInfo;
	}
	public void setConnInfo(String connInfo) {
		this.connInfo = connInfo;
	}
	public String getAdminAcsYn() {
		return adminAcsYn;
	}
	public void setAdminAcsYn(String adminAcsYn) {
		this.adminAcsYn = adminAcsYn;
	}
	public String getAcsType() {
		return acsType;
	}
	public void setAcsType(String acsType) {
		this.acsType = acsType;
	}
	public String getAuthExprtDt() {
		return authExprtDt;
	}
	public void setAuthExprtDt(String authExprtDt) {
		this.authExprtDt = authExprtDt;
	}
	public String getEduGrpCd() {
		return eduGrpCd;
	}
	public void setEduGrpCd(String eduGrpCd) {
		this.eduGrpCd = eduGrpCd;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}
	public String getSctrCdNm() {
		return sctrCdNm;
	}
	public void setSctrCdNm(String sctrCdNm) {
		this.sctrCdNm = sctrCdNm;
	}
	public String getReveal() {
		return reveal;
	}
	public void setReveal(String reveal) {
		this.reveal = reveal;
	}
	public String getTempLoginPwd() {
		return tempLoginPwd;
	}
	public void setTempLoginPwd(String tempLoginPwd) {
		this.tempLoginPwd = tempLoginPwd;
	}
	public String getSecessionExeDtm() {
		return secessionExeDtm;
	}
	public void setSecessionExeDtm(String secessionExeDtm) {
		this.secessionExeDtm = secessionExeDtm;
	}
	public String getCrsCd() {
		return crsCd;
	}
	public void setCrsCd(String crsCd) {
		this.crsCd = crsCd;
	}
	public String getAtndStatsCd() {
		return atndStatsCd;
	}
	public void setAtndStatsCd(String atndStatsCd) {
		this.atndStatsCd = atndStatsCd;
	}
	public String getAppltUsrCd() {
		return appltUsrCd;
	}
	public void setAppltUsrCd(String appltUsrCd) {
		this.appltUsrCd = appltUsrCd;
	}
	public String getFacDivCd() {
		return facDivCd;
	}
	public void setFacDivCd(String facDivCd) {
		this.facDivCd = facDivCd;
	}
	public String getFacDivNm() {
		return facDivNm;
	}
	public void setFacDivNm(String facDivNm) {
		this.facDivNm = facDivNm;
	}
	public String getFailRsn() {
		return failRsn;
	}
	public void setFailRsn(String failRsn) {
		this.failRsn = failRsn;
	}

	/*
	 * public KakaotalkMessage getKakaotalkMessage() { return kakaotalkMessage; }
	 * public void setKakaotalkMessage(KakaotalkMessage kakaotalkMessage) {
	 * this.kakaotalkMessage = kakaotalkMessage; } public TextMessage
	 * getTextMessage() { return textMessage; } public void
	 * setTextMessage(TextMessage textMessage) { this.textMessage = textMessage; }
	 */
	public String getDrctrNm() {
		return drctrNm;
	}
	public void setDrctrNm(String drctrNm) {
		this.drctrNm = drctrNm;
	}
	public String getBreuNm() {
		return breuNm;
	}
	public void setBreuNm(String breuNm) {
		this.breuNm = breuNm;
	}
	public String getCmndNm() {
		return cmndNm;
	}
	public void setCmndNm(String cmndNm) {
		this.cmndNm = cmndNm;
	}
	public String getSctrNm() {
		return sctrNm;
	}
	public void setSctrNm(String sctrNm) {
		this.sctrNm = sctrNm;
	}
	public String getBrthDtDec() {
		return brthDtDec;
	}
	public void setBrthDtDec(String brthDtDec) {
		this.brthDtDec = brthDtDec;
	}
	public String getCpCd() {
		return cpCd;
	}
	public void setCpCd(String cpCd) {
		this.cpCd = cpCd;
	}
	public String getCpNm() {
		return cpNm;
	}
	public void setCpNm(String cpNm) {
		this.cpNm = cpNm;
	}
	public String getTtrCd() {
		return ttrCd;
	}
	public void setTtrCd(String ttrCd) {
		this.ttrCd = ttrCd;
	}
	public String getOrgnDiv() {
		return orgnDiv;
	}
	public void setOrgnDiv(String orgnDiv) {
		this.orgnDiv = orgnDiv;
	}
	public String getAutoAppvlYn() {
		return autoAppvlYn;
	}
	public void setAutoAppvlYn(String autoAppvlYn) {
		this.autoAppvlYn = autoAppvlYn;
	}
	public String getOldTtrId() {
		return oldTtrId;
	}
	public void setOldTtrId(String oldTtrId) {
		this.oldTtrId = oldTtrId;
	}
	
}