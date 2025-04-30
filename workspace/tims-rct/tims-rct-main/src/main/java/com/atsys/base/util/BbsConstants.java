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
 * source : BbsConstants.java
 * desc   : 공통 상수 정의 클래스
 * </pre>
 * @version
 * <pre>
 * 1.0	2009. 04. 11		송기선		최초 작성
 * 1.1
 * </pre>
 */

@Component
public class BbsConstants extends HashMap<Object, Object>{
	private Logger LOGGER = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6163132393501243351L;

	public Map<Object,Object> map;
	
	// 게시판유형 BBS001
	public static final long 		BOARD_TYPE_GENERAL						= 1;	//일반
	public static final long 		BOARD_TYPE_NOTICE						= 2;	//공지
	public static final long 		BOARD_TYPE_PDS							= 3;	//자료실
	public static final long 		BOARD_TYPE_QNA							= 4;	//질의응답(Q&A)
	public static final long 		BOARD_TYPE_FAQ							= 5;	//FAQ
	
	// 문의글상태 BBS002
	public static final long		BOARD_APPLICATION						= 1;	//신청완료
	public static final long		BOARD_RECEIPT							= 2;	//접수완료 
	public static final long		BOARD_ANSWER							= 3;	//답변완료

	// 문의채용구분 BBS003
	public static final long		FULL_TIME_FACULTY						= 10;	//전임교원
	public static final long		NON_FULL_TIME_FACULTY					= 20;	//비전임교원
	public static final long		ADJUNCT_FACULTY							= 30;	//강사
	public static final long		ADMINISTRATIVE_STAFF					= 40;	//일반직원 
	
	// 게시판 접근 권한 BBS09
	public static final String		VL_BBS_AUTH_LIST							= "list"; // 목록
	public static final String		VL_BBS_AUTH_VIEW							= "view"; // 읽기
	public static final String		VL_BBS_AUTH_INSERT							= "insert"; // 등록
	public static final String		VL_BBS_AUTH_UPDATE							= "update"; // 수정
	public static final String		VL_BBS_AUTH_DELETE							= "delete"; // 삭제
	public static final String		VL_BBS_AUTH_COMMENT							= "comment"; // 댓글
	public static final String		VL_BBS_AUTH_ANSWER							= "answer"; // 답글
	public static final String		VL_BBS_AUTH_SECRET							= "secret"; // 비밀글
	public static final String		VL_BBS_AUTH_RECOMMEND						= "recommend"; // 추천
	public static final String		VL_BBS_AUTH_ADMIN							= "admin"; // 관리
	
	// 게시판 발송대상선택 BBS10
	public static final String		VL_BRD_SND_TRGT_TTR							= "1"; // 담당강사
	public static final String		VL_BRD_SND_TRGT_SEL							= "3"; // 발송대상선택
	
	// select tag의 label/value 컬럼 정의
//	public static final	String		OPTION_VALUE_CODE							= "smallDiv";
//	public static final	String		OPTION_LABEL_CODE							= "contentsDescr";
//	public static final	String		OPTION_INIT_CODE							= "0";
//	public static final	String		OPTIONS_SELECT_KEY_LARGE_DIV				= "largeDiv";
//	public static final String		CODE_ITEMS_PREFIX							= "codeItems_";
//	public static final	String		DEFAULT_SELECTED_VALUE						= "1";
	

	// 게시물 상태 : PH_CODE > PHB05
	public static final String		VL_BOARD_STATUS_OPENED					= "1";	// 운영
	public static final String		VL_BOARD_STATUS_CLOSED					= "2";	// 폐쇄
	
	// 알림 유형
	public static final long		VL_NOTIFY_FUNC_WRITE						= 0x00000001;	//게시물 등록 시
	public static final long		VL_NOTIFY_FUNC_ANSWER						= 0x00000002;	//답변 등록 시
	public static final long		VL_NOTIFY_FUNC_COMMENT						= 0x00000004;	//댓글 등록 시
	public static final long		VL_NOTIFY_FUNC_DELETE						= 0x00000008;	//게시물 삭제 시
	
	// 알림 대상
	public static final long		VL_NOTIFY_TO_ADMIN							= 0x00000001;	//전체/시스템관리자
	public static final long		VL_NOTIFY_TO_MANAGER						= 0x00000002;	//게시판관리자
	public static final long		VL_NOTIFY_TO_REG_USER						= 0x00000004;	//게시자
	public static final long		VL_NOTIFY_TO_ROOT_REG_USER					= 0x00000008;	//원글게시자
	
	// 알림 수단
	public static final long		VL_NOTIFY_BY_EMAIL							= 0x00000001;	//메일알림
	public static final long		VL_NOTIFY_BY_SMS							= 0x00000002;	//SMS알림 
	
	// 게시물 첨부파일 유형
	public static final long 		VL_ATTACH_FILE_GENERAL						= 0x00000001;	//일반첨부파일
	public static final long 		VL_ATTACH_FILE_LINK							= 0x00000002;	//첨부파일 중 본문 내 링크삽입 
	public static final long 		VL_ATTACH_FILE_IMAGE						= 0x00000004;	//첨부파일 중 본문 내 이미지삽입
	public static final long 		VL_ATTACH_FILE_MOVIE						= 0x00000008;	//첨부파일 중 본문 내 동영상삽입
	public static final long 		VL_ATTACH_FILE_THUMBNAIL					= 0x00000010;	//썸네일이미지
	
	// 게시물 상태 : PH_CODE > PHB05
	public static final String		VL_POSTING_STATUS_CREATED					= "0";	// 생성
	public static final String		VL_POSTING_STATUS_REGISTERED				= "1";	// 등록
	public static final String		VL_POSTING_STATUS_OPENING					= "2";	// 접수
	public static final String		VL_POSTING_STATUS_CLOSED					= "5";	// 마감
	public static final String		VL_POSTING_STATUS_CHECKED					= "6";	// 확인
	public static final String		VL_POSTING_STATUS_FINISHED					= "8";	// 종료
	public static final String		VL_POSTING_STATUS_DELETED					= "9";	// 삭제
	
	public static final String 		VL_BOARD_PORTLET_LATEST						= "latest";	//최신게시물
	public static final String 		VL_BOARD_PORTLET_MINE						= "mine";	//나의게시물
	
	public static final String 		VL_BOARD_PORTLET_VIEWMODE_GENERAL			= "general";	//일반
	public static final String 		VL_BOARD_PORTLET_VIEWMODE_GALLERY			= "gallery";	//갤러리
	public static final String 		VL_BOARD_PORTLET_VIEWMODE_MOVIE				= "movie";		//동영상
	
	public static final String 		VL_BOARD_PORTLET_ORDERBY_POPULAR			= "popular";	//인기순
	
	public static final String[]	BOARD_INFO_PARTS							= {"기본정보 및 속성", "게시물 분류", "설명"};
	public static final String 		BOARD_INFO_ASSORT							= "1";	//게시물 분류
	public static final String 		BOARD_INFO_PARTS_DESCR						= "2";	//설명
	
	public static final String		VL_BBS_DELIMETER							= ""; // 분리형 문자
	public static final String		VL_DELIMETER								= ""; // 분리형 문자
	
	public static final long 		VL_BOARD_ASSORT_LECTURE						= 3;	//강좌별 게시판
	public static final long 		VL_BOARD_ASSORT_EXPERT						= 4;	//전문가별 게시판
	public static final long 		VL_BOARD_ASSORT_PROJECT						= 28;	//공개데이터 게시판
	
	public static final String		VL_BBS_BAD_STATUS_INIT						= "1";	// 불량게시물 초기값
	public static final String		VL_BBS_BAD_STATUS_REG						= "2";	// 불량게시물 등록
	public static final String		VL_BBS_BAD_STATUS_FIX						= "3";	// 불량게시물 확정
	
	//게시판 메뉴ID(프로그램)
	public static final String		VL_PROGRAM_EDU_NOTICE					    = "hom1300e"; // 학습공지관리
	public static final String		VL_PROGRAM_FAQ							    = "hom1500e"; // FAQ관리
	public static final String		VL_PROGRAM_QA								= "hom1600e"; // Q&A관리
	public static final String		VL_PROGRAM_NOTICE					        = "hom1700e"; // 공지관리
	public static final String		VL_PROGRAM_PDS								= "hom1900e"; // 자료실
	public static final String		VL_PROGRAM_CONT								= "hom2100e"; // 안내콘텐츠
	public static final String		VL_PROGRAM_CURRICULUM						= "hom2200e"; // 안내콘텐츠
	
	//게시판 사용 구분
	public static final String		VL_BBS_BRD_MENU_DIV1						= "1";	// 일반
	public static final String		VL_BBS_BRD_MENU_DIV2						= "2";	// 강의실
	
	// 게시판 사용자 그룹 : PSB26
	public static final long 		VL_BBS_USER_GROUP_GENERAL					= 0x00000001; // 일반
	public static final long 		VL_BBS_USER_GROUP_MEMBER					= 0x00000002; // 회원
	public static final long 		VL_BBS_USER_GROUP_MANAGER					= 0x00000004; // 관리자
	
	public BbsConstants() {
		if(map == null) {
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
