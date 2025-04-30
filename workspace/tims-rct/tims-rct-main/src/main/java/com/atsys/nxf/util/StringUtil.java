/*************************************************************
  프로그램명 : StringUtil.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;

public class StringUtil {
	/**
	 * 내용, 바이트  수, 속성을 받아서 그에 맞는 문자열을 리턴한다.
	 * @param src
	 * @param bSize
	 * @param attribute
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getString(String src, int bSize, String[] attribute) throws UnsupportedEncodingException{
		String src2 = src;
		src2 = (src2 == null) ? "" : src2;
		
		String returnStr = src2.trim();
		
		if(attribute != null){
			for(int i=0 ; i<attribute.length ; i++){
				//이름일 경우 공백을 없앤다.
				if(attribute[i].equals("name")){
					returnStr = deleteBlank(returnStr);
				}
				
				//공백을 없앤다.
				if(attribute[i].equals("deleteblank")){
					returnStr = deleteBlank(returnStr);
				}
				
				//주민등록번호나 사업자등록번호 등은 '-'(하이픈) 등의 문자는 없앤다.
				if(attribute[i].equals("numberform")){
					returnStr = getNumberForm(returnStr);
				}
				
				//주소에서 영문약자 'APT'은 '아파트'로 고친다. 또한 다른 영문은 소리나는대로 넣는다.
				if(attribute[i].equals("address")){
					returnStr = getAddress(returnStr);
					returnStr = getAlphabet(returnStr);
				}
				
				//알파벳을 없애고 한글 소리나는대로 바꾼다.
				if(attribute[i].equals("alphabet")){
					returnStr = getAlphabet(returnStr);
				}
				
				//숫자타입은 빈 공백만큼 앞에 0을 채운다.
				if(attribute[i].equals("number")){
					returnStr = getNumber(returnStr, bSize);
				}
				
				//문자열의 하이픈을 없앤다.
				if(attribute[i].equals("deletehyphen")){
					returnStr = deleteHyphen(returnStr);
				}
				
				//문자열의 슬래쉬를 없앤다.
				if(attribute[i].equals("deleteslash")){
					returnStr = deleteSlash(returnStr);
				}
				
				//전화번호
				if(attribute[i].equals("tel")){
					returnStr = getTelephone(returnStr);
				}
			}
		}
		
		returnStr = putBlankByteSize(returnStr, bSize);
		
		return returnStr;
	}
	
	/**
	 * 전화번호 형태로 리턴한다.
	 * @param src
	 * @return
	 */
	protected static String getTelephone(String src){
		String[] localNumbers = {"02", 
				                 "031", 
								 "032", 
								 "033", 
								 "041", 
								 "042", 
								 "043", 
								 "051", 
								 "052", 
								 "053", 
								 "054", 
								 "055", 
								 "061", 
								 "062", 
								 "063",  
								 "064"};
		String numform   = getNumberForm(src);
		
		String returnStr = "";
		String tmpnum    = "";
		String local     = "";
		String front     = "";
		String back      = "";
		
		for(int i=0 ; i<localNumbers.length ; i++){
			if(numform.indexOf(localNumbers[i]) == 0){
				local = localNumbers[i];
				break;
			}
		}
		
		//지역번호를 제외한 번호
		tmpnum = StringMng.replaceStr(numform, local, "");
		
		int size = tmpnum.length();
		
		if(size > 4){
			switch(size){
				case 5 : 
					front = tmpnum.substring(0, 1);
					back  = tmpnum.substring(1, 5);
					break;
				case 6 :
					front = tmpnum.substring(0, 2);
					back  = tmpnum.substring(2, 6);
					break;
				case 7 : 
					front = tmpnum.substring(0, 3);
					back  = tmpnum.substring(3, 7);
					break;
				case 8 :
					front = tmpnum.substring(0, 4);
					back  = tmpnum.substring(4, 8);
					break;
				default :
					front = tmpnum.substring(0, size - 4);
					back  = tmpnum.substring(size - 4, size);
					break;
			}
			
			returnStr = !local.equals("") ? local + "-" : "";
			returnStr = returnStr + front + "-" + back;
		}else if(size > 0 && size <5){
			returnStr = !local.equals("") ? local + "-" : "";
			returnStr = returnStr + tmpnum;
		}else{
			returnStr = !local.equals("") ? local : "";
		}
		
		return returnStr;
	}
	
	/**
	 * 문자열의 공백을 없앤다.
	 * @param src
	 * @return
	 */
	protected static String deleteBlank(String src){
		return StringMng.replaceStr(src, " ", "");
	}
	
	/**
	 * 문자열의 슬래쉬를 없앤다.
	 * @param src
	 * @return
	 */
	protected static String deleteSlash(String src){
		return StringMng.replaceStr(src, "/", "");
	}
	
	/**
	 * 문자열의 하이픈을 없앤다.
	 * @param src
	 * @return
	 */
	protected static String deleteHyphen(String src){
		return StringMng.replaceStr(src, "-", "");
	}
	
	/**
	 * 문자열의 숫자만 이용하여 바이트 크기만큼 앞에 0을 채워 리턴한다.
	 * @param src
	 * @param bSize
	 * @return
	 */
	protected static String getNumber(String src, int bSize){
		StringBuffer returnStr = new StringBuffer();
		
		char[] chArr = src.toCharArray();
		int size     = chArr.length;		
		
		for(int i=0 ; i<size ; i++){
			if('0' <= chArr[i] && chArr[i] <= '9'){
				returnStr.append(chArr[i]);
			}
		}
		
		String returnVal = StringMng.lpad(returnStr.toString(), '0', bSize);
		
		return returnVal;
	}
	
	/**
	 * 영문 알파벳을 한글 소리나는 대로 표시
	 * @param src
	 * @return
	 */
	protected static String getAlphabet(String src){
		StringBuffer returnStr = new StringBuffer();
		
		char[] chArr = src.toCharArray();
		
		for(int i=0 ; i<chArr.length ; i++){
			switch(chArr[i]){
				case 'A': returnStr.append("에이");	  break;
				case 'B': returnStr.append("비");	  break;
				case 'C': returnStr.append("씨");	  break;
				case 'D': returnStr.append("디");	  break;
				case 'E': returnStr.append("이");	  break;
				case 'F': returnStr.append("에프");	  break;
				case 'G': returnStr.append("지");	  break;
				case 'H': returnStr.append("에이치");	  break;
				case 'I': returnStr.append("아이");	  break;
				case 'J': returnStr.append("제이");	  break;
				case 'K': returnStr.append("케이");	  break;
				case 'L': returnStr.append("엘");	  break;
				case 'M': returnStr.append("엠");	  break;
				case 'N': returnStr.append("엔");	  break;
				case 'O': returnStr.append("오");	  break;
				case 'P': returnStr.append("피");	  break;
				case 'Q': returnStr.append("큐");	  break;
				case 'R': returnStr.append("알");	  break;
				case 'S': returnStr.append("에스");	  break;
				case 'T': returnStr.append("티");	  break;
				case 'U': returnStr.append("유");	  break;
				case 'V': returnStr.append("브이");	  break;
				case 'W': returnStr.append("더블유");	  break;
				case 'X': returnStr.append("엑스");	  break;
				case 'Y': returnStr.append("와이");	  break;
				case 'Z': returnStr.append("제트");	  break;
				case 'a': returnStr.append("에이");	  break;
				case 'b': returnStr.append("비");	  break;
				case 'c': returnStr.append("씨");	  break;
				case 'd': returnStr.append("디");	  break;
				case 'e': returnStr.append("이");	  break;
				case 'f': returnStr.append("에프");	  break;
				case 'g': returnStr.append("지");	  break;
				case 'h': returnStr.append("에이치");	  break;
				case 'i': returnStr.append("아이");	  break;
				case 'j': returnStr.append("제이");	  break;
				case 'k': returnStr.append("케이");	  break;
				case 'l': returnStr.append("엘");	  break;
				case 'm': returnStr.append("엠");	  break;
				case 'n': returnStr.append("엔");	  break;
				case 'o': returnStr.append("오");	  break;
				case 'p': returnStr.append("피");	  break;
				case 'q': returnStr.append("큐");	  break;
				case 'r': returnStr.append("알");	  break;
				case 's': returnStr.append("에스");	  break;
				case 't': returnStr.append("티");	  break;
				case 'u': returnStr.append("유");	  break;
				case 'v': returnStr.append("브이");	  break;
				case 'w': returnStr.append("더블유");	  break;
				case 'x': returnStr.append("엑스");	  break;
				case 'y': returnStr.append("와이");	  break;
				case 'z': returnStr.append("제트");	  break;
				default : returnStr.append(chArr[i]); break;
			}
		}
		
		return returnStr.toString();
	}
	
	/**
	 * 주소 중 영문약자 'APT' 등을 한글 아파트 로 고쳐줌
	 * @param src
	 * @return
	 */
	protected static String getAddress(String src){
		String[] iniArrAPT = {"APT", "A.P.T", "apt", "a.p.t"};
		String[] iniArrBui = {"BD", "B.D", "bd", "b.d"};
		
		for(int i=0 ; i<iniArrAPT.length ; i++){
			StringMng.replaceStr(src, iniArrAPT[i], "아파트");
		}
		
		for(int i=0 ; i<iniArrBui.length ; i++){
			StringMng.replaceStr(src, iniArrBui[i], "빌딩");
		}
		
		StringMng.replaceStr(src, "F", "층");
		
		return src;
	}
	
	/**
	 * 사이즈만큼 빈 공백을 삽입하고 리턴한다.
	 * @param src
	 * @param bSize
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	protected static String putBlankByteSize(String src, int bSize) throws UnsupportedEncodingException{
		String src2 = src;
		byte [] totalByte = src2.getBytes("euc-kr");
		
		int byteSize = totalByte.length;
		int forSize  = bSize - byteSize;
		
		for(int i=0 ; i<forSize ; i++){
			src2 = src2.concat(" ");
		}
			
		return src2;
	}
	
	/**
	 * 문자열중 숫자만 리턴
	 * @param src
	 * @return
	 */
	protected static String getNumberForm(String src){
		StringBuffer returnStr = new StringBuffer();
		
		char[] chArr = src.toCharArray();
		int size     = chArr.length;		
		
		for(int i=0 ; i<size ; i++){
			if('0' <= chArr[i] && chArr[i] <= '9'){
				returnStr.append(chArr[i]);
			}
		}
		
		return returnStr.toString();
	}
	
	/**
	 * 문자열의 \n을 <br>
	 * 로 치환함
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHtmlString(String value) {
		String str = value;
		if (str != null && str.length() > 0) {
			str = StringMng.replaceStr(str, "<br>", "\n");
		}

		return str;
	}
	
	/**
	 * 문자열을 시작위치와 종료위치의 사이를 반환함 = substring
	 * @param sourceStr
	 * @param startStr
	 * @param endStr
	 * @return
	 */
	public static String cutBetween(String sourceStr, String startStr, String endStr) 
	{
		if (sourceStr == null || startStr == null || endStr == null)
			return sourceStr;

		String tmpStr = sourceStr;
		int beginIndex = tmpStr.indexOf(startStr);
		
		if (beginIndex == -1)
			return sourceStr;
		else
			tmpStr = tmpStr.substring(beginIndex + startStr.length());
			
		int endIndex = tmpStr.indexOf(endStr); 
		
		if (endIndex == -1)
			return sourceStr;
		
		return tmpStr.substring(0, endIndex);
	}
	
	/**
	 * 문자열을 target 문자열을 기준으로 오른쪽의 문자열을 반환
	 * @param sourceStr
	 * @param targetStr
	 * @return
	 */
	public static String truncateRight(String sourceStr, String targetStr)
	{
		if (sourceStr == null || targetStr == null)
			return sourceStr;
		
		int endIndex = sourceStr.indexOf(targetStr);
		
		if (endIndex == -1)
			endIndex = sourceStr.length();
		
		return sourceStr.substring(0, endIndex);
	}
	/**
	 * 문자열의 첫번째 문자를 소문자로 변환
	 * @param value
	 * @return
	 */
	public static String deCapitalize(String value)
	{
		if (value == null || value.trim().equals(""))
			return "";
	
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}
	
	
	public static int oneByteMin = 0x0000;
	public static int oneByteMax = 0x007F;
	
	public static int twoByteMin = 0x0800;
	public static int twoByteMax = 0x07FF;
	
	
	public static int threeByteMin = 0x0800;
	public static int threeByteMax = 0xFFFF;
	
	public static int surrogateMin = 0x10000;
	public static int surrogateMax = 0x10FFFF;
	
	/**
	*게시판 제목 같은 글을 원하는 길이로 자릅니다.
	*한글( or 한글 + 영어) 제목을 자르면 깨지는걸 막기위한 메소드이다.
	*반환될 String 의 길이는 maxByteSize과 trail의 length를 더한 길이가 될 것이다.
	*마지막 character가 공백이면 이 공백은 제거된다.
	*@param str : 원래의 글
	*@param maxByteSize : 자르고자 하는 길이
	*@param trail : maxByteSize 만큼 자르고 뒤에 붙일 꼬리글
	*@return String 결과값
	*/
	//사용법 : getCutString("가나다abcdefghij", 10,"...")
	public static String getCutString(String str, int maxByteSize, String trail) throws UnsupportedEncodingException {
		int maxByteSize2 = maxByteSize;
		//널일 경우에는 그냥 리턴
		if (str == null)
			return null;
		
		if (str.length() == 0)
			return str;
		
		byte strByte[] = str.getBytes("UTF-8");
		
		if (strByte.length <= maxByteSize2)
			return str;
		
		//마지막 줄임말
		int trailByteSize = 0;
		
		//줄임말의 바이트 수 계산
		if (trail != null)
			trailByteSize = trail.getBytes("UTF-8").length;
		
		//실질적으로 포함되는 최대 바이트 수는 trailByte를 뺀 것이다.
		maxByteSize2 = maxByteSize2 - trailByteSize;
		
		//마지막 바이트 위치
		int endPos = 0;
		//현재까지 조사한 바이트 수
		int currByte = 0;
		
		for (int i = 0; i < str.length(); i++) {
			//순차적으로 문자들을 가져옴.
			char ch = str.charAt(i);
			
			//이 문자가 몇 바이트로 구성된 UTF-8 코드인지를 검사하여 currByte에 누적 시킨다.
			currByte = currByte + availibleByteNum(ch);
			
			//현재까지 조사된 바이트가 maxSize를 넘는다면 이전 단계 까지 누적된 바이트 까지를 유효한 바이트로 간주한다. 
			if (currByte > maxByteSize2) {
				endPos = currByte - availibleByteNum(ch);
				break;
			}
		}
		
		//원래 문자열을 바이트로 가져와서 유효한 바이트 까지 배열 복사를 한다.
		byte newStrByte[] = new byte[endPos];
		
		System.arraycopy(strByte, 0, newStrByte, 0, endPos);
		
		String newStr = new String(newStrByte, "UTF-8");
//		System.out.println(newStr.getBytes("UTF-8").length+ " " + newStr);
		newStr += trail;
		
		return newStr;
	}
	
	public static int availibleByteNum(char c) {
		int digit = c;
		
		if (oneByteMin <= digit && digit <= oneByteMax)
			return 1;  
		else if (twoByteMin <= digit && digit <= twoByteMax)
			return 2;
		else if (threeByteMin <= digit && digit <= threeByteMax)
			return 3;
		else if (surrogateMin <= digit && digit <= surrogateMax)
			return 4;
		
		return -1;
	}

	/*
	  * Clob 를 String 으로 변경
	  */
	 public static String clobToString(Clob clob) throws SQLException, IOException {
		 if(clob == null) {
			 return "";
		 }

		 StringBuffer strOut = new StringBuffer();

		 String str = "";

		 BufferedReader br = new BufferedReader(clob.getCharacterStream());
		 try{
			 while(true) {
				 str = br.readLine();
				 if(str != null) {
					 strOut.append(str);
				 }
				 else {
					 break;
				 }
			 }
		 }finally{
			 br.close();
		 }
		 
		 return strOut.toString();
	 }
}