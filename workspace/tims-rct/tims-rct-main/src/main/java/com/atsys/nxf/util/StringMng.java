/*************************************************************
  프로그램명 : StringMng.java
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

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class StringMng {
	
	private static Logger LOGGER = Logger.getLogger(StringMng.class);
	
	public StringMng() {
    }

    public static String fixedLength(String str, int len, String strTofill) {
    	String str2 = str;
        StringBuffer sb;
        try {
            byte srcByteArr[] = str2.getBytes();
            byte tmpBytArr[] = new byte[len];
            byte byteTofill = strTofill.getBytes()[0];
            for(int i = 0; i < len; i++) {
                if(i < srcByteArr.length) {
                    tmpBytArr[i] = srcByteArr[i];
                } else {
                    tmpBytArr[i] = byteTofill;

                }
            }
            if(srcByteArr.length > len) {
                int noMinus = 0;
                for(int i = len - 1; i >= 0 && tmpBytArr[i] < 0; i--) {
                    noMinus++;

                }
                if(noMinus % 2 == 1) {
                    tmpBytArr[len - 1] = byteTofill;
                }
                str2 = new String(tmpBytArr);
            }
            String s1 = new String(tmpBytArr);
            return s1;
        } catch(ArrayStoreException e) {
			LOGGER.info("info to Exception : " + e);
            sb = new StringBuffer();
        } catch(Exception e) {
			LOGGER.info("info to Exception : " + e);
            sb = new StringBuffer();
        }
        sb.setLength(len);
        for(int i = 0; i < len; ) {
            sb.setCharAt(i++, ' ');

        }
        String s = sb.toString();
        return s;
    }

    public static String fixedLength(String str, int len) {
        return fixedLength(str, len, " ");
    }

    public static int getByteLength(String str) {
        if(str == null) {
            return 0;
        } else {
            return str.getBytes().length;
        }
    }

    public static String replaceStr(String source, String keyStr, String toStr[]) {
        if(source == null) {
            return null;
        }
        int startIndex = 0;
        int curIndex = 0;
        int i = 0;
        StringBuffer result = new StringBuffer();
        String specialString = null;
        while(true) {
        	curIndex = source.indexOf(keyStr, startIndex);
        	if(curIndex>=0) {
        		if(i < toStr.length) {
        			specialString = toStr[i++];
        		} else {
        			specialString = " ";
        		}
        		result.append(source.substring(startIndex, curIndex)).append(specialString);
        		startIndex = curIndex + keyStr.length();
        	}
        	else {
        		break;
        	}
        }
        if(startIndex <= source.length()) {
            result.append(source.substring(startIndex, source.length()));
        }
        return result.toString();
    }

    public static String replaceStr(String source, String keyStr, String toStr) {
    	
        if(source == null) {
            return null;
        }else if( "".equals(source) ){
        	return "";
        }
        
        int startIndex = 0;
        int curIndex = 0;
        StringBuffer result = new StringBuffer();
        while(true) {
        	curIndex = source.indexOf(keyStr, startIndex);
        	if(curIndex>=0) {
        		result.append(source.substring(startIndex, curIndex)).append(toStr);
        		startIndex = curIndex + keyStr.length();
        	}
        	else {
        		break;
        	}
        }
        if(startIndex <= source.length()) {
            result.append(source.substring(startIndex, source.length()));
        }
        return result.toString();
    }

    public static String fmtStr(String str, String format) {
        if(str == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        char f[] = format.toCharArray();
        char s[] = str.toCharArray();
        int len = f.length;
        int h = 0;
        for(int i = 0; i < len && h < s.length; i++) {
            if(f[i] == '#') {
                buf.append(s[h++]);
            } else {
                buf.append(f[i]);

            }
        }
        return buf.toString();
    }

    public static String cutblank(String str) {
        int st = 0;
        char val[] = str.toCharArray();
        int count = val.length;
        int len;
        for(len = count; st < len && (val[st] <= ' ' || val[st] == '\u3000'); st++) {
            ;
        }
        for(; st < len && (val[len - 1] <= ' ' || val[len - 1] == '\u3000'); len--) {
            ;
        }
        return st <= 0 && len >= count ? str : str.substring(st, len);
    }

    public static String translateNewline(String source, String specialString) {
        String result = "";
        for(StringTokenizer st = new StringTokenizer(source, "\n"); st.hasMoreTokens(); ) {
//            result = String.valueOf(result) + String.valueOf(String.valueOf(st.nextToken()) +
//                     String.valueOf(specialString));
            result = result + st.nextToken() + specialString;
        }
        return result;
    }

    /**
     * 일정한 길이만큼 좌측으로 문자를 채운다
     * @param src String
     * @param putChar char
     * @param cnt int
     * @return String
     */
    public static String lpad(String src, char putChar, int cnt){
        String valueStr = "";
        if(src != null && src.length() < cnt){
            int srcCnt = src.length();
            int forCnt = cnt - srcCnt;
            String putString = "";
            StringBuffer sbuf = new StringBuffer();

            for(int i=0 ; i<forCnt ; i++){
//                putString += String.valueOf(putChar);
            	sbuf.append(putChar);
            }
            
            putString = sbuf.toString();
            valueStr = putString + src;
        } else {
            valueStr = src;
        }

        return valueStr;
    }

    /**
     * 일정한 길이만큼 우측으로 문자를 채운다
     * @param src String
     * @param putChar char
     * @param cnt int
     * @return String
     */
    public static String rpad(String src, char putChar, int cnt){
        String valueStr = "";
        if(src != null && src.length() < cnt){
            int srcCnt = src.length();
            int forCnt = cnt - srcCnt;
            String putString = "";
            StringBuffer sbuf = new StringBuffer();

            for(int i=0 ; i<forCnt ; i++){
                sbuf.append(putChar);
            }

            putString = sbuf.toString();
            valueStr = src + putString;
        } else {
            valueStr = src;
        }

        return valueStr;
    }

    /**
     * null, "0" String을 "" String으로 변환
     *
     * @param text String
     * @return String
     */
    public static String getZeroToEmpty(String text)
    {
        String rtn = "";

        if (text == null)
            return rtn;
        else if ( text.toLowerCase().equals("null") || text.toLowerCase().equals("0"))
            return rtn;
        else
            return text;
    }
}
