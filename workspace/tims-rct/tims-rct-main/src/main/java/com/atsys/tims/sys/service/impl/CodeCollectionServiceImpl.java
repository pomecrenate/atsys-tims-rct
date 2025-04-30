/*************************************************************
  프로그램명 : CdCollectionServiceImpl.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.CodeSelectionVo;
import com.atsys.search.CodeSelectionSo;
import com.atsys.tims.sys.dao.CodeCollectionDao;
import com.atsys.tims.sys.service.CodeCollectionService;

@Service("codeCollectionService")
//@Transactional(readOnly=true)
public class CodeCollectionServiceImpl implements CodeCollectionService {

	@Autowired
	private CodeCollectionDao codeCollectionDao;

	@Override
	public Map<String, List<CodeSelectionVo>> selectCodeList(CodeSelectionSo so) throws Exception {
		Map<String, List<CodeSelectionVo>> map = new HashMap<String, List<CodeSelectionVo>>();

		List<CodeSelectionVo> codeList = codeCollectionDao.selectList(so);
		//사용여부 'N'일 경우 해당 코드명 뒤에 "[미사용]이 붙게됨"
		for (CodeSelectionVo codeSelVo : codeList) {
			codeSelVo.setCdNm(codeSelVo.getCdNm().replace("[x]", "[ x ]") );
			if( "Y".equals(codeSelVo.getDelYn()) ){
				codeSelVo.setCdNm(codeSelVo.getCdNm() + "[x]");
			}
		}
		String key = null;
		for (CodeSelectionVo codeSelectionVo : codeList) {
			key = codeSelectionVo.getId();
			if(map.containsKey(key)) {
				map.get(key).add(codeSelectionVo);
			}
			else {
				List<CodeSelectionVo> list = new ArrayList<CodeSelectionVo>();
				list.add(codeSelectionVo);
				map.put(key, list);
			}
		}
		return map;
	}

	

	@Override
	public Map<String, String> selectList(CodeSelectionSo so) throws Exception {
		Map<String, String> map = null;
		List<CodeSelectionVo> codeList = codeCollectionDao.selectList(so);
		//사용여부 'N'일 경우 해당 코드명 뒤에 "[미사용]이 붙게됨"
		for (CodeSelectionVo codeSelVo : codeList) {
			codeSelVo.setCdNm(codeSelVo.getCdNm().replaceAll("[x]", "[ x ]") );
			if( "N".equals(codeSelVo.getDelYn()) ){
				codeSelVo.setCdNm(codeSelVo.getCdNm() + "[x]");
			}
		}
		map = makeComboItem(codeList);

		return map;
	}

	/**
	 * DB에서 조회된 내용을 ibsheet combo에 들어갈 문자열로 변경한다.
	 */
	private Map<String, String> makeComboItem(List<CodeSelectionVo> li){
		Map<String, String> map = new HashMap<String, String>();
		String code = "";
//		String text = "";
//		String cdNm = "";
    	for(int j = 0; j < li.size(); j++) {
    		String comboText = "", comboCd = "", comboCdAndText = "";
    		CodeSelectionVo cvo  = li.get(j);

    		if(!code.equals(cvo.getId())) {
    			code = cvo.getId();
//    			text = cvo.getId() + "Nm";
//    			cdNm = cvo.getId() + "CdNm";

    			for(int i = 0; i < li.size(); i++){
    	    		CodeSelectionVo item = li.get(i);
    	    		if(code.equals(item.getId())) {
    	    			if("".equals(comboText) || comboText == null) {
    	    				comboText = item.getCdNm();
    	    				comboCdAndText = item.getSmallCd() + " " + item.getCdNm();
    	    			} else {
    	    				comboText += "|" + item.getCdNm();
    	    				comboCdAndText += "|" + item.getSmallCd() + " " + item.getCdNm();
    	    			}

    	    			if("".equals(comboCd) || comboCd == null) {
    	    				comboCd = item.getSmallCd();
    	    			} else {
    	    				comboCd += "|" + item.getSmallCd();
    	    			}
    	    		}
    	    	}

    			map.put(code+"_S", comboCd);
    			map.put(code+"Nm_S", comboText);
    			map.put(code+"CdNm_S", comboCdAndText);
			}
    	}

    	return map;
	}
}
