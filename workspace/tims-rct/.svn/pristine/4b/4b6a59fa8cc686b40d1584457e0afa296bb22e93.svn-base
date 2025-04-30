package com.atsys.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 게시판 공통 상수 정의 클래스
 * <pre>
 * system : 공통
 * menu   : 공통
 * source : CmmCdConstants.java
 * desc   : 공통 상수 정의 클래스
 * </pre>
 * @version
 * <pre>
 * 1.0	2009. 04. 11		송기선		최초 작성
 * 1.1  2025. 04. 11		백세진		내용 수정
 * </pre>
 */

@Component
public class CmmCdConstants extends HashMap<Object,Object>{
	private Logger LOGGER = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6163132393501243351L;

	public Map<Object,Object> map;
	
	//채용학기(lrgDiv : RCT001, use column : NTC_SMT)
	public static final String		NTC_SMT_IRREGL				= "00";	//수시
	public static final String		NTC_SMT_FIRST				= "10";	//1학기
	public static final String		NTC_SMT_SECOND		  		= "20";	//2학기
	public static final String		NTC_SMT_SUMMER				= "30";	//계절학기(하계) 
	public static final String		NTC_SMT_WINTER				= "40";	//계절학기(동계)  
	
	//채용구분(lrgDiv : RCT002, use column : NTC_TYPE) 
	public static final String		RECRUIT_TYPE_FULLTIME		= "10";	//전임
	public static final String		RECRUIT_TYPE_PARTTIME		= "20";	//비전임
	public static final String		RECRUIT_TYPE_LECTURER		= "30";	//강사
	public static final String		RECRUIT_TYPE_STAFF			= "40";	//일반직원  
	
	//공고상태(lrgDiv : RCT003, use column : NTC_STATUS)
	public static final String		NTC_STATUS_WRITING			= "10";	//입력중
	public static final String		NTC_STATUS_OPEN				= "20";	//공고중
	public static final String		NTC_STATUS_EVALUATING		= "30";	//심사평가중
	public static final String		NTC_STATUS_RESULT			= "40";	//합격자발표
	
	//교원구분(lrgDiv : RCT004(교원구분)|RCT005(직원구분), use column : STAFF_TYPE)
	//교원구분
	public static final String STAFF_TYPE_GENERAL 				= "10010"; // 일반
	public static final String STAFF_TYPE_CULTURE 				= "10020"; // 교양
	public static final String STAFF_TYPE_RESEARCH_ONLY 		= "10030"; // 연구전담
	public static final String STAFF_TYPE_PROFESSIONAL 			= "10040"; // 전문
	public static final String STAFF_TYPE_INDUS_COOP 			= "10050"; // 산학협력중점
	public static final String STAFF_TYPE_LECTURE_ONLY 			= "10060"; // 강의전담
	public static final String STAFF_TYPE_HK 					= "10070"; // HK

	public static final String STAFF_TYPE_ADJUNCT 				= "20010"; // 겸임
	public static final String STAFF_TYPE_INVITED 				= "20020"; // 객원
	public static final String STAFF_TYPE_DISTINGUISHED 		= "20030"; // 석좌
	public static final String STAFF_TYPE_HONORARY 				= "20040"; // 명예
	public static final String STAFF_TYPE_SPECIAL_APPOINT 		= "20050"; // 초빙
	public static final String STAFF_TYPE_RESEARCH 				= "20060"; // 연구
	public static final String STAFF_TYPE_INDUS_RESEARCH 		= "20070"; // 산학협력
	public static final String STAFF_TYPE_SPECIAL 				= "20080"; // 특임 
	public static final String STAFF_TYPE_LECTURER 				= "30010"; // 강사

	//직원구분
	public static final String STAFF_TYPE_G9 					= "40010"; // (일반직)9급
	public static final String STAFF_TYPE_G8 					= "40020"; // (일반직)8급
	public static final String STAFF_TYPE_G7 					= "40030"; // (일반직)7급
	public static final String STAFF_TYPE_T9 					= "40510"; // (기술직)9급
	public static final String STAFF_TYPE_T8 					= "40520"; // (기술직)8급
	public static final String STAFF_TYPE_T7 					= "40530"; // (기술직)7급
	
	//평가차수(lrgDiv : RCT006, use column : STEP)
	public static final String STEP_1							= "1"; // 1차
	public static final String STEP_2							= "2"; // 2차
	public static final String STEP_3							= "3"; // 3차
	
	//채용공고정보구분(lrgDiv : RCT007, use column : NTC_INFO_TYPE)
	public static final String NTC_INFO_TYPE_QUALIFICATION 	 	= "1"; // 지원자격
	public static final String NTC_INFO_TYPE_NOTICE          	= "2"; // 유의사항
	public static final String NTC_INFO_TYPE_METHOD          	= "3"; // 지원방법
	public static final String NTC_INFO_TYPE_DOCUMENT        	= "4"; // 제출서류
	public static final String NTC_INFO_TYPE_PROCESS         	= "5"; // 전형절차
	public static final String NTC_INFO_TYPE_ETC             	= "6"; // 기타및서식
	
	//제출서류구분(lrgDiv : RCT008, use column : DOC_CD)
	public static final String DOC_CD_GRADUATION_CERT          	= "1010"; // 졸업증명서
	public static final String DOC_CD_TRANSCRIPT            	= "1020"; // 성적증명서
	public static final String DOC_CD_CAREER_CERT              	= "1030"; // 경력증명서
	public static final String DOC_CD_EMPLOYMENT_CERT          	= "1040"; // 재직증명서
	public static final String DOC_CD_LICENSE_COPY             	= "1050"; // 자격증사본
	public static final String DOC_CD_LANGUAGE_SCORE           	= "1060"; // 어학성적표
	public static final String DOC_CD_RESEARCH_EVIDENCE        	= "1070"; // 연구실적목록 및 증빙자료
	public static final String DOC_CD_RECOMMENDATION_LETTER    	= "1080"; // 추천서
	public static final String DOC_CD_MILITARY_CERT            	= "1090"; // 병적증명서
	public static final String DOC_CD_DISABLED_VETERAN_CERT    	= "1100"; // 장애인 및 국가유공자 증명서  
	
	//지원서상태(lrgDiv : RCT009, use column : APP_STATUS)
	public static final String		APP_STATUS_SAVE				= "1";	//임시저장
	public static final String		APP_STATUS_SUBMIT			= "2";	//제출완료
	public static final String		APP_STATUS_RECEIVED		    = "3";	//접수확인
	public static final String		APP_STATUS_REJECTED			= "4";	//접수반려 
	
	//평가위원구분(lrgDiv : RCT011, use column : JDG_DIV)
	public static final String		JDG_DIV_INTERNAL 			= "1"; // 내부
	public static final String		JDG_DIV_EXTERNAL 		    = "2"; // 외부
	
	//심사구분(lrgDiv : RCT012, use column : QST_TYPE)
	public static final String		QST_TYPE_BASIC				= "10";	// 기초심사
	public static final String		QST_TYPE_MAJOR				= "20";	// 전공심사
	public static final String		QST_TYPE_SEMINAR 			= "30";	// 세미나
	public static final String		QST_TYPE_INTERVIEW			= "40";	// 면접 
	
	//합격단계(lrgDiv : RCT014, use column : PASS_STEP)
	public static final String 		PASS_STEP_DOC     			= "0"; // 서류심사
	public static final String 		PASS_STEP_FIRST   			= "1"; // 1차 대상자
	public static final String 		PASS_STEP_SECOND  			= "2"; // 2차 대상자
	public static final String 		PASS_STEP_THIRD   			= "3"; // 3차 대상자
	public static final String 		PASS_STEP_FINAL   			= "0"; // 최종 합격
	
	//메일도메인(lrgDiv : RCT30)
	public static final String		MAIL_DOMAIN_NAVER			= "naver.com";	//네이버
	public static final String		MAIL_DOMAIN_KAKAO			= "kakao.com";	//카카오
	public static final String		MAIL_DOMAIN_DAUM			= "daum.net";	//다음
	public static final String		MAIL_DOMAIN_GMAIL			= "gmail.com";	//구글 
	
	//병역관계(lrgDiv : RCT031, use column : MIL_TYPE)
	public static final String		MIL_TYPE_FULFILLED			="1"; // 군필
	public static final String		MIL_TYPE_UNFULFILLED		="2"; // 미필
	public static final String		MIL_TYPE_EXEMPTED			="3"; // 면제 
	public static final String		MIL_TYPE_NOT_APPLICABLE  	="9"; // 해당없음
	
	//병역계급(lrgDiv : RCT032, use column : MIL_RANK)
	public static final String 		MIL_TYPE_PRIVATE         	= "1"; // 이병
	public static final String 		MIL_TYPE_PFC             	= "2"; // 일병
	public static final String 		MIL_TYPE_CORPORAL       	= "3"; // 상병
	public static final String 		MIL_TYPE_SERGEANT        	= "4"; // 병장

	public static final String 		MIL_TYPE_SGT_GENERAL   		= "5"; // 일반하사
	public static final String 		MIL_TYPE_SGT_SHORT       	= "6"; // 단기하사
	public static final String 		MIL_TYPE_SGT_LONG        	= "7"; // 장기하사

	public static final String 		MIL_TYPE_STAFF_SGT      	= "8"; // 중사
	public static final String 		MIL_TYPE_MASTER_SGT      	= "9"; // 상사
	public static final String 		MIL_TYPE_WARRANT_OFFICER 	= "10"; // 준위

	public static final String 		MIL_TYPE_SECOND_LT       	= "11"; // 소위
	public static final String 		MIL_TYPE_FIRST_LT        	= "12"; // 중위
	public static final String 		MIL_TYPE_CAPTAIN         	= "13"; // 대위
	public static final String 		MIL_TYPE_MAJOR           	= "14"; // 소령
	public static final String 		MIL_TYPE_LT_COLONEL      	= "15"; // 중령
	public static final String 		MIL_TYPE_COLONEL         	= "16"; // 대령
	
	//학력구분(lrgDiv : RCT040, use column : EDU_TYPE)
	public static final String 		EDU_TYPE_HIGHSCHOOL       	= ""; // 고등학교
	public static final String 		EDU_TYPE_JUNIOR_COLLEGE   	= ""; // 전문대학교
	public static final String 		EDU_TYPE_UNIVERSITY       	= ""; // 대학교
	public static final String 		EDU_TYPE_GRADUATE_MASTER  	= ""; // 대학원(석사)
	public static final String 		EDU_TYPE_GRADUATE_DOCTOR  	= ""; // 대학원(박사)
	
	//취득학위구분(lrgDiv : RCT041, use column : ACQ_TYPE)
	/*public static final String 		ACQ_TYPE_DOCTOR            	= "1"; // 박사
	public static final String 		ACQ_TYPE_DOCTOR_COURSE     	= "2"; // 박사수료
	public static final String 		ACQ_TYPE_MASTER            	= "3"; // 석사
	public static final String 		ACQ_TYPE_MASTER_COURSE     	= "4"; // 석사수료
	public static final String		 ACQ_TYPE_BACHELOR         	= "5"; // 학사
	public static final String 		ACQ_TYPE_BELOW_HIGHSCHOOL  	= "6"; // 고졸이하
	public static final String 		ACQ_TYPE_ASSOCIATE         	= "7"; // 전문학사*/

	//국가코드(lrgDiv : RCT042, use column : COUNTRY_CD)
	public static final String 		COUNTRY_CD_KR            	= "KR"; // 대한민국
	public static final String 		COUNTRY_CD_US           	= "US"; // 미국
	public static final String 		COUNTRY_CD_CN            	= "CN"; // 중국
	public static final String 		COUNTRY_CD_JP            	= "JP"; // 일본
	public static final String 		COUNTRY_CD_GB            	= "GB"; // 영국
	public static final String 		COUNTRY_CD_FR           	= "FR"; // 프랑스
	public static final String 		COUNTRY_CD_DE           	= "DE"; // 독일
	
	//학위취득구분(lrgDiv : RCT043, use column : ACQ_TYPE)
  /*public static final String 		ACQ_TYPE_DOCTOR            	= "1"; // 박사
	public static final String 		ACQ_TYPE_DOCTOR_COURSE     	= "2"; // 박사수료
	public static final String 		ACQ_TYPE_MASTER            	= "3"; // 석사
	public static final String 		ACQ_TYPE_MASTER_COURSE     	= "4"; // 석사수료
	public static final String		 ACQ_TYPE_BACHELOR         	= "5"; // 학사
	public static final String 		ACQ_TYPE_BELOW_HIGHSCHOOL  	= "6"; // 고졸이하
	public static final String 		ACQ_TYPE_ASSOCIATE         	= "7"; // 전문학사*/

	//학점만점기준(lrgDiv : RCT044, use column : MAX_GPA)
	public static final String 		MAX_GPA_40            		= "40"; // 4.0
	public static final String 		MAX_GPA_43            		= "43"; // 4.3
	public static final String 		MAX_GPA_45            		= "45"; // 4.5
	
	//수상/자격/면허구분(lrgDiv : RCT050, use column : AWARD_TYPE)
	public static final String 		AWRD_TYPE_PRIZE    			= "1"; // 수상
	public static final String 		AWRD_TYPE_CERT     			= "2"; // 자격
	public static final String 		AWRD_TYPE_LICENSE  			= "3"; // 면허

	//수상명구분(lrgDiv : RCT051, use column : AWARD_NM)
	
	
	//업무그룹유형(lrgDiv : COM128)
	public static final String		AUTH_GRP_TYPE_TTR	= "01";// 강사유형
	public static final String		AUTH_GRP_TYPE_ADMIN	= "02";// 관리자유형
	public static final String		AUTH_GRP_TYPE_NONE	= "03";// 없음
	public static final String		AUTH_GRP_TYPE_OPR	= "04";// 운영기관
	
	//과정상태(lrgDiv : SEI04)
	public static final String		CRS_READY	= "1";// 준비중
	public static final String		CRS_APTV_RQ	= "2";// 승인요청
	public static final String		CRS_APTV	= "3";// 승인
	public static final String		CRS_COMP	= "4";// 반려
	public static final String		CRS_FINISH	= "5";// 종료
	
	//강사상태(lrgDiv : SEI09)
	public static final String		TTR_APR			= "1";// 승인
	public static final String		TTR_OPEN_REQ	= "2";// 공개요청
	public static final String		TTR_OPEN		= "3";// 공개중
	public static final String		TTR_COMP		= "4";// 반려
	public static final String		TTR_FINISH		= "5";// 중지/종료
	
	//과정속성(lrgDiv : COU02)
	public static final String		CRS_RCOM		= "4";// 추천
	public static final String		CRS_CERTIF		= "5";// 수료증
	public static final String		CRS_STAT_NOT 	= "3";// 통계제외
	public static final String		CRS_NOUSR_ATND	= "2";// 비회원 수강
	public static final String		CRS_DOWN_TASK	= "7";// 교재다운로드
	public static final String		CRS_TTR_CMPL	= "8";// 강사평가 수료 필수
	public static final String		CRS_TOP_FIX		= "6";// 상단고정
	public static final String		CRS_OFFLINE_OPEN = "9";// 오프라인 노출여부

	public CmmCdConstants() {
		if( map == null) {
			map = new HashMap<Object,Object>();
			
			Class<?> c = this.getClass();
			Field[] fields = c.getDeclaredFields();
			
			for( int i=0; i < fields.length; i++) {
				Field field = fields[i];
				int modifier = field.getModifiers();
				if( Modifier.isFinal( modifier) && !Modifier.isPrivate( modifier)){
					try {
						map.put( field.getName(), field.get( this));
					}
					catch( IllegalAccessException e){
						LOGGER.debug("info to IllegalAccessException", e);
					}
				}
			}
		}
	}
	
	@Override
	public Object get( Object key){
		return map.get( key);
	}
	
	@Override
	public Object put( Object key, Object value){
		Object preValue = map.get(key);
		map.put( key, value);
		
		return preValue;
	}
}
