/*************************************************************
  프로그램명 : MoveMethod.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.bind.support;

import org.springframework.util.StringUtils;

public class MoveMethod {
	
	private final static String MOVE = "_move";
	private static final String LIST = MOVE + "=list";
	private static final String NEW = MOVE + "=new";
	private static final String EDIT = MOVE + "=edit";;
	private static final String POP = MOVE + "=pop";;
	
	public enum Move {
		LIST, NEW, EDIT, POP;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
		
		public String toMethod() {
			return MOVE + "=" + toString();
		}
	}
	
	
	public static final Move resolveMoveMethod(String name) {
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		
		Move[] moves = Move.values();
		for (Move move : moves) {
			if (move.toString().equals(name)) {
				return move;
			}
		}

		return null;
	}
	
	public static boolean isValidMove(String move) {
		if (resolveMoveMethod(move) != null) {
			return true;
		}
		
		return false;
	}

	public static String getList() {
		return LIST;
	}

	public static String getNew() {
		return NEW;
	}

	public static String getEdit() {
		return EDIT;
	}

	public static String getPop() {
		return POP;
	}
	public static String getMove() {
		return MOVE;
	}
}