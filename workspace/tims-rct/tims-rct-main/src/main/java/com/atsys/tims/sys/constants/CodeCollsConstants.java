/*************************************************************
  프로그램명 : CodeCollsConstants.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.constants;

/**
 * @Class Name : CodeCollsConstants.java
 * @Description : CodeCollsConstants class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 11. 15.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class CodeCollsConstants {

	public static enum CodeType {
		COM_1("COM040"), AFA_1("AFA002");
		
		private String type;
		
		CodeType( String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

	}
	

	public static enum UseStatus {
		IN_USE("Y"), NOT_USE("N"), ALL("A"), CURRENT("C");
		
		private String use;
		
		UseStatus( String use) {
			this.use = use;
		}

		public String getUse() {
			return use;
		}

	}
	

}
