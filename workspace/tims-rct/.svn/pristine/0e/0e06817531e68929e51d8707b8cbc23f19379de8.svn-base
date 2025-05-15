/*************************************************************
 프로그램명 : Evl1102eServiceImpl.java
 설명 : 평가표관리
 작성자 : 이예찬
 일자 : 2025.05.13
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsys.base.util.Utilities;
import com.atsys.model.TbEvalSheetQstVo;
import com.atsys.model.TbEvalSheetVo;
import com.atsys.tims.evl.dao.Evl1102eDao;
import com.atsys.tims.evl.service.Evl1102eService;

@Service("Evl1102eService")
public class Evl1102eServiceImpl implements Evl1102eService {

    @Autowired
    private Evl1102eDao evl1102eDao;
    
    @Override
    public List<TbEvalSheetVo> getInfo(Map<String, Object> so) throws Exception {
        return evl1102eDao.selectInfo(so);
    }
    
    @Override
    public Map<String, Object> getEvalSheetWithQsts(Map<String, Object> so) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        // 평가표 코드 조회
        String sheetCd = evl1102eDao.selectSheetCd(so);
        result.put("sheetCd", sheetCd);
        
        // 평가표 문항 목록 조회
        if (sheetCd != null && !sheetCd.isEmpty()) {
            List<TbEvalSheetQstVo> qstList = evl1102eDao.selectEvalSheetQsts(sheetCd);
            result.put("qstList", qstList);
        }
        
        return result;
    }
    
    @Override
    public List<TbEvalSheetVo> selectEvalSheetList(Map<String, Object> so) throws Exception {
        return evl1102eDao.selectEvalSheetList(so);
    }
    
    @Override
    @Transactional
    public int deleteEvalSheet(List<String> sheetCds) throws Exception {
        int deleteCnt = 0;
        
        for (String sheetCd : sheetCds) {
            // 평가표 삭제 가능 여부 확인
            int evalCount = evl1102eDao.checkSheetDeletable(sheetCd);
            
            // 평가 결과가 있으면 해당 평가표는 삭제하지 않고 계속 진행
            if (evalCount > 0) {
                continue;
            }
            
            // 먼저 평가표에 속한 모든 문항 삭제
            evl1102eDao.deleteEvalSheetQstBySheetCd(sheetCd);
            
            // 평가표 삭제
            deleteCnt += evl1102eDao.deleteEvalSheet(sheetCd);
        }
        
        if (deleteCnt > 0) {
            Utilities.regSearchHst("D", deleteCnt);
        }
        
        return deleteCnt;
    }
    
    @Override
    @Transactional
    public List<Integer> saveEvalSheet(Map<String, Object> sheet, List<Map<String, Object>> qsts) throws Exception {
        List<Integer> cnt = new ArrayList<>();
        int insertCnt = 0;
        int updateCnt = 0;
        int deleteCnt = 0;
        
        if (qsts == null || qsts.isEmpty()) {
            cnt.add(0);
            cnt.add(0);
            cnt.add(0);
            return cnt;
        }
        
        // 평가표 처리
        String sheetCd = (String) sheet.get("sheetCd");
        boolean isNew = (sheetCd == null || sheetCd.isEmpty());
        
        if (isNew) {
            // 신규 평가표 등록
            TbEvalSheetVo evalSheetVo = new TbEvalSheetVo();
            evalSheetVo.setStepCd((String) sheet.get("stepCd"));
            evalSheetVo.setStaffType((String) sheet.get("staffType"));
            evl1102eDao.insertEvalSheet(evalSheetVo);
            
            // 생성된 평가표 코드 가져오기
            Map<String, Object> params = new HashMap<>();
            params.put("stepCd", sheet.get("stepCd"));
            params.put("staffType", sheet.get("staffType"));
            sheetCd = evl1102eDao.selectSheetCd(params);
        }
        
        // 기존 평가표 문항 목록 조회
        List<String> existingQstCds = new ArrayList<>();
        if (!isNew) {
            existingQstCds = evl1102eDao.selectQstCdsBySheetCd(sheetCd);
        }
        
        // 새 문항 목록에서 qstCd 추출
        List<String> newQstCds = new ArrayList<>();
        Map<String, Map<String, Object>> newQstMap = new HashMap<>();
        
        for (Map<String, Object> qst : qsts) {
            String qstCd = (String) qst.get("qstCd");
            if (qstCd != null && !qstCd.isEmpty()) {
                newQstCds.add(qstCd);
                newQstMap.put(qstCd, qst);
            }
        }
        
        // 1. 기존 문항 중 새로운 목록에 없는 문항은 삭제
        for (String existingQstCd : existingQstCds) {
            if (!newQstCds.contains(existingQstCd)) {
                // 삭제 가능 여부 확인
                int scoreCount = evl1102eDao.checkQstDeletable(existingQstCd);
                if (scoreCount == 0) {
                    // 평가 점수가 없는 경우에만 삭제
                    deleteCnt += evl1102eDao.deleteEvalSheetQst(existingQstCd);
                }
            }
        }
        
        if (deleteCnt > 0) {
            Utilities.regSearchHst("D", deleteCnt);
        }
        
        // 2. 기존 문항 중 새로운 목록에도 있는 문항은 업데이트
        for (String existingQstCd : existingQstCds) {
            if (newQstCds.contains(existingQstCd)) {
                Map<String, Object> qstMap = newQstMap.get(existingQstCd);
                TbEvalSheetQstVo qstVo = new TbEvalSheetQstVo();
                
                qstVo.setQstCd(existingQstCd);
                qstVo.setSheetCd(sheetCd);
                qstVo.setQstType((String) qstMap.get("qstType"));
                qstVo.setQstNm((String) qstMap.get("qstNm"));
                qstVo.setQstScore((Integer) qstMap.get("qstScore"));
                qstVo.setIndNm((String) qstMap.get("indNm"));
                qstVo.setIndScore((Integer) qstMap.get("indScore"));
                qstVo.setScoreCriteria((String) qstMap.get("scoreCriteria"));
                qstVo.setSortOrder((Integer) qstMap.get("sortOrder"));
                
                updateCnt += evl1102eDao.updateEvalSheetQst(qstVo);
                
                // 업데이트된 문항은 새로 추가할 목록에서 제외
                newQstCds.remove(existingQstCd);
            }
        }
        
        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        // 3. 완전히 새로운 문항 추가
        for (Map<String, Object> qstMap : qsts) {
            String qstCd = (String) qstMap.get("qstCd");
            
            // 새로운 문항이거나 기존에 없던 문항이면 추가
            if (qstCd == null || qstCd.isEmpty() || !existingQstCds.contains(qstCd)) {
                TbEvalSheetQstVo qstVo = new TbEvalSheetQstVo();
                
                qstVo.setSheetCd(sheetCd);
                qstVo.setQstType((String) qstMap.get("qstType"));
                qstVo.setQstNm((String) qstMap.get("qstNm"));
                qstVo.setQstScore((Integer) qstMap.get("qstScore"));
                qstVo.setIndNm((String) qstMap.get("indNm"));
                qstVo.setIndScore((Integer) qstMap.get("indScore"));
                qstVo.setScoreCriteria((String) qstMap.get("scoreCriteria"));
                qstVo.setSortOrder((Integer) qstMap.get("sortOrder"));
                
                insertCnt += evl1102eDao.insertEvalSheetQst(qstVo);
            }
        }
        
        if (insertCnt > 0) {
            Utilities.regSearchHst("I", insertCnt);
        }
        
        cnt.add(insertCnt);
        cnt.add(deleteCnt);
        cnt.add(updateCnt);
        
        return cnt;
    }
} 