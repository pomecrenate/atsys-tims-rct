/*************************************************************
  프로그램명 : Utilities.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수
*************************************************************/
package com.atsys.base.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.atsys.base.ConstantsUtils;
import com.atsys.model.CodeSelectionVo;
import com.atsys.model.TimsFileVo;
import com.atsys.model.TimsFileVo.FileStatus;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;
import com.atsys.search.CodeSelection;
import com.atsys.search.CodeSelectionSo;
import com.atsys.search.ListSo;
import com.atsys.tims.sys.service.CodeCollectionService;
import com.atsys.tims.sys.service.UniqIdService;
//import com.atsys.tims.sys.service.MenuService;
//import com.atsys.tims.sys.service.SrchHstService;
//import com.atsys.tims.sys.service.UniqIdService;
import com.atsys.tims.sys.web.UrlUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; 
@Component
public class Utilities {
	private static final String ENC_LANG = "UTF-8";
	private static ConfigurableApplicationContext context;
	private static ObjectMapper objectMapper;
	private static EgovPropertyService propertiesService;
	private static Logger LOGGER = Logger.getLogger(Utilities.class);

	public static final List<String> GOOGLE_API_GEOCODE_URLS = new ArrayList<>();
	public static int googleApiGeocodeUrlsIndex = 0;
	private static final int MAX_RECURSION_DEPTH = 10; // 최대 재귀 호출 깊이 설정
	
	@Autowired
	private ConfigurableApplicationContext ctx;

	@Resource(name = "propertiesService")
	protected EgovPropertyService properties;
	
	private static Utilities utilities;
	
	private static Integer mode64 = 2; // 1: 미적용, 2: base64적용

	@PostConstruct
	public void init() {
		utilities = this;
		/**
		 * https://console.developers.google.com/
		 */
		// GOOGLE_API_GEOCODE_URLS.add("https://maps.googleapis.com/maps/api/geocode/json?address=");

		context = this.ctx;
		propertiesService = this.properties;
		objectMapper = (ObjectMapper) context.getBean("jacksonObjectMapper");
	}

	public static String getArrayString(String[] arr) {
		return getArrayString(arr, ",");
	}

	public static String getArrayString(String[] arr, String strSpliter) {
		if(arr == null || arr.length == 0) {
			return "";
		}
		String strRet = "";
		for(String str : arr) {
			if(strRet.length() > 0) {
				strRet += strSpliter;
			}
			strRet += str;
		}
		return strRet;
	}

	public static Element appendElement(String strTagName, String strText, Document xDoc, Element xElement) {
		try {
			Element xChild = xDoc.createElement(strTagName);
			xChild.setTextContent(Utilities.nullCheck(strText));
			xElement.appendChild(xChild);
			return xChild;
		} catch(DOMException ex) {
			LOGGER.error("info to DOMException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static void clearDirectory(String strDirectory) {
		clearDirectory(new File(strDirectory));
	}

	public static void clearDirectory(File fDirectory) {
		if(fDirectory == null) {
			return;
		}
		if(!fDirectory.isDirectory()) {
			return;
		}
		File[] listFile = fDirectory.listFiles();
		int nLength = listFile.length;

		for(int i = 0; i < nLength; i++) {
			File fSub = listFile[i];
			if(fSub.isFile()) {
				fSub.delete();
			} else if(fSub.isDirectory()) {
				deleteFile(fSub);
			}
		}
	}

	public static void deleteDirectory(File fDirectory) {
		if(fDirectory == null || !fDirectory.isDirectory()) {
			return;
		}
		deleteFile(fDirectory);
	}

	public static void deleteDirectory(String strDirectory) {
		deleteDirectory(new File(strDirectory));
	}

	public static void deleteFile(File fDelete) {
		if(fDelete == null) {
			return;
		}
		clearDirectory(fDelete);
		fDelete.delete();
	}

	public static boolean createDirectory(String strDirectory) {
		File dInfo = new File(strDirectory);
		if(!dInfo.exists()) {
			dInfo.mkdirs();
		}

        // 수정 : 권한 설정
		dInfo.setExecutable(true, false);
		dInfo.setReadable(true);
		dInfo.setWritable(true, false);
		return dInfo.exists();
	}

	public static String getByteString(byte[] bt) {
		if(bt == null) {
			return "";
		}
		return new String(bt);
	}

	public static String getXMLText(String strText) {
		String strText2 = strText;
		strText2 = nullCheck(strText2);
		strText2 = strText2.replace("\"", "&quot;");
		strText2 = strText2.replace("\'", "&apos;");
		strText2 = strText2.replace("<", "&lt;");
		strText2 = strText2.replace(">", "&gt;");
		return strText2;
	}

	public static String getDateString() {
		return getDateString("-");
	}

	public static String getDateString(Date dt) {
		return getDateString(dt, "-");
	}

	public static String getDateString(String strSpliter) {
		return getDateString(new Date(), strSpliter);
	}

	public static String getDateString(Date dt, String strSpliter) {
		String strFormat = "yyyy" + strSpliter + "MM" + strSpliter + "dd";
		SimpleDateFormat sf = new SimpleDateFormat(strFormat, Locale.KOREA);
		return sf.format(dt);
	}

	public static String getDateFormat(String strDate) {
		return Utilities.getDateFormat(strDate, "-");
	}

	public static String getDateFormat(String strDate, String spliter) {
		String strDate2 = strDate;
		if(Utilities.isEmpty(strDate2)) {
			return null;
		}
		strDate2 = Utilities.getOnlyNumberString(strDate2);
		if(strDate2.length() < 8) {
			return strDate2;
		}
		return strDate2.substring(0, 4) + spliter + strDate2.substring(4, 6) + spliter + strDate2.substring(6, 8);
	}

	public static String getTimeFormat(String strDate) {
		return getTimeFormat(strDate, ":");
	}

	public static String getTimeFormat(String strDate, String spliter) {
		String strDate2 = strDate;
		if(Utilities.isEmpty(strDate2)) {
			return null;
		}
		strDate2 = Utilities.getOnlyNumberString(strDate2);
		if(strDate2.length() < 6) {
			return strDate2;
		}
		return strDate2.substring(0, 2) + spliter + strDate2.substring(2, 4) + spliter + strDate2.substring(4, 6);
	}

	public static String getDateTimeFormat(String strDateTime) {
		return getDateTimeFormat(strDateTime, "-", ":");
	}

	public static String getDateTimeFormat(String strDateTime, String spliterD, String spliterT) {
		String strDateTime2 = strDateTime;
		if(Utilities.isEmpty(strDateTime2)) {
			return null;
		}
		strDateTime2 = Utilities.getOnlyNumberString(strDateTime2);

		if(strDateTime2.length() < 14) {
			return strDateTime2;
		}
		return getDateFormat(strDateTime2, spliterD) + " " + getTimeFormat(strDateTime2.substring(8));
	}

	public static Date parseDate(String strDate) {
		if(strDate == null || strDate.length() == 0) {
			return null;
		}
		return parseDate(strDate, "-", ":", " ");
	}

	public static Date parseDate(String strDate, String spliter) {
		if(strDate == null || strDate.length() == 0) {
			return null;
		}
		return parseDate(strDate, spliter, spliter, spliter);
	}

	public static Date parseDate(String strDate, String spliterD, String spliterT, String separator) {
		if(strDate == null || strDate.length() == 0) {
			return null;
		}
		String strFormat = "yyyy" + spliterD + "MM" + spliterD + "dd" + separator + "HH" + spliterT + "mm" + spliterT + "ss";
		return parseDate(strDate, new SimpleDateFormat(strFormat, Locale.KOREA));
	}

	public static Date parseDate(String strDate, SimpleDateFormat format) {
		try {
			return format.parse(strDate);
		} catch(ParseException ex) {
			LOGGER.error("info to ParseException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String getSizeString(long nSize) {
		int k = 1000;
		int m = k * k;
		int g = m * k;
		if(nSize < k) {
			return nSize + "";
		} else if(nSize < m) {
			return parseInt(nSize / k + "") + "." + parseLong((nSize % k) * 100 / k + "") + " k";
		} else if(nSize < g) {
			return parseInt(nSize / m + "") + "." + parseLong((nSize % m) * 100 / m + "") + " m";
		} else {
			return parseInt(nSize / g + "") + "." + parseLong((nSize % g) * 100 / g + "") + " m";
		}
	}

	public static String getFileSizString(Object nSize) {
		return FileUtils.byteCountToDisplaySize(parseLong(nSize));
	}

	public static String getFilePath(String fullPath) {
		String fullPath2 = fullPath;
		if(fullPath2 == null) {
			return null;
		}
		fullPath2 = fullPath2.replace('\\', '/');
		int index = fullPath2.lastIndexOf("/");
		if(index == -1) {
			index = fullPath2.lastIndexOf("\\");
			if(index > -1) {
				return fullPath2.substring(0, index);
			}
		}

		return fullPath2.substring(0, index);
	}

	public static String getFileName(String filepath) {
		if(filepath == null) {
			return null;
		}
		int index = filepath.lastIndexOf("/");
		if(index > -1) {
			return filepath.substring(index + 1);
		}
		index = filepath.lastIndexOf("\\");
		if(index > -1) {
			return filepath.substring(index + 1);
		}

		return filepath;
	}

	public static String getFileNameWithoutExtension(String filepath) {
		String filename = getFileName(filepath);
		if(filename == null || filename.length() == 0) {
			return "";
		}
		int pos = filename.lastIndexOf(".");
		if(pos <= 0) {
			return filename;
		}
		return filename.substring(0, pos);
	}

	public static String trimStart(String strSource, String strTrim) {
		String strSource2 = strSource;
		if(strTrim == null || strTrim.length() == 0) {
			return strSource2;
		}
		while(strSource2.startsWith(strTrim)) {
			strSource2 = strSource2.substring(strTrim.length());
		}
		return strSource2;
	}

	public static String trimEnd(String strSource, String strTrim) {
		String strSource2 = strSource;
		if(strTrim == null || strTrim.length() == 0) {
			return strSource2;
		}
		if(strSource2 == null || strSource2.length() == 0) {
			return "";
		}
		while(strSource2.endsWith(strTrim)) {
			int nIndex = strSource2.length() - strTrim.length();
			strSource2 = strSource2.substring(0, nIndex);
		}
		return strSource2;
	}

	public static String padLeft(Object source, int nSize, char szPad) {
		String strSource = source == null ? "" : source.toString();

		int nCnt = nSize - strSource.length();
		if(nCnt < 1) {
			return strSource;
		}
		for(int i = 0; i < nCnt; i++) {
			strSource = szPad + strSource;
		}
		return strSource;
	}

	public static String padRight(Object source, int nSize, char szPad) {
		String strSource = source == null ? "" : source.toString();

		int nCnt = nSize - strSource.length();
		if(nCnt < 1) {
			return strSource;
		}
		for(int i = 0; i < nCnt; i++) {
			strSource = strSource + szPad;
		}
		return strSource;
	}

	public static String getNumberString(long nNumber, int nSize) {
		return getNumberString(nNumber, nSize, '0');
	}

	public static String getNumberString(long nNumber, int nSize, char chPad) {
		String strNumber = nNumber + "";
		return padLeft(strNumber, nSize, chPad);
	}

	public static String getScriptText(String strValue) {
		if(strValue == null) {
			return "";
		}
		return strValue.replace("\"", "\\\"").replace("\r\n", "\n").replace("\n", "\\n");
	}

	public static String getSQLText(String strValue) {
		if(strValue == null) {
			return "";
		}
		return strValue.replace("'", "''");
	}

	public static byte[] getStringByte(String strText) throws Exception {
		if(strText == null || strText.length() == 0) {
			return new byte[] {};
		}
		return strText.getBytes(ENC_LANG);
	}

	public static String getText(Element xElement) {
		if(xElement == null) {
			return "";
		}
		return nullCheck(xElement.getTextContent());
	}

	public static String getTimeString(Object time) {
		return getTimeString(parseLong(time));
	}

	public static String getTimeString(long time) {
		long totalSec = time / 1000;
		long hour = totalSec / 3600;
		long min = (totalSec % 3600) / 60;
		long sec = (totalSec % 3600) % 60;
		return Utilities.padLeft(hour, 2, '0') + ":" + Utilities.padLeft(min, 2, '0') + ":" + Utilities.padLeft(sec, 2, '0');
	}

	public static String getDateTimeString(Date dt) {
		if(dt == null) {
			return null;
		}
		return getDateString(dt, ".") + " " + getTimeString(dt, ":");
	}

	public static String getDateTimeString() {
		return getDateString() + " " + getTimeString();
	}

	public static String getTimeString() {
		return getTimeString(new Date(), ":");
	}

	public static String getTimeString(String strSpliter) {
		return getTimeString(new Date(), strSpliter);
	}

	public static String getTimeString(Date dt, String strSpliter) {
		try {
			String strFormat = "HH" + strSpliter + "mm" + strSpliter + "ss";
			SimpleDateFormat sf = new SimpleDateFormat(strFormat, Locale.KOREA);
			return sf.format(dt);
		} catch(RuntimeException ex) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		return "";
	}

	public static String getPKCd() {
		return getPKCd(Long.toHexString(System.currentTimeMillis()).toUpperCase());
	}

	public static String getPKCd(String prefix) {
		int length = prefix == null ? 20 : 20 - prefix.length();
		if(length < 0) {
			return prefix.substring(0, 20);
		}
		return Utilities.nullCheck(prefix) + Utilities.getUniqId(length);
	}

	public static String getUniqId(int nLength) {
		if(nLength < 1) {
			return "";
		}
		Random rd = new SecureRandom();
		final char chAdded = 'A';
		final char chRange = (char) 26;
		char[] chValue = new char[nLength];
		for(int i = 0; i < nLength; i++) {
			chValue[i] = (char) (rd.nextDouble() * chRange + chAdded);
		}
		return new String(chValue).toUpperCase();
	}

	public static String getUniqNumberID(int nLength) {
		if(nLength < 1) {
			return "";
		}
		Random rd = new SecureRandom();

		final char chAdded = '0';
		final char chRange = (char) 9;
		char[] chValue = new char[nLength];
		for(int i = 0; i < nLength; i++) {
			chValue[i] = (char) (rd.nextDouble() * chRange + chAdded + (i == 0 ? 1 : 0));
		}
		return new String(chValue);
	}

	public static String getUUID() {
		return String.valueOf(UUID.randomUUID());
	}

	public static Document getXmlDocument(String strTag) {
		try {
			String strXML = getXMLHeader() + "<" + strTag + "/>";
			Element xElement = loadXMLString(strXML);
			if(xElement == null) {
				return null;
			}
			return xElement.getOwnerDocument();
		} catch(DOMException ex) {
			LOGGER.error("info to DOMException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String getXMLHeader() {
		return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
	}

	public static boolean isWritableDirectory(String strPath) {
		File dir = new File(strPath);
		return dir.isDirectory() && dir.canWrite();
	}

	public static Element loadXMLURL(String strURL) {
		try {
			javax.xml.parsers.DocumentBuilderFactory bFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			bFactory.setNamespaceAware(true);
			javax.xml.parsers.DocumentBuilder builder = null;
			builder = bFactory.newDocumentBuilder();
			Document dom = builder.parse(strURL);
			if(dom == null) {
				return null;
			}

			Element domElement = dom.getDocumentElement();
			return domElement;
		} catch(DOMException ex) {
			LOGGER.error("info to DOMException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static Element loadXML(String strXmlFile) {
		try {
			javax.xml.parsers.DocumentBuilderFactory bFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			bFactory.setNamespaceAware(true);
			javax.xml.parsers.DocumentBuilder builder = null;
			builder = bFactory.newDocumentBuilder();
			File fXML = new File(strXmlFile);
			if(!fXML.isFile()) {
				return null;
			}
			Document dom = builder.parse(fXML);
			if(dom == null) {
				return null;
			}

			Element domElement = dom.getDocumentElement();
			return domElement;
		} catch(DOMException ex) {
			LOGGER.error("info to DOMException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static Element loadXMLString(String strXML) {
		try {
			javax.xml.parsers.DocumentBuilderFactory bFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			bFactory.setNamespaceAware(true);
			javax.xml.parsers.DocumentBuilder builder = null;
			builder = bFactory.newDocumentBuilder();
			ByteArrayInputStream ds = new ByteArrayInputStream(strXML.getBytes());
			Document dom = builder.parse(ds);
			if(dom == null) {
				Utilities.trace("Fail to load xml dom.");
				return null;
			}
			Element domElement = dom.getDocumentElement();
			return domElement;
		} catch(DOMException ex) {
			LOGGER.error("info to DOMException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String nullCheck(String strValue) {
		return ((strValue == null) ? "" : strValue);
	}

	public static String nullCheck(Object obj) {
		return ((obj == null) ? "" : obj.toString());
	}

	public static boolean parseBoolean(String strValue) {
		try {
			if(Utilities.isEmpty(strValue)) {
				return false;
			}
			return Boolean.parseBoolean(strValue);
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
		return false;
	}

	public static boolean parseBoolean(Object obj) {
		try {
			if(Utilities.isEmpty(obj)) {
				return false;
			}
			return Utilities.parseBoolean(obj.toString());
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
	}

	public static double parseDouble(String strValue) {
		try {
			return Double.parseDouble(strValue);
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0;
		}
	}

	public static double parseDouble(Object obj) {
		try {
			if(obj == null) {
				return 0.0d;
			}
			return Utilities.parseDouble(obj.toString());
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0;
		}
	}

	public static float parseFloat(String strValue) {
		try {
			return Float.parseFloat(strValue);
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0f;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0f;
		}
	}

	public static float parseFloat(Object obj) {
		try {
			if(obj == null) {
				return 0.0f;
			}
			return Utilities.parseFloat(obj.toString());
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0f;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0.0f;
		}
	}

	public static int parseInt(Object obj) {
		try {
			if(obj == null) {
				return 0;
			}
			return (int) Double.parseDouble(obj.toString());
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
//			e.printStackTrace();
			LOGGER.error("info to Exception : 841" );
			return 0;
		}
	}

	public static long parseLong(String strValue) {
		try {
			if(isEmpty(strValue)) {
				return 0;
			}
			return strValue == null ? 0 : Long.parseLong(strValue);
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0;
		}
	}

	public static long parseLong(Object obj) {
		try {
			if(obj == null) {
				return 0l;
			}
			return Utilities.parseLong(obj.toString());
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return 0;
		}
	}

	public static boolean saveXML(String strFileName, Document xDoc) {
		try {
			return true;
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
	}

	public static void trace(Exception ex) {

	}

	public static void trace(Object obj) {
		trace(obj.toString(), 0);
	}

	public static void trace(String strLog, int recursionDepth) {
		
		if (recursionDepth >= MAX_RECURSION_DEPTH) {
			System.out.println("Reached maximum recursion depth. Aborting.");
			return;
		}
		
		System.out.println(strLog);
		
		// 재귀 호출
		trace(strLog, recursionDepth + 1);
	}

	public static String getHtmlText(String strText) {
		if(Utilities.isEmpty(strText)) {
			return strText;
		}
		return strText.replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;");
	}

	public static String getDecodeHtmlText(String strText) {
		if(Utilities.isEmpty(strText)) {
			return strText;
		}
		// 순서 변경하면 안됨. ibsheet에서 엑셀 다운 시 사용
		return strText.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">");
	}

	public static int ceiling(double dbNumber) {
		int nNumber = (int) dbNumber;
		if(nNumber < dbNumber) {
			nNumber++;
		}
		return nNumber;
	}

	public static String getEncodeText(String strText) {
		if(strText == null || strText.length() == 0) {
			return "";
		}
		String strRet = "";
		try {
			strRet = URLEncoder.encode(strText, ENC_LANG);
		} catch(UnsupportedEncodingException e) {
			LOGGER.error("info to UnsupportedEncodingException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		}
		return strRet;
	}

	public static String getDecodeText(String strText) {
		String strText2 = strText;
		if(strText2 == null || strText2.length() == 0) {
			return "";
		}
		String strRet = "";
		try {
			strText2 = strText2.replace("_amp_", "&");
			strText2 = strText2.replace("_dpy_", "%");
			strRet = URLDecoder.decode(strText2, ENC_LANG);
		} catch(UnsupportedEncodingException e) {
			LOGGER.error("info to UnsupportedEncodingException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		}
		return strRet;
	}

	public static String getDecodeText(Element iText) {
		String strText = getText(iText);
		if(strText == null || strText.length() == 0) {
			return "";
		}
		String strRet = "";
		try {
			strText = strText.replace("_amp_", "&");
			strText = strText.replace("_dpy_", "%");
			strRet = URLDecoder.decode(strText, ENC_LANG);
		} catch(UnsupportedEncodingException e) {
			LOGGER.error("info to UnsupportedEncodingException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			strRet = "";
		}
		return strRet;
	}

	public static Date getDate(int nYear, int nMonth, int nDay) {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.HOUR_OF_DAY, 0);
		cd.set(Calendar.MINUTE, 0);
		cd.set(Calendar.SECOND, 0);
		cd.set(Calendar.MILLISECOND, 0);
		cd.set(Calendar.YEAR, nYear);
		cd.set(Calendar.MONTH, nMonth);
		cd.set(Calendar.DATE, nDay);
		return cd.getTime();
	}

	public static Calendar getCalendar() {
		Calendar cd = Calendar.getInstance();
		return cd;
	}

	public static int getYear() {
		return getCalendar().get(Calendar.YEAR);
	}

	public static int getMonth() {
		return getCalendar().get(Calendar.MONTH) + 1;
	}

	public static int getDay() {
		return getCalendar().get(Calendar.DATE);
	}

	public static Calendar getCalendar(int nYear, int nMonth, int nDay) {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.YEAR, nYear);
		cd.set(Calendar.MONTH, nMonth + 1);
		cd.set(Calendar.DATE, nYear);
		return cd;
	}

	public static String nullValue(String value) {
		return value == null ? "" : value;
	}

	public static String getFileExtn(String filename) {
		if(filename == null) {
			return null;
		}
		int index = filename.lastIndexOf(".");
		if(index > -1) {
			return filename.substring(index + 1);
		}
		return "";
	}

	public static String getFullHref(String strBasePath, String strHref) {
		String strBasePath2 = strBasePath;
		if(strBasePath2 == null) {
			strBasePath2 = "";
		}
		String strHref2 = strHref;
		if(strHref2 == null) {
			strHref2 = "";
		}
		strBasePath2 = strBasePath2.replace('\\', '/');
		if(!strBasePath2.endsWith("/")) {
			strBasePath2 += "/";
		}
		if(strHref2.indexOf("./") < 0) {
			return strBasePath2 + strHref2;
		}
		if(strHref2.indexOf("./") == 0) {
			return strBasePath2 + strHref2.substring(2);
		}
		strBasePath2 = strBasePath2.substring(0, strBasePath2.length() - 1);
		while(strHref2.indexOf("../") != 0) {
			int nIndex = strBasePath2.lastIndexOf("/");
			if(nIndex < 0) {
				return "";
			}
			strBasePath2 = strBasePath2.substring(0, nIndex);
			strHref2 = strHref2.substring(3);
		}
		return strBasePath2 + "/" + strHref2;

	}

	public static List<Map<String, Object>> convert2CamelCase(List<Map<String, Object>> list) {
		List<Map<String, Object>> ret = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			ret.add(convert2CamelCase(list.get(i)));
		}
		return ret;
	}

	public static Map<String, Object> convert2CamelCase(Map<String, Object> map) {
		Map<String, Object> ret = new HashMap<>();
		for(String key : map.keySet()) {
			ret.put(convert2CamelCase(key), map.get(key));
		}
		return ret;
	}

	public static String convert2CamelCase(String underScore) {
		if(underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
			return underScore;
		}
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		int len = underScore.length();

		for(int i = 0; i < len; i++) {
			char currentChar = underScore.charAt(i);
			if(currentChar == '_') {
				nextUpper = true;
			} else {
				if(nextUpper) {
					result.append(Character.toUpperCase(currentChar));
					nextUpper = false;
				} else {
					result.append(Character.toLowerCase(currentChar));
				}
			}
		}
		return result.toString();
	}

	public static boolean moveFile(String fileName, String targetPath, String targetName) {
		Path file = Paths.get(fileName);
		Path movePath = Paths.get(targetPath);
		try {
			createDirectory(targetPath);
			Files.move(file, movePath.resolve(targetName));
		} catch(IOException e) {
			LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
		return true;
	}
	
	public static String getServerIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch(UnknownHostException  e) {
			LOGGER.error("info to UnknownHostException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String getPeerIp() {
		HttpServletRequest request = getRequest();
		if(request != null) {
//			return request.getRemoteAddr().replaceAll("0:0:0:0:0:0:0:1", "127.0.0.1");
			
			String ip = request.getHeader("X-Forwarded-For");
			
			if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
				ip = request.getHeader("Proxy-Client-IP");
			}
			if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
				ip = request.getRemoteAddr() ;
			}
			
			return ip.replaceAll("0:0:0:0:0:0:0:1", "127.0.0.1");
			
		} else {
			return null;
		}
	}

	public static HttpSession getSession() {
		if(getRequest() != null) {
			return getRequest().getSession();
		}
		return null;
	}

	public static String getSessionId() {
		String sessionId = getSession() == null ? null : (String) getSession().getAttribute("timsSessionId");
		if(Utilities.isEmpty(sessionId)) {
			sessionId = Utilities.getUniqId(20);
			setSessionId(sessionId);
		}
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		if(getSession() != null) {
			getSession().setAttribute("timsSessionId", sessionId);
		}
	}

	public static HttpServletResponse getResponse() {
		HttpServletRequest request = getRequest();
		if(request == null) {
			return null;
		}
		return (HttpServletResponse) request.getAttribute("httpServletResponse");
	}

	@SuppressWarnings("rawtypes")
	public static <T> T beanToBean(Object obj, Class cls) {
		return mapToBean(beanToMap(obj), cls);
	}

	public static boolean beanToBean(Object target, Object src) {
		try {
			Map<String, Object> map = Utilities.beanToMap(src);
			Utilities.mapToBean(map, target);
			return true;
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T mapToBean(Map<String, Object> map, Class cls) {
		return (T) objectMapper.convertValue(map, cls);

	}
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>>  convertMapList(List list) {
		if(list == null) {
			return null;
		}
		List<Map<String, Object>> arr =  new ArrayList<>();
		for(Map<String, Object> map : arr) {
			arr.add(beanToMap(map));
		}
		return arr;
	}

	@SuppressWarnings({ "unchecked"})
	public static Map<String, Object> beanToMap(Object obj) {
		if(obj == null) {
			return null;
		}
		return objectMapper.convertValue(obj, EgovMap.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean beanToMap(Map target, Object obj) {
		if(target == null) {
			return false;
		}
		EgovMap map = objectMapper.convertValue(obj, EgovMap.class);
		target.putAll(map);
		return true;
	}

	public static boolean mapToBean(Map<String, Object> map, Object bean) {
		if(bean == null || map == null) {
			return false;
		}
		if(map == null || bean == null) {
			return false;
		}
		try {
			BeanUtils.populate(bean, map);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}

	public static List<Map<String, Object>> beanToMap(List<?> list) {
		List<Map<String, Object>> l = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			l.add(beanToMap(list.get(i)));
		}
		return l;
	}

	public static double round(double number, int num) {
		double pow = Math.pow(10, num);
		double tmp = number * pow;
		double value = Math.round(tmp);
		return value / pow;
	}

	public static double round(float number, int num) {
		double pow = Math.pow(10, num);
		double tmp = number * pow;
		long value = Math.round(tmp);
		return value / pow;
	}

	public static String roundString(double number, int num) {
		double value = Utilities.round(number, num);
		return Utilities.getNumberString(value, num);
	}

	public static String roundString(float number, int num) {
		double value = Utilities.round(number, num);
		return Utilities.getNumberString(value, num);
	}

	public static String getMoneyString(long nNumber) {
		return getNumberString(nNumber);
	}

	public static String getNumberString(long nNumber) {
		long nNumber2 = nNumber;
		String strMoney = "";
		while(nNumber2 > 0) {
			if(strMoney.length() > 0) {
				strMoney = "," + strMoney;
			}
			strMoney = getNumberString(nNumber2 % 1000, 3) + strMoney;
			nNumber2 /= 1000;
			if(nNumber2 < 1) {
				break;
			}
		}
		strMoney = trimStart(strMoney, "0");
		if(strMoney.length() == 0) {
			strMoney = "0";
		}
		return strMoney;
	}

	public static String getNumberString(double number, int num) {
		long nVal = (long) number;
		String n = getNumberString(nVal);
		if(num < 1) {
			return n;
		}
		String format = String.format("%" + num + "f", number);
		int nIndex = format.indexOf(".");

		String dbVal = "";
		if(nIndex > -1) {
			dbVal = format.substring(nIndex + 1);
		}

		String zero = "";
		for(int i = 0; i < num; i++) {
			zero += "0";
		}
		String db = dbVal + zero;
		db = db.substring(0, num);
		return n + "." + db;
	}

	public static boolean downloadFile(HttpServletResponse response, String strFileName, String strDisplayName, long start, long end) {
		return downloadFile(response, strFileName, strDisplayName, start, end, null);
	}

	public static boolean downloadFile(HttpServletResponse response, String strFileName, String strDisplayName, long start, long end, String contentsType) {
		String contentsType2 = contentsType;
		if(response == null) {
			return false;
		}
		File fDownload = new File(strFileName);
		if(!fDownload.isFile()) {
			return false;
		}
		int nLength = (int) (end - start + 1);
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
		if(Utilities.isEmpty(contentsType2)) {
			contentsType2 = "application/octet-stream";
		}
		try {
			response.setContentType(contentsType2);
//			if(contentsType2.indexOf("application/pdf") > -1) { 
				// contentsType2이 application/pdf를 포함하는 경우에는 다운로드 하지 않고 바로 보여줌.
//			} else {
			if(contentsType2.indexOf("application/pdf") <= -1) { 
				//response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, ENC_LANG) + "\";");	
				response.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode(strDisplayName, ENC_LANG) + "\";");	
			}
			response.setContentLength(nLength);
			return downloadStream(response.getOutputStream(), fDownload, start, end);
		} catch(UnsupportedEncodingException e) {
			LOGGER.error("info to UnsupportedEncodingException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
	}

	public static boolean downloadFile(HttpServletResponse response, String strFileName, String strDisplayName) {
		return downloadFile(response, strFileName, strDisplayName, null);
	}

	public static boolean downloadFile(HttpServletResponse response, String strFileName, String strDisplayName, String contentsType) {
		String contentsType2 = contentsType;
		if(response == null) {
			return false;
		}
		File fDownload = new File(strFileName);
		LOGGER.debug("file  Path : " + strFileName);
		if(!fDownload.isFile()) {
			return false;
		}
		int nLength = (int) fDownload.length();
		if(Utilities.isEmpty(contentsType2)) {
			contentsType2 = "application/octet-stream";
		}
		try {
			response.setContentType(contentsType2);
//			if(contentsType2.indexOf("application/pdf") > -1) { 
				// contentsType2이 application/pdf를 포함하는 경우에는 다운로드 하지 않고 바로 보여줌.
//			} else {
			if(contentsType2.indexOf("application/pdf") <= -1) { 
				response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, ENC_LANG) + "\";");	
			}
			response.setContentLength(nLength);
			return downloadStream(response.getOutputStream(), fDownload);
		} catch(UnsupportedEncodingException ex) {
			try {
				response.sendError(404);
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			return false;
		} catch(Exception ex) {
			try {
				response.sendError(404);
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			return false;
		}
	}

	public static boolean downloadText(HttpServletResponse response, String text, String strDisplayName) {
		if(response == null) {
			return false;
		}
		int nLength = text.length();

		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(strDisplayName, ENC_LANG) + "\";");
			response.setContentLength(nLength);
			return downloadStream(response.getOutputStream(), text);
		} catch(UnsupportedEncodingException ex) {
			try {
				response.sendError(404);
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			return false;
		} catch(Exception ex) {
			try {
				response.sendError(404);
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			return false;
		}
	}

	public static boolean downloadStream(OutputStream out, String text) {
		if(out == null || text == null) {
			return false;
		}

		BufferedOutputStream outs = null;
		try {
			outs = new BufferedOutputStream(out);
			try {
				outs.write(text.getBytes(ENC_LANG), 0, text.length());
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return false;
			}
			return true;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} finally {
			try {
				if(outs != null) {
					outs.close();
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
		}
	}

	public static boolean downloadStream(OutputStream out, File fDownload) throws Exception {
		if(out == null || fDownload == null) {
			return false;
		}
		if(!fDownload.isFile()) {
			return false;
		}

		return downloadStream(out, Files.newInputStream(fDownload.toPath()));
	}
	public static boolean downloadStream(OutputStream out, File fDownload, long start, long end) throws Exception {
		if(out == null || fDownload == null) {
			return false;
		}
		if(!fDownload.isFile()) {
			return false;
		}
		
		return downloadStream(out, Files.newInputStream(fDownload.toPath()), start, end);
	}

	public static boolean downloadStream(OutputStream out, InputStream is, long start, long end) {
		if(out == null || is == null) {
			return false;
		}
		long size = end - start + 1;
		long left = size;
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		try {
			fin = new BufferedInputStream(is);
			if(start > 0) {
				fin.skip(start);
			}
			outs = new BufferedOutputStream(out);
			byte[] buffer = new byte[4096];
			try {
				int nRead = fin.read(buffer);
				while(nRead != -1 && left > 0) {
					int write = (int) (left > nRead ? nRead : left);
					outs.write(buffer, 0, write);
					left -= nRead;
					nRead = fin.read(buffer);
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return false;
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return false;
			}
			return true;
		} catch(IOException e) {
			LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} finally {
			try {
				if(fin != null) {
					fin.close();
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			try {
				if(outs != null) {
					fin.close();
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
		}
	}

	public static boolean downloadStream(OutputStream out, InputStream is) {
		if(out == null || is == null) {
			return false;
		}
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		try {
			fin = new BufferedInputStream(is);
			outs = new BufferedOutputStream(out);
			byte[] buffer = new byte[4096];
			try {
				int nRead = fin.read(buffer);
				while(nRead != -1) {
					outs.write(buffer, 0, nRead);
					nRead = fin.read(buffer);
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return false;
			} catch(Exception e) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return false;
			}
			return true;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} finally {
			try {
				if(fin != null) {
					fin.close();
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
			try {
				if(outs != null) {
					outs.close();
				}
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			}
		}
	}

	public static String encodBase64(byte[] value) {
		return Base64.getEncoder().encodeToString(value);
	}

	public static String encodBase64(String value) throws Exception {
		return encodBase64(value.getBytes());
	}

	public static String decodBase64(String value) {
		return new String(decodBase64Byte(value));
	}

	public static byte[] decodBase64Byte(String value) {
		return Base64.getDecoder().decode(value);
	}

	public static boolean isNotEmpty(Object obj) {
		return !Utilities.isEmpty(obj);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if(obj == null) {
			return true;
		} else if((obj instanceof String)) {
			return ((String) obj).isEmpty();
		} else if(obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		} else if(obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		} else if(obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		} else if(obj instanceof File) {
			return !((File) obj).exists();
		} else if(obj instanceof List) {
			return ((List) obj).size() == 0;
		} else if(obj instanceof Object) {
			return obj == null;
		}
		try {
			Method method = obj.getClass().getMethod("isEmpty", (Class<?>[]) null);
			return (Boolean) method.invoke(obj);
		} catch(IllegalArgumentException e) {
			LOGGER.error("info to IllegalArgumentException: " + e);
			return false;
		} catch(Exception ex) {
			LOGGER.error("info to Exception: " + ex);
			return false;
		}
	}

	public static StackTraceElement getSourceInfo() {
		try {
			return Thread.currentThread().getStackTrace()[2];
		} catch(NullPointerException e) {
			LOGGER.error("info to NullPointerException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static HttpServletRequest getRequest() {
		try {
			ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if(servletContainer != null) {
				return servletContainer.getRequest();
			} else {
				return null;
			}
		} catch(IllegalStateException e) {
			LOGGER.error("info to IllegalStateException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String getRequestBody() {
		return RequestUtils.getRequestBody();
	}

	public static Dimension getImageSize(String fileName) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(fileName);
			return new Dimension(image.getWidth(null), image.getHeight(null));
		} catch(IllegalArgumentException e) {
			LOGGER.error("info to IllegalArgumentException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static void downloadWorkbook(Workbook workbook) throws Exception {
		downloadWorkbook(workbook, getResponse(), null);
	}

	public static void downloadWorkbook(String fileName, Workbook workbook) throws Exception {
		downloadWorkbook(workbook, getResponse(), fileName);
	}

	public static void downloadWorkbook(Workbook workbook, HttpServletResponse response, String fileName) throws Exception {
		String fileName2 = fileName;
		if(workbook == null || response == null) {
			return;
		}
		if(Utilities.isEmpty(fileName2)) {
			fileName2 = Utilities.getDateString("") + Utilities.getTimeString("") + ".xlsx";
		}
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName2, "UTF-8") + "\";");
		workbook.write(response.getOutputStream());
		// workbook.close();
	}

	public static List<String> renameFilenames(List<String> list) {
		Map<String, String> map = new HashMap<>();
		List<String> sList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			if(map.containsKey(name)) {
				for(int j = 1; j < 100000; j++) {
					String name2 = name + "(" + j + ")";
					if(map.containsKey(name2)) {
						continue;
					} else {
						map.put(name2, name);
						sList.add(name2);
						break;
					}
				}
			} else {
				map.put(name, name);
				sList.add(name);
			}
		}
		return sList;
	}

	public static Cookie getCookie(String cookieName) {
		if(isEmpty(cookieName)) {
			return null;
		}
		Cookie[] cookies = getRequest().getCookies();
		for(int i = 0; cookies != null && i < cookies.length; i++) {
			if(cookieName.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}

	public static void setCookie(String name, String value) {
		String value2 = value;
		HttpServletResponse response = getResponse();
		value2 = value2 == null ? null : value2.replace("\n", "").replace("\r", "");
		Cookie cookie = new Cookie(name, value2);
		cookie.setSecure(true);
		cookie.setPath("/");

		response.addCookie(cookie);
	}

	public static String getMessage() {
		return getMessage(getRequest().getParameter("msgTicket"));
	}

	public static String getMessage(String msgTicket) {
		try {
			if(isEmpty(msgTicket)) {
				return null;
			}
			String msg = (String) getSession().getAttribute(msgTicket);
			return msg;
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static void sendMessage(String msgTicket, String message) {
		try {
			if(isEmpty(msgTicket)) {
				return;
			}
			if(isEmpty(message)) {
				getSession().removeAttribute(msgTicket);
			} else {
				if("99999999".equals(message)) {
					if(isEmpty(getMessage())) {
						return;
					}
				}
				getSession().setAttribute(msgTicket, message);
			}
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
	}

	public static void sendMessage(String message) {
		sendMessage(getRequest().getParameter("msgTicket"), message);
	}

	public static void removeMessage() {
		removeMessage(getRequest().getParameter("msgTicket"));
	}

	public static void removeMessage(String msgTicket) {
		try {
			if(isEmpty(msgTicket)) {
				return;
			}
			getSession().removeAttribute(msgTicket);
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
	}

	public static String getSystemId() {
		return UrlUtil.getSystemId().toUpperCase();
	}

	public static String getProgramId() {
		try {
			return UrlUtil.getProgramId().toUpperCase();
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static String getSystemLangauge() {
		String reqSysLang = "";
		HttpServletRequest request = getRequest();
		if(request != null) {
			reqSysLang = request.getParameter("sysLang") != null ? request.getParameter("sysLang") : "";
		} else {
			reqSysLang = ConstantsUtils.getDefaultLangauge();
		}
		
		if(StringUtils.isNotBlank(reqSysLang)){
			return reqSysLang;
		} else {
			String sSysLang = (String)getSession().getAttribute("sysLang");
			if(StringUtils.isNotBlank(sSysLang)){
				return sSysLang;
			} else {
				Locale locale = getRequest().getLocale();
				return locale.getLanguage();
//				return ConstantsUtils.getDefaultLangauge();
			}
		}
	}

	public static void makeSo(Map<String, Object> map) {
		ListSo page = (ListSo) Utilities.mapToBean(map, ListSo.class);

		if(page.getCurrentPageNo() == 0) {
			page.setCurrentPageNo(1);
		}
		if(page.getRecordCountPerPage() == 0) {
			page.setRecordCountPerPage(10);
		}
		if(page.getPageSize() == 0) {
			page.setPageSize(5);
		}
		if(page instanceof ListSo) {
			ListSo so = page;
			int cuurentPage = Utilities.parseInt(so.getCurrentPageNo());
			int onepagerow = Utilities.parseInt(so.getOnepagerow());
			if(cuurentPage <= 0) {
				cuurentPage = 1;
			}
			if(onepagerow <= 0) {
				onepagerow = 10;
			}
			so.setCurrentPage(cuurentPage + "");
			so.setOnepagerow(onepagerow + "");
		}
		if(map != null) {
			map.putAll(Utilities.beanToMap(page));
			map.put(ConstantsUtils.getPagenationInfo(), page);
		}
	}

	public static String encryptPassword(String pwd, String id) {
		return getSHA512(pwd);// PasswordCrypt.encrypt(pwd, id);
	}

	public static String encryptPassword256(String pwd, String id) {
		return getSHA256(pwd);// PasswordCrypt.encrypt(pwd, id);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls) {
		try {
			return (T) context.getBean(cls);
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		try {
			return (T) context.getBean(name);
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}

	public static List<TimsFileVo> saveTempFiles(MultipartRequest mrequest) {
		String tempDir = getRootFilePath() + getTempFilePath(); // /edata/temp

		createDirectory(tempDir);
		Iterator<String> names = mrequest.getFileNames();
		List<TimsFileVo> list = new ArrayList<>();
		while(names.hasNext()) {
			try {
				String name = names.next();
				list.add(saveTempFile(mrequest.getFile(name)));
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return null;
			} catch(Exception ex) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return null;
			}
		}
		return list;
	}

	public static File saveTempFile(MultipartRequest mrequest) {
		String tempDir = getRootFilePath() + getTempFilePath(); // /edata/temp

		createDirectory(tempDir);
		Iterator<String> names = mrequest.getFileNames();
		while(names.hasNext()) {
			try {
				String name = names.next();
				MultipartFile file = mrequest.getFile(name);
				String fileExtnsNm = getFileExtn(file.getOriginalFilename());
				File temp = new File(tempDir + "/" + getUniqId(10) + "." + fileExtnsNm);
				file.transferTo(temp);
				return temp;
			} catch(IOException e) {
				LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			} catch(Exception ex) {
				LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
				return null;
			}
		}
		return null;
	}

	public static TimsFileVo saveTempFile(MultipartFile file) throws Exception {
		String tempDir = getRootFilePath() + getTempFilePath(); // /edata/temp
		createDirectory(tempDir);

		String fileExtnsNm = getFileExtn(file.getOriginalFilename());

		File temp = new File(tempDir + "/" + getUniqId(10) + "." + fileExtnsNm);
		file.transferTo(temp);

		if(!temp.isFile()) {
			throw new SecurityException("there is no file");
		}
		
		HttpServletRequest request = getRequest();
		String fileKey = request.getParameter(ConstantsUtils.getFileKeyParam());
		int fileSeq = parseInt(request.getParameter(ConstantsUtils.getFileSeqParam()));

		TimsFileVo vo = new TimsFileVo();
		vo.setSaveFileNm(temp.getName());
		vo.setOrigFileNm(getFileName(file.getOriginalFilename()));
		vo.setCntsType(file.getContentType());
		vo.setFileStats(FileStatus.ADDED.getCode());
		vo.setFileExtn(getFileExtn(file.getOriginalFilename()));
		vo.setFileSiz(file.getSize());
		vo.setFileKey(fileKey);
		vo.setFileSeq(fileSeq);

		return vo;
	}

	public static boolean moveFromFile(String fileNm, String filePath) {
		return moveFromFile(fileNm, filePath, fileNm);
	}

	/**
	 * temp폴더의 파일을 운영폴더로 이동
	 * @param tempFileNm
	 * @param realPath
	 * @param targetName
	 * @return
	 */
	public static boolean moveFromFile(String tempFileNm, String realPath, String targetName) {
		String tempFile = getRootFilePath() + getTempFilePath(); // /edata/temp
		tempFile += "/" + tempFileNm;
		String target = getRootFilePath() + realPath + "/" + targetName;
//		createDirectory(realPath);
		File src = new File(tempFile);
		if(!src.isFile()) {
			return false;
		}
		File targetFile = new File(target);
		if(targetFile.isFile()) {
			targetFile.delete();
		}
		if(targetFile.isDirectory()) {
			deleteDirectory(target);
		}
		return moveFile(src.getAbsolutePath(), getFilePath(targetFile.getAbsolutePath()), targetName);
	}
	
	public static boolean copyFromFile(String fromPath, String toPath, String targetName) {
		// 대상 파일
		String fromFile = getRootFilePath() + fromPath;
		File src = new File(fromFile);
		if(!src.isFile()) {
			return false;
		}
		
		String target = getRootFilePath() + toPath + "/" + targetName;
		File targetFile = new File(target);
		if(targetFile.isFile()) {
			targetFile.delete();
		}

		Path file = Paths.get(src.getAbsolutePath());
		Path copyPath = Paths.get(getFilePath(targetFile.getAbsolutePath()));
		
		try {
			Files.copy(file, copyPath.resolve(targetName));
		} catch(IOException e) {
			LOGGER.error("info to IOException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
		return true;
	}
	
	/**
	 * 파일 복사
	 * @param fromPath
	 * @param toPath
	 * @param fromFileName
	 * @param toFileName
	 * @return
	 */
	public static boolean copyFromFile(String fromPath, String toPath, String fromFileName, String toFileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		FileChannel in = null;
		FileChannel out = null;
		try {
			File fromFile = new File(fromPath + "\\" + fromFileName);
			if(fromFile.isFile()) {
				fis = new FileInputStream(fromPath + "\\" + fromFileName);
				createDirectory(toPath);
				fos = new FileOutputStream(toPath + "\\" + toFileName);
				
				in = fis.getChannel();
				out = fos.getChannel();
				
				in.transferTo(0, in.size(), out);
			}
		}
		catch(FileNotFoundException e) {
//			e.printStackTrace();
			LOGGER.error("info to Exception : 2173" );
		} catch (IOException e) {
//			e.printStackTrace();
			LOGGER.error("info to Exception : 2175" );
		}
		finally {
			 // 마지막에 FileInputStream과 FileOutputStream을 닫아준다.
			if(fis != null) {
				try {
					fis.close();
				} catch(IOException e) {
//					e.printStackTrace();
					LOGGER.error("info to Exception : 2185" );
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch(IOException e){
//					e.printStackTrace();
					LOGGER.error("info to Exception : 2193" );
				}
			}
			// 마지막에 FileChannel을 닫아준다.
			if(in != null) {
				try {
					in.close();
				} catch(IOException e) {
//					e.printStackTrace();
					LOGGER.error("info to Exception : 2202" );
				}
			}
			if(out != null) {
				try {
					out.close();
				} catch(IOException e){
//					e.printStackTrace();
					LOGGER.error("info to Exception : 2210" );
				}
			}
		}
		return true;
	}
	
	public static EgovPropertyService getEgovPropertyService() {
		return propertiesService;
	}
	
	public static String getProperty(String property) {
		return getProperty(property, "");
	}
	
	public static String getProperty(String property, String defaultValue) {
		return propertiesService.getString(property, defaultValue);
	}
	
	public static String getStorageRootPath() {
		return getProperty("attach.storage.rootPath");
	}
	
	public static String getRootFilePath() {
		return getProperty("attach.filePath.root");
	}
	
	public static String getTempFilePath() {
		return getProperty("attach.filePath.temp");
	}
	
	public static String getUploadPath() {
		return getProperty("attach.filePath.upload");
	}
	
	public static String getBbsFilePath() {
		return getProperty("attach.filePath.bbs");
	}
	
	public static String getTxtbkFilePath() {
		return getProperty("attach.filePath.txtbk");
	}
	
	public static String getTaskFilePath() {
		return getProperty("attach.filePath.task");
	}

	public static String getExcelFilePath() {
		return getProperty("attach.filePath.excel");
	}
	
	public static String getPopupFilePath() {
		return getProperty("attach.filePath.popup");
	}
	
	public static String getNoteFilePath() {
		return getProperty("attach.filePath.note");
	}
	
	public static String getExamFilePath() {
		return getProperty("attach.filePath.exam");
	}
	
	public static String getQuizFilePath() {
		return getProperty("attach.filePath.quiz");
	}
	
	public static String getCntsPath() {
		return getProperty("attach.filePath.cnts");
	}
	
	public static String getMediaPath() {
		return getProperty("attach.filePath.mcnts");
	}
	
	public static String getStreamingUrl() {
		return getProperty("media.streaming.url");
	}
	
	public static String getGoogleMapsAPIKey() {
		return getProperty("google.maps.api-key");
	}
	
	public static String getKakaoSnsShareAPIKey() {
		return getProperty("kakao.sns.share.api-key");
	}
	
	public static String getBatchServerUrl() {
		return getProperty("batch.server-url");
	}

	public static String getAttachWebPath() {
		return getProperty("attach.web.path");
	}
	
	public static String getCloudFrontUrl() {
		HttpServletRequest request = getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getAttachWebPath();
	}
	
	public static String getServerUrl() {
		HttpServletRequest request = getRequest();
		if(isEmpty(request)) {
			return getProperty("server.url");
		} else {
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();			
		}
	}

	public static String getVimeoToken() {
		return getProperty("vimeo.accessToken");
	}
	
	public static String getVimeoEduFolderId() {
		return getProperty("vimeo.edu.folder.id");
	}
	
	public static String getVimeoEtcFolderId() {
		return getProperty("vimeo.etc.folder.id");
	}
	
	public static String getVimeoAcsDomain() {
		return getProperty("vimeo.acs.domain");
	}
	
	public static String getVimeoAppId() {
		return getProperty("vimeo.app.id");
	}
	
	public static String getVimeoPlayerUrl() {
		return getProperty("vimeo.player.url");
	}
	
	public static String getJusoConfmKey() {
		return getProperty("juso.confmKey");
	}
	
	public static String getJusoDomain() {
		return getProperty("juso.domain");
	}
	
	public static List<String> getFiles(String path, List<String> list) {
		File fPath = new File(path);
		if(!fPath.isDirectory()) {
			return null;
		}
		File[] fList = fPath.listFiles();
		for(int i = 0; i < fList.length; i++) {
			File file = fList[i];
			if(file.isFile()) {
				list.add(file.getPath());
			}
			if(file.isDirectory()) {
				getFiles(file.getPath(), list);
			}
		}
		return list;
	}
	
	public static List<String> getFiles(String path) {
		return getFiles(path, new ArrayList<String>());
	} 

	// @SuppressWarnings("unchecked")
	public static double distance(String org, String target) {
		EgovMap mapOrg = addr2GeoCodi(org);
		if(mapOrg == null) {
			return 0;
		}
		EgovMap mapTarget = addr2GeoCodi(target);
		if(mapTarget == null) {
			return 0;
		}
		return distance((double) mapOrg.get("lat"), (double) mapOrg.get("lng"), (double) mapTarget.get("lat"), (double) mapTarget.get("lng"));
	}
	
	public static EgovMap addr2GeoCodi(String address) {
		if(isEmpty(address)) {
			return null;
		}
		try {
			EgovMap mapRet = new EgovMap();
			JsonNode node = null;
			int i = 0;
			for(i = 0; i < GOOGLE_API_GEOCODE_URLS.size(); i++) {
				String url = GOOGLE_API_GEOCODE_URLS.get(googleApiGeocodeUrlsIndex) + URLEncoder.encode(address, "UTF-8");
				googleApiGeocodeUrlsIndex++;
				googleApiGeocodeUrlsIndex = googleApiGeocodeUrlsIndex % GOOGLE_API_GEOCODE_URLS.size();
				URL source = new URL(url);
				node = objectMapper.readTree(source);
				if(!"OVER_QUERY_LIMIT".equals(node.get("status").asText())) {
					break;
				}

			}
			if(i >= GOOGLE_API_GEOCODE_URLS.size()) {
				mapRet.put("status", "OVER_QUERY_LIMIT");
				return mapRet;
			}
			if(!node.has("status") || !"OK".equals(node.get("status").asText()))
				return addr2GeoCodi2(address);
			if(!node.has("results")) {
				return addr2GeoCodi2(address);
			}
			node = node.get("results");
			if(node == null) {
				return addr2GeoCodi2(address);
			}
			node = node.get(0);
			JsonNode mapGeo = node.get("geometry");
			JsonNode mapLoc = mapGeo.get("location");
			
			mapRet.put("address", address);
			mapRet.put("formattedAddress", node.get("formatted_address").asText());
			mapRet.put("placeCd", node.get("place_id").asText());
			mapRet.put("lat", mapLoc.get("lat").asDouble());
			mapRet.put("lng", mapLoc.get("lng").asDouble());
			return mapRet;
		} catch(UnsupportedEncodingException e) {
			LOGGER.error("info to UnsupportedEncodingException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return null;
		}
	}
	
	private static EgovMap addr2GeoCodi2(String address) {
		String address2 = address;
		if(isEmpty(address2)) {
			return null;
		}
		address2 = address2.trim();
		int n = address2.lastIndexOf(' ');
		if(n < 0) {
			return null;
		}
		if(n >= address2.length()) {
			return null;
		}
		String addr = address2.substring(0, n).trim();
		EgovMap ret = addr2GeoCodi(addr);
		return ret;
	}
	
	public static double distance(double latOrg, double lonOrg, double latTarget, double lonTarget) {
		double theta = lonOrg - lonTarget;
		double dist = Math.sin(deg2rad(latOrg)) * Math.sin(deg2rad(latTarget)) + Math.cos(deg2rad(latOrg)) * Math.cos(deg2rad(latTarget)) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344;
		return dist;
	}
	
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	public static boolean isAdminContext() {
		return ConstantsUtils.getAdmContenxt().equals(getSystemId()) || ConstantsUtils.getTtrContenxt().equals(getSystemId());
	}
	
	public static boolean isTeacherContext() {
		return ConstantsUtils.getTtrContenxt().equals(getSystemId());
	}
	
	public static List<CodeSelectionVo> getCommonCodes(String cls, String cdg, String use, String cond, String smd, String inc, String exc) throws Exception {
		CodeCollectionService service = getBean("codeCollectionService");
		
		CodeSelectionSo so = new CodeSelectionSo();
		so.setCodeColls(new ArrayList<CodeSelection>());
		/* CodeSelection 셋팅 시작 */
		CodeSelection selection = new CodeSelection();
		// 코드구분
		selection.setCls(cls);
		// largeDiv
		selection.setCdg(cdg);
		// 사용여부
		selection.setUse(use);
		// 특정컬럼(특정컬럼선택 ex : COND_CD1)
		selection.setCond(cond);
		// 특정조건값(특정컬럼의 값)
		selection.setSmd(smd);
		// 해당 코드 포합 구분자 '|'
		List<String> incList = new ArrayList<>();
		for(String incStr : StringUtils.split(inc, "|")) {
			incList.add(incStr);
		}
		selection.setInc(incList);
		// 해당 코드 제외 구분자 '|'
		List<String> excList = new ArrayList<>();
		for(String excStr : StringUtils.split(exc, "|")) {
			excList.add(excStr);
		}
		selection.setExc(excList);
		/* CodeSelection 셋팅 끝 */
		
		so.getCodeColls().add(selection);
		
		Map<String, List<CodeSelectionVo>> mapList = service.selectCodeList(so);
		Iterator<Entry<String, List<CodeSelectionVo>>> i = mapList.entrySet().iterator();
		
		while(i.hasNext()) {
			Entry<String, List<CodeSelectionVo>> entry = i.next();
			//20210714
			if(isNotEmpty(entry)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public static List<CodeSelectionVo> getCommonCodes2(String cls, String cdg, String use, String cond, String smd, String inc, String exc) throws Exception {
		CodeCollectionService service = getBean("codeCollectionService");
		
		CodeSelectionSo so = new CodeSelectionSo();
		so.setCodeColls(new ArrayList<CodeSelection>());
		/* CodeSelection 셋팅 시작 */
		CodeSelection selection = new CodeSelection();
		// 코드구분
		selection.setCls(cls);
		// largeDiv
		selection.setCdg(cdg);
		// 사용여부
		selection.setUse(use);
		// 특정컬럼(특정컬럼선택 ex : COND_CD1)
		selection.setCond(cond);
		// 특정조건값(특정컬럼의 값)
		selection.setSmd(smd);
		// 해당 코드 포합 구분자 '|'
		List<String> incList = new ArrayList<>();
		for(String incStr : StringUtils.split(inc, "|")) {
			incList.add(incStr);
		}
		selection.setInc(incList);
		// 해당 코드 제외 구분자 '|'
		List<String> excList = new ArrayList<>();
		for(String excStr : StringUtils.split(exc, "|")) {
			excList.add(excStr);
		}
		selection.setExc(excList);
		/* CodeSelection 셋팅 끝 */
		
		so.getCodeColls().add(selection);
		
		Map<String, List<CodeSelectionVo>> mapList = service.selectCodeList(so);
		Iterator<Entry<String, List<CodeSelectionVo>>> i = mapList.entrySet().iterator();
		
		while(i.hasNext()) {
			Entry<String, List<CodeSelectionVo>> entry = i.next();
			//20210714
			if(isNotEmpty(entry)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public static boolean isMobile() {
		try {
			HttpServletRequest request = getRequest();
			String userAgent = request.getHeader("user-agent");
			if(isEmpty(userAgent)) {
				return false;
			}
			boolean mobile1 = userAgent.matches(".*(iPad|iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
			boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
			if(mobile1 || mobile2) {
				return true;
			}
			return false;
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		} catch(Exception ex) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			return false;
		}
	}
	
	public static String decryptRSA(String securedValue) throws Exception {
		PrivateKey privateKey = (PrivateKey) getSession().getAttribute("privateKey");
		Cipher cipher = Cipher.getInstance(ConstantsUtils.getRsaInstance());
		byte[] encryptedBytes = hexToByteArray(securedValue);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
		return decryptedValue;
	}
	
	public static byte[] hexToByteArray(String hex) {
		if(hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}
		
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}
	
	public static String makeParamText(String contents, Map<String, String> map) {
		String contents2 = contents;
		if(map == null) {
			return contents2;
		}
		if(isEmpty(contents2)) {
			return contents2;
		}
		for(Map.Entry<String, String> elem : map.entrySet()) {
			String name = "#{" + elem.getKey() + "}";
			String value = nullCheck(elem.getValue());
			contents2 = contents2.replace(name, value);
		}
		return contents2;
	}
	
	public static String getOnlyNumberString(Object str) {
		if(str == null) {
			return null;
		}
		if(isEmpty(str)) {
			return str.toString();
		}
		return str.toString().replaceAll("[^0-9]", "");
	}
	
	public static String getPhoneString(Object str) {
		String no = getOnlyNumberString(str);
		if(isEmpty(no)) {
			return no;
		}
		if(no.length() > 11) {
			no = no.substring(0, 11);
		}
		return no;
	}
	
	public static String getJsonString(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}
	
	public static String getJsonPString(Object obj, String callback) throws Exception {
		if(isEmpty(callback)) {
			return objectMapper.writeValueAsString(obj);
		} else {
			return callback + "(" + objectMapper.writeValueAsString(obj) + ")";
		}
	}
	
	// SSO 암호화
	public static String encodeBySType(String strData) {
		String strRet = "";
//		strRet = Encrypt.com_Encode(":" + strData + ":sisenc");
		return strRet;
	}
	
	// 복호화
	public static String decodeBySType(String strData) {
		String strRet = "";
		int e = 0, d = 0;// , s=0, i=0;
		
//		strRet = Encrypt.com_Decode(strData);
		
		e = strRet.indexOf(":");
		d = strRet.indexOf(":sisenc");
		if(e > -1 && d > -1) {
			strRet = strRet.substring(e + 1, d);
		}
		return strRet;
	}
	
	public static void createRSASession() {
		try {
			HttpSession session = getSession();
			if(session.getAttribute("privateKey") != null) {
				return;
			}
			
			KeyPairGenerator generator = KeyPairGenerator.getInstance(ConstantsUtils.getRsaInstance());
			generator.initialize(2048);
			
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance(ConstantsUtils.getRsaInstance());
			
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			session.setAttribute("privateKey", privateKey);
			session.setAttribute("publicKeyModulus", publicKeyModulus);
			session.setAttribute("publicKeyExponent", publicKeyExponent);
		} catch(RuntimeException e) {
			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		} catch(Exception e) {
			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		}
	}
	
	public static void removeRSASession() {
		HttpSession session = getSession();
		if(session.getAttribute("privateKey") == null) {
			return;
		}
		
		session.removeAttribute("privateKey");
		session.removeAttribute("publicKeyModulus");
		session.removeAttribute("publicKeyExponent");
	}
	
	public static String getMediaPlayUrl(String playUrl) {
		String returnUrl = playUrl;
		if(isEmpty(returnUrl)) {
			return null;
		}
		int idx = returnUrl.indexOf("://");
		if(idx > 0 && idx < 10) {
			return returnUrl;
		}
		if(returnUrl.startsWith("/")) {
			returnUrl = returnUrl.substring(1);
		}
		return getMediaPath() + returnUrl;
	}
	
	/*
	 * public static void downloadIbExcel() throws Exception { HttpServletRequest
	 * request = getRequest(); HttpServletResponse response = getResponse();
	 * Down2Excel ibExcel = new Down2Excel(); ibExcel.setService(request, response);
	 * ibExcel.setPageEncoding("utf-8"); String webRootPath = getTempFilePath() +
	 * "/"; ibExcel.setWebRoot(webRootPath); ibExcel.setTreeChar("\u2026");
	 * ibExcel.setDefaultFontName("맑은 고딕"); ibExcel.setDefaultFontSize((short) 11);
	 * ibExcel.setHeaderFontColor("#FFFFFF"); Workbook workbook = null;
	 * 
	 * try { response.reset(); String data = ibExcel.getData(); data =
	 * getDecodeHtmlText(data); ibExcel.setData(data); workbook =
	 * ibExcel.makeExcel(); ServletOutputStream out2 = response.getOutputStream();
	 * workbook.write(out2); out2.flush(); } catch(IOException e) {
	 * LOGGER.error("info to IOException : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } catch(Exception
	 * e) { LOGGER.error("info to Exception : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } finally {
	 * if(workbook != null) { try { workbook.close(); } catch(IOException e) {
	 * LOGGER.error("info to IOException : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } } } }
	 */
	
	public static Utilities getThis() {
		return utilities;
	}
	
	/*
	 * @SuppressWarnings("rawtypes") public static void downloadIbExcel(List ibData)
	 * throws Exception { Map<String, Object> map = new HashMap<String, Object>();
	 * downloadIbExcel(ibData, map); }
	 */
	
	/*
	 * @SuppressWarnings("rawtypes") public static void downloadIbExcel(List ibData,
	 * Map<String, Object> map) throws Exception { HttpServletRequest request =
	 * getRequest(); HttpServletResponse response = getResponse();
	 * request.setAttribute("SHEETDATA", ibData); DirectDown2Excel ibExcel = new
	 * DirectDown2Excel(); ibExcel.setService(request, response);
	 * ibExcel.setPageEncoding("utf-8"); String webRootPath = getRootFilePath() +
	 * getTempFilePath() + "/"; // /edata/temp ibExcel.setWebRoot(webRootPath);
	 * ibExcel.setTreeChar("\u2026"); ibExcel.setDefaultFontName("맑은 고딕");
	 * ibExcel.setDefaultFontSize((short) 11); Workbook workbook = null;
	 * 
	 * OutputStream outputStream = null; ByteArrayOutputStream bos = null; try {
	 * response.reset();
	 * 
	 * // 파라메터 정보를 얻음 String data = ibExcel.getData(); data =
	 * getDecodeHtmlText(data); // 파라메터 정보를 다시 설정함 (예, 암호화된 파라메터를 복호화 처리를 하여 다시 설정)
	 * ibExcel.setData(data); workbook = ibExcel.makeDirectExcel();
	 * 
	 * MenuService service = getBean(MenuService.class); MenuVo vo = new MenuVo();
	 * vo.setSysMenuCd(getProgramId()); // 시스템 메뉴 코드 MenuVo vo2 =
	 * service.selectPrsnSetn(vo);
	 * 
	 * boolean bExcel = false; if(isNotEmpty(vo2)) { // 개인정보조회설정
	 * if(isNotEmpty(vo2.getPrsnInfoSetn())) { if(vo2.getPrsnInfoSetn().indexOf("E")
	 * > -1) { bExcel = true; } } // 관리자조회설정 if(isNotEmpty(vo2.getAdminSrchSetn()))
	 * { if(vo2.getAdminSrchSetn().indexOf("E") > -1) { bExcel = true; } } }
	 * 
	 * if(bExcel) { // 다운로드 받을 파일 이름을 얻음 String fileName =
	 * ibExcel.getDownloadFileName(); String fileExtnsNm = getFileExtn(fileName);
	 * 
	 * // excel 폴더에 저장될 엑셀파일 정보를 TB_UPLD_FILE 테이블에 INSERT String filePath =
	 * getExcelFilePath(); // /excel String atchFileCd = getTbUniqId(); String
	 * saveFileNm = getUniqId(10) + "." + fileExtnsNm;
	 * 
	 * TbUpldFileVo tbUpldFileVo = new TbUpldFileVo();
	 * tbUpldFileVo.setUpldFileCd(atchFileCd); tbUpldFileVo.setFileNm(fileName);
	 * tbUpldFileVo.setSaveFileNm(saveFileNm); tbUpldFileVo.setSavePath(filePath +
	 * "/" + saveFileNm); tbUpldFileVo.setFileExtn(fileExtnsNm);
	 * 
	 * FileUploadService fileUploadService = getBean("fileUploadService");
	 * fileUploadService.saveUpldFile(tbUpldFileVo);
	 * 
	 * // 엑셀다운로드 이력 등록 regSearchHst("E", ibData.size(), atchFileCd, map);
	 * 
	 * createDirectory(getRootFilePath() + filePath); // 디렉토리 생성
	 * 
	 * File file = new File(getRootFilePath() + tbUpldFileVo.getSavePath());
	 * outputStream = new FileOutputStream(file);
	 * 
	 * bos = new ByteArrayOutputStream(); workbook.write(bos);
	 * 
	 * byte[] byteArray = bos.toByteArray();
	 * 
	 * for(byte b : byteArray) { outputStream.write(b); }
	 * 
	 * // S3 샘플 소스 // 지정된 경로에 파일을 생성함 //
	 * S3UploaderUtil.uploadToS3ExcelSearchHst(workbook, tbUpldFileVo); }
	 * 
	 * ServletOutputStream out2 = response.getOutputStream();
	 * workbook.write(response.getOutputStream()); out2.flush(); } catch(IOException
	 * e) { LOGGER.error("info to IOException : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } catch(Exception
	 * e) { LOGGER.error("info to Exception : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } finally {
	 * if(workbook != null) { try { workbook.close(); } catch(IOException e) {
	 * LOGGER.error("info to IOException : " +
	 * Thread.currentThread().getStackTrace()[1].getLineNumber()); } }
	 * if(outputStream != null) { outputStream.close(); } if(bos != null) {
	 * bos.close(); } } }
	 */
	
	public static String getPhoneNumberFormat(Object phoneNumber) {
		if(isEmpty(phoneNumber)) {
			return nullCheck(phoneNumber);
		}
		String numbers = getOnlyNumberString(phoneNumber);
		if(numbers.length() == 10) {
			return numbers.substring(0, 3) + "-" + numbers.substring(3, 6) + "-" + numbers.substring(6);
		} else if(numbers.length() >= 11) {
			return numbers.substring(0, 3) + "-" + numbers.substring(3, 7) + "-" + numbers.substring(7);
		}
		return nullCheck(phoneNumber);
	}
	
	public static String getSHA256(String input) {
		if(input == null) {
			return null;
		}
//		try {
////			return shaPasswordEncoder256.encodePassword(input, null);
//			// return DigestUtils.sha256Hex(input);
//		} catch(RuntimeException e) {
//			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
//		} catch(Exception e) {
//			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
//		}
		
		return null;
	}
	
	public static String getSHA512(String input) {
		if(input == null) {
			return null;
		}
//		try {
////			return shaPasswordEncoder512.encodePassword(input, null);
//		} catch(RuntimeException e) {
//			LOGGER.error("info to RuntimeException : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
//		} catch(Exception e) {
//			LOGGER.error("info to Exception : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
//		}
		return null;
	}
	
	public static String getUserViewPath() {
		return ConstantsUtils.getFrontViewRoot();
	}
	
	public static String getWebPathToRealPath(String webPath) {
		HttpServletRequest req = getRequest();
		if(req == null || req.getSession() == null || req.getSession().getServletContext() == null) {
			return null;
		}
		return req.getSession().getServletContext().getRealPath(webPath);
	}
	
	/**
	 * 비밀번호 검증 정규식
	 * @param so
	 * @return
	 */
	public static Map<String, Object> isPasswordCk(Map<String, Object> so) {
		Map<String, Object> result = new HashMap<>();
		
		String loginId = so.get("loginId").toString();
		String newPassword = so.get("loginPassword").toString();
		String newConPassword = so.get("loginPasswordConfirm").toString();
		 
		// 정규식 (영문(대소문자 구분안함), 숫자, 특수문자 조합, 9~20자리)
		String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[A-Za-z]).{9,20}$";
		// Pattern 클래스의 compile(), matcher() 함수를 활용하여 Matcher 클래스 생성
		Matcher matcher = Pattern.compile(pwPattern).matcher(newPassword);
		 
		// 정규식 (같은 문자 4개 이상 사용 불가)
		pwPattern = "(.)\\1\\1\\1";
		// Pattern 클래스의 compile(), matcher() 함수를 활용하여 Matcher 클래스 생성
		Matcher matcher2 = Pattern.compile(pwPattern).matcher(newPassword);
		 
		// Matcher 클래스의 matches() 함수를 활용하여 체크, true 일 경우 정규식을 만족함
		if(!matcher.matches()){
			result.put("message", "비밀번호는 영문, 숫자, 특수문자를 조합하여 9~20자리를 사용해야합니다.");
			result.put("error", true);
			return result;
		}
		 
		// Matcher 클래스의 find() 함수를 활용하여 체크, true 일 경우 정규식을 만족함
		if(matcher2.find()){
			result.put("message", "비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.");
			result.put("error", true);
			return result;
		}
		 
		// String 클래스의 contains() 함수를 활용하여 Id가 비밀번호 문자열에 있는지 체크함
		if(newPassword.contains(loginId)){
			result.put("message", "비밀번호에 로그인ID가 포함되어 있습니다.");
			result.put("error", true);
			return result;
		}
		
		// String 클래스의 contains() 함수를 활용하여 새비밀번호가 기존비밀번호 문자열에 있는지 체크함
		if(so.containsKey("password")) {
			String oldPassword = so.get("password").toString();
			if(newPassword.equals(oldPassword)){
				result.put("message", "비밀번호가 이전 비밀번호와 일치합니다.");
				result.put("error", true);
				return result;
			}
		}
		
		// String 클래스의 contains() 함수를 활용하여 공백문자가 비밀번호 문자열에 있는지 체크함
		if(newPassword.contains(" ")){
			result.put("message", "비밀번호에 공백문자가 포함되어 있습니다.");
			result.put("error", true);
			return result;
		}
		
		if(!newPassword.equals(newConPassword)){
			result.put("message", "비밀번호와 비밀번호 확인의 값이 동일하지 않습니다.");
			result.put("error", true);
			return result;
		}
		
		result.put("error", false);
		return result;
	}
	
	public static String[] parseStringToArray(String str){
		return parseStringToArray(str, "|");
	}
	
	public static String[] parseStringToArray(String str, String split){
		String[] stringArray = null;
		if(isNotEmpty(str)) {
			stringArray = str.split("\\" + split);
		}
		
		return stringArray;
	}
	
	public static void regSearchHst(String exeDiv, int hstCnt) throws Exception {
		Map<String, Object> map = new HashedMap<String, Object>();
		regSearchHst(exeDiv, hstCnt, "", map);
	}
	
	public static void regSearchHst(String exeDiv, int hstCnt, Map<String, Object> map) throws Exception {
		regSearchHst(exeDiv, hstCnt, "", map);
	}
	
	public static void regSearchHst(String exeDiv, int hstCnt, String upldFileCd, Map<String, Object> map) throws Exception {
		String upperExeDiv = exeDiv.toUpperCase(); // 실행구분(R-조회,I-등록,U-수정,D-삭제,E-다운로드)
		
//		// 실행구분이 조회이고 건수가 0 일때는 등록도지 않게 처리
//		if(upperExeDiv.equals("R") && hstCnt == 0) {
//			return;
//		}
//		
//		String sysCd = UrlUtil.getSystemId(); // 시스템 코드(SERVICE,FRONT)
//		String sysMenuCd = getProgramId(); // 시스템 메뉴 코드
//		String acsPgmUri = getRequest().getRequestURI(); // 실행 화면 URI
//		
//		MenuService service = getBean(MenuService.class);
//		MenuVo vo = new MenuVo();
//		vo.setSysMenuCd(sysMenuCd); // 시스템 메뉴 코드
//		MenuVo vo2 = service.selectPrsnSetn(vo);
//		
//		if(upperExeDiv.equals("R") && isNotEmpty(map.get("srchTrgtCd"))) {
//			if(map.get("srchTrgtCd").toString().equals("02")) { // 조회구분 - 회원정보 단건 상세 조회
//				vo2 = new MenuVo();
//				vo2.setPrsnInfoSetn((String)map.get("prsnInfoSetn"));
//				vo2.setSysMenuCd((String)map.get("sysMenuCd"));
//			}
//		}
//		
//		if(isEmpty(vo2)) {
//			return;
//		}
//		if(isEmpty(vo2.getPrsnInfoSetn()) && isEmpty(vo2.getAdminSrchSetn())) {
//			return;
//		}
//		
//		LoginUser usr = SessionLoginUtils.getLoginUser();
//		
//		TbSrchHstVo hstVo = new TbSrchHstVo();
//		hstVo.setSysMenuCd(sysMenuCd);
//		hstVo.setAcsPgmUri(acsPgmUri);
//		hstVo.setSysCd(sysCd);
//		hstVo.setExeDiv(upperExeDiv);
//		hstVo.setTrgtUsrCd(usr.getUsrCd());
//		hstVo.setHstCnt(hstCnt);
//		hstVo.setAtchFileCd(upldFileCd);
//		hstVo.setSrchTrgtCd("01");
//		if(upperExeDiv.equals("R") && isNotEmpty(map.get("srchTrgtCd"))) {
//			hstVo.setSrchTrgtCd((String)map.get("srchTrgtCd"));
//			hstVo.setSrchUsrCd((String)map.get("srchUsrCd"));
//			hstVo.setSysMenuCd(vo2.getSysMenuCd());				
//		}
//		
//		SrchHstService srchHstservice = getBean("srchHstService");
//		// 개인정보조회설정
//		if(isNotEmpty(vo2.getPrsnInfoSetn())) {
//			if(vo2.getPrsnInfoSetn().indexOf(upperExeDiv) > -1) {
////				String srchHstCd = getTbUniqId();
////				hstVo.setSrchHstCd(srchHstCd);
//				hstVo.setSrchSetnDiv("P");
//				hstVo.setDwldRsn((String)map.get("dwldRsn"));
//				srchHstservice.insert(hstVo);
//			}
//		}
//		// 관리자조회설정
//		if(isNotEmpty(vo2.getAdminSrchSetn())) {
//			if(vo2.getAdminSrchSetn().indexOf(upperExeDiv) > -1) {
////				String srchHstCd = getTbUniqId();
////				hstVo.setSrchHstCd(srchHstCd);
//				hstVo.setSrchSetnDiv("A");
//				hstVo.setDwldRsn((String)map.get("dwldRsn"));
//				srchHstservice.insert(hstVo);
//			}
//		}
		return;
	}
	
	/**
	 * 두 지점(위경도) 사이의 거리를 구함(미터)(google Distance Matrix API)
	 * @param TbGisuAtndVo
	 * @return long
	 */
//	public static long distanceMatrix(TbGisuAtndVo vo) {
//		// 두 지점 간 거리(M)
//		long distance = 0;
//		
//		String origins = vo.getFacCrdt();
//		String destinations = geoCode(vo.getRdnmAddr1() + vo.getRdnmAddr2());
//		
//		if(isNotEmpty(destinations)) {
//			// 해당 주소의 위경도 변환이 안되는 경우에는 -1로 리턴
//			if(destinations.equals("-1")) {
//				return -1;
//			}
//			
//			String apiKey = getGoogleMapsAPIKey();
//			
//			//units:metric => 미터법 / mode:transit => 대중교통 / region:KR  국가
//			String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&mode=transit&region=KR";
//			try {
//					RestTemplate restTemplate = new RestTemplate(); 
//					String fullUrl = url + "&origins=" + origins + "&destinations=" + destinations + "&key=" + apiKey;
//			        ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);
//			        
//					/*
//					 * OkHttpClient client = new OkHttpClient();
//					 * 
//					 * Request request = new
//					 * Request.Builder().url(url+"&origins="+origins+"&destinations="+destinations+
//					 * "&key="+apiKey).method("GET", null).build(); Response response =
//					 * client.newCall(request).execute();
//					 */
//					String jsonText = response.getBody().toString();
//					
//					JSONParser parser = new JSONParser();
//					Object obj  = parser.parse(jsonText);
//					JSONObject json = (JSONObject)obj;
//					
//					if(json.get("status").equals("OK")) {
//						JSONArray rowsArr = (JSONArray)json.get("rows");
//						JSONObject rowsJson = (JSONObject)rowsArr.get(0);
//						JSONArray elementsArr = (JSONArray)rowsJson.get("elements");
//						JSONObject elementsJson = (JSONObject)elementsArr.get(0);
//						
//						if(elementsJson.get("status").equals("OK")) {
//							JSONObject distanceJson = (JSONObject)elementsJson.get("distance");
//							
//							distance = (long)distanceJson.get("value");
//							
//							//미터로 return
//							return distance;
//						} else if(elementsJson.get("status").equals("ZERO_RESULTS")) {
//							return -1;
//						}
//					}
//			} catch (Exception e){
//				LOGGER.error(e.toString());
//			}
//		}
//		
//		return 0;
//	}
	
	/**
	 * 도로명 주소를 사용하여 해당 주소의 위경도 변환(google geoCode API)
	 * @param String
	 * @return String
	 */
	public static String geoCode(String address) {
		String apiKey = getGoogleMapsAPIKey();
		
		//region:KR  국가
		String url = "https://maps.googleapis.com/maps/api/geocode/json?region=KR";
		try {
			
			RestTemplate restTemplate = new RestTemplate(); 
			String fullUrl = url + "&address=" + address + "&key==" + apiKey + "&key=" + apiKey;
			ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);
				
			/*
			 * OkHttpClient client = new OkHttpClient();
			 * 
			 * Request request = new
			 * Request.Builder().url(url+"&address="+address+"&key="+apiKey).method("GET",
			 * null).build(); Response response = client.newCall(request).execute();
			 */
				String jsonText = response.getBody().toString();
				
				JSONParser parser = new JSONParser();
				Object obj  = parser.parse(jsonText);
				JSONObject json = (JSONObject)obj;
				
				if(json.get("status").equals("OK")) {
					JSONArray resultsArr = (JSONArray)json.get("results");
					JSONObject resultsJson = (JSONObject)resultsArr.get(0);
					JSONObject geometryJson = (JSONObject)resultsJson.get("geometry");
					JSONObject locationJson = (JSONObject)geometryJson.get("location");
					
					String lat = String.valueOf(locationJson.get("lat"));
					String lng = String.valueOf(locationJson.get("lng"));
					
					String crdt = lat+","+lng;
					return crdt;
				} else if(json.get("status").equals("ZERO_RESULTS")) {
					return "-1";
				}
		} catch (Exception e) {
			// 다른 예외가 발생한 경우의 예외 처리 코드를 작성합니다.
			LOGGER.error("An unexpected error occurred: " + e.getMessage(), e);
		}
		return null;
	}
	
	public static String getDecodedBase64(String obj) {
		String resultData = obj;
		if(mode64 == 2) {
			byte[] decodedBytes = Base64.getDecoder().decode(obj);
			String decodedString = new String(decodedBytes);
			resultData = decodedString;
		}
		
		return resultData;
	}
	
	public static String setEncodedBase64(String obj) {
		String resultData = obj;
		if(mode64 == 2) {
			byte[] testToByte = obj.getBytes();
			String encodedBytes = Base64.getEncoder().encodeToString(testToByte);
			String encodedString = new String(encodedBytes);
			resultData = encodedString;
		}
		
		return resultData;
	}
	
	public static String getTbUniqId() {
		try {
			UniqIdService service = getBean("uniqIdService");
			if(service != null) {
				EgovMap so = new EgovMap();
				EgovMap map = service.selectTbUniqId(so);
				return String.valueOf(map.get("uniqId"));
			}else {
				return "";
			}
		} catch (IOException e) {
			// IOException이 발생한 경우의 예외 처리 코드를 작성합니다.
			LOGGER.error("IOException occurred: " + e.getMessage(), e);
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("info to Exception : 3364" );
		}	
		return "";
	}
	
	public static String getPkCd(String seqDiv) throws Exception{
//		try {
//			UniqIdService service = getBean("uniqIdService");
//			Map<String, Object> so = new HashMap<String, Object>();
//			so.put("seqDiv", seqDiv);
//			service.selectPkCd(so);
//			return String.valueOf(so.get("seq"));
//		} catch (IOException e) {
//			// IOException이 발생한 경우의 예외 처리 코드를 작성합니다.
//			LOGGER.error("IOException occurred: " + e.getMessage(), e);
//		} catch(Exception e) {
////			e.printStackTrace();
//			LOGGER.error("info to Exception : 3393" );
//		}
		
		return "";
	}
}
