/*************************************************************
 프로그램명 : Evl1103eServiceImpl.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
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
import com.atsys.model.Evl1103eVo;
import com.atsys.model.TbEvalResultVo;
import com.atsys.model.TbEvalScoreVo;
import com.atsys.tims.evl.dao.Evl1103eDao;
import com.atsys.tims.evl.service.Evl1103eService;

@Service("Evl1103eService")
public class Evl1103eServiceImpl implements Evl1103eService {

    @Autowired
    private Evl1103eDao evl1103eDao;
    
    @Override
    public List<Evl1103eVo> getInfo(Map<String, Object> so) throws Exception {
        return evl1103eDao.selectInfo(so);
    }
    
    @Override
    public List<TbEvalResultVo> getApplicants(Map<String, Object> so) throws Exception {
        return evl1103eDao.selectApplicants(so);
    }
    
    @Override
    public List<TbEvalScoreVo> getEvalSheetWithScores(Map<String, Object> so) throws Exception {
        return evl1103eDao.selectEvalDetails(so);
    }
    
    @Override
    @Transactional
    public List<Integer> saveAndConfirmResult(Map<String, Object> result, List<Map<String, Object>> scores) throws Exception {
        List<Integer> cnt = new ArrayList<>();
        int insertCnt = 0;
        int updateCnt = 0;
        int deleteCnt = 0;
        
        if (scores == null || scores.isEmpty()) {
            cnt.add(0);
            cnt.add(0);
            cnt.add(0);
            return cnt;
        }
        
        // 평가결과 관련 정보 추출
        String jdgCd = (String) result.get("usrCd");
        String appCd = (String) result.get("appCd");
        String stepCd = (String) result.get("stepCd");
        String resultCd = (String) result.get("resultCd");
        String opinion = (String) result.get("opinion");
        String minutes = (String) result.get("minutes");
            
        // sheetCd 가져오기
        String sheetCd = (String) result.get("sheetCd");
        if (sheetCd == null || sheetCd.isEmpty()) {
            Map<String, Object> sheetParams = new HashMap<>();
            sheetParams.put("appCd", appCd);
            sheetParams.put("stepCd", stepCd);
            sheetCd = evl1103eDao.selectSheetCdByAppCd(sheetParams);
        }
        
        // 평가결과 처리
        boolean isNew = (resultCd == null || resultCd.isEmpty());
            
        if (isNew) {
        	// 신규 평가결과 생성
        	TbEvalResultVo evalResult = new TbEvalResultVo();
        	evalResult.setJdgCd(jdgCd);
        	evalResult.setAppCd(appCd);
        	evalResult.setSheetCd(sheetCd);
        	evalResult.setOpinion(opinion);
        	evalResult.setMinutes(minutes);
        	evalResult.setConfirmedYn("Y");
                
        	// 평가 결과 저장
        	evl1103eDao.insertEvalResult(evalResult);
                
        	// 생성된 결과코드 가져오기
        	Map<String, Object> params = new HashMap<>();
        	params.put("usrCd", jdgCd);
        	params.put("appCd", appCd);
        	resultCd = evl1103eDao.selectResultCd(params);
        } else {
        	// 기존 평가결과 업데이트
        	TbEvalResultVo evalResult = new TbEvalResultVo();
        	evalResult.setResultCd(resultCd);
        	evalResult.setOpinion(opinion);
        	evalResult.setMinutes(minutes);
        	evalResult.setConfirmedYn("Y");
                
        	// 평가 결과 업데이트
        	evl1103eDao.updateEvalResult(evalResult);
        }
        
        // 기존 점수 목록 조회
        List<Map<String, Object>> existingScores = new ArrayList<>();
        Map<String, String> existingScoreMap = new HashMap<>();
        
        if (!isNew) {
            existingScores = evl1103eDao.selectScoreCdsByResultCd(resultCd);
            for (Map<String, Object> score : existingScores) {
                String qstCd = (String) score.get("QST_CD");
                String scoreCd = (String) score.get("SCORE_CD");
                existingScoreMap.put(qstCd, scoreCd);
            }
        }
        
        // 새 점수 목록에서 qstCd 추출
        List<String> newQstCds = new ArrayList<>();
        Map<String, Map<String, Object>> newScoreMap = new HashMap<>();
        
        for (Map<String, Object> score : scores) {
            String qstCd = (String) score.get("qstCd");
            if (qstCd != null && !qstCd.isEmpty()) {
                newQstCds.add(qstCd);
                newScoreMap.put(qstCd, score);
            }
        }
        
        // 1. 기존 점수 중 새로운 목록에 없는 점수는 삭제
        for (Map<String, Object> score : existingScores) {
            String qstCd = (String) score.get("QST_CD");
            String scoreCd = (String) score.get("SCORE_CD");
            
            if (!newQstCds.contains(qstCd)) {
                deleteCnt += evl1103eDao.deleteEvalScore(scoreCd);
            }
        }
        
        if (deleteCnt > 0) {
            Utilities.regSearchHst("D", deleteCnt);
        }
            
        // 2. 기존 점수 중 새로운 목록에도 있는 점수는 업데이트
        for (String qstCd : existingScoreMap.keySet()) {
            if (newQstCds.contains(qstCd)) {
                Map<String, Object> newScore = newScoreMap.get(qstCd);
                String scoreCd = existingScoreMap.get(qstCd);
                
                TbEvalScoreVo evalScore = new TbEvalScoreVo();
                evalScore.setScoreCd(scoreCd);
                evalScore.setResultScore(Integer.parseInt(newScore.get("resultScore").toString()));
                
                updateCnt += evl1103eDao.updateEvalScore(evalScore);
                
                // 업데이트된 점수는 새로 추가할 목록에서 제외
                newQstCds.remove(qstCd);
            }
        }
        
        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        // 3. 완전히 새로운 점수 추가
        for (String qstCd : newQstCds) {
            Map<String, Object> score = newScoreMap.get(qstCd);
            
            TbEvalScoreVo evalScore = new TbEvalScoreVo();
            evalScore.setQstCd(qstCd);
            evalScore.setResultCd(resultCd);
            evalScore.setResultScore(Integer.parseInt(score.get("resultScore").toString()));
                    
            insertCnt += evl1103eDao.insertEvalScore(evalScore);
        }
            
        if (insertCnt > 0) {
        	Utilities.regSearchHst("I", insertCnt);
        }
            
        // 결과 카운트를 리스트에 저장
        cnt.add(insertCnt);
        cnt.add(deleteCnt);
        cnt.add(updateCnt);
            
        return cnt;
    }
    
    @Override
    @Transactional
    public int cancelConfirmResult(Map<String, Object> so) throws Exception {
    	int totalCount = 0;
        int updateCnt = 0;
        
        Map<String, Object> params = new HashMap<>();
        params.put("stepCd", so.get("stepCd"));
        params.put("appCd", so.get("appCd"));
        
        // 점수가 이미 집계되었는지 확인 (집계된 경우 취소 불가)
        totalCount += evl1103eDao.checkConfirmCancelable(params);
    	
        if (totalCount > 0) {
            return 0;
        }
        
        Map<String, Object> evalParams = new HashMap<>();
        evalParams.put("resultCd", so.get("resultCd"));
        // 미확정 상태로 업데이트
        evalParams.put("confirmedYn", "N");
        updateCnt += evl1103eDao.confirmResult(evalParams);
            
        if (updateCnt > 0) {
        	Utilities.regSearchHst("U", updateCnt);
        }
        
        return updateCnt;
    }
    
    @Override
    public String getResultCd(Map<String, Object> so) throws Exception {
        // 평가위원 코드(usrCd)와 지원자 코드(appCd)로 결과코드 조회
        return evl1103eDao.selectResultCd(so);
    }
} 