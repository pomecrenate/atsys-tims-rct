/*************************************************************
 프로그램명 : Evl1104eServiceImpl.java
 설명 : 사정관리
 작성자 : 이예찬
 일자 : 2025.05.22
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
import com.atsys.model.Evl1104eVo;
import com.atsys.model.TbEvalTotalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;
import com.atsys.tims.evl.dao.Evl1104eDao;
import com.atsys.tims.evl.service.Evl1104eService;

@Service("Evl1104eService")
public class Evl1104eServiceImpl implements Evl1104eService {

    @Autowired
    private Evl1104eDao evl1104eDao;
    
    @Override
    public List<TbEvalTotalScoreVo> getInfo(Map<String, Object> so) throws Exception {
        return evl1104eDao.selectInfo(so);
    }
    
    @Override
    public List<Evl1104eVo> getApplicants(Map<String, Object> so) throws Exception {
        return evl1104eDao.selectApplicants(so);
    }
    
    @Override
    public List<TbSelEvalJdgVo> getJudges(Map<String, Object> so) throws Exception {
        return evl1104eDao.selectJudges(so);
    }
    
    @Override
    public String getTotalCd(Map<String, Object> so) throws Exception {
        return evl1104eDao.selectTotalCd(so);
    }
    
    @Override
    @Transactional
    public List<Integer> calcScores(Map<String, Object> so) throws Exception {
    	List<Integer> cnt = new ArrayList<>();
    	int insertCnt = 0;
    	int updateCnt = 0;
    	
        String appCd = (String) so.get("appCd");
        String stepCd = (String) so.get("stepCd");
        
        if (appCd == null || appCd.isEmpty() || stepCd == null || stepCd.isEmpty()) {
            cnt.add(0);
            cnt.add(0);
            cnt.add(0);
            return cnt;
        }
        
        // 기존 총점 확인
        String totalCd = evl1104eDao.selectTotalCd(so);
        boolean isNew = (totalCd == null || totalCd.isEmpty());
        
        // 확정된 평가점수 목록 조회
        List<Map<String, Object>> scores = evl1104eDao.selectConfirmedScores(so);
        
        // 평균 계산
        Map<String, Object> calcResults = calculateScores(scores);
        int totalAvg = (int) calcResults.get("totalAvg");
        int exceptedAvg = (int) calcResults.get("exceptedAvg");
        
        TbEvalTotalScoreVo totalScore = new TbEvalTotalScoreVo();
        totalScore.setStepCd(stepCd);
        totalScore.setAppCd(appCd);
        totalScore.setTotalAvg(totalAvg);
        totalScore.setExceptedAvg(exceptedAvg);
        
        // 신규 등록 또는 업데이트
        if (isNew) {
            // 신규 등록
            insertCnt = evl1104eDao.insertTotalScore(totalScore);
            
            if (insertCnt > 0) {
                Utilities.regSearchHst("I", insertCnt);
            }
        } else {
            // 업데이트
            totalScore.setTotalCd(totalCd);
            updateCnt = evl1104eDao.updateTotalScore(totalScore);
            
            Map<String, Object> params = new HashMap<>();
            params.put("delYn", "N");
            params.put("totalCd", totalCd);
            
            // 삭제여부 변경
            evl1104eDao.deleteTotalScore(params);
            
            if (updateCnt > 0) {
                Utilities.regSearchHst("U", updateCnt);
            }
        }
        
     // 결과 카운트를 리스트에 저장
        cnt.add(insertCnt);
        cnt.add(updateCnt);
        
        return cnt;
    }
    
    @Override
    @Transactional
    public int cancelCalcScores(Map<String, Object> so) throws Exception {
        int deleteCnt = 0;
        
        Map<String, Object> params = new HashMap<>();
        params.put("delYn", "Y");
        params.put("totalCd", so.get("totalCd"));
        
        // TB_EVAL_TOTAL_SCORE에서 삭제
        deleteCnt = evl1104eDao.deleteTotalScore(params);
        
        if (deleteCnt > 0) {
            Utilities.regSearchHst("D", deleteCnt);
        }
        
        return deleteCnt;
    }
    
    // 평균 점수 및 최고/최저 제외 평균 계산
    private Map<String, Object> calculateScores(List<Map<String, Object>> scores) {
        Map<String, Object> result = new HashMap<>();
        
        if (scores == null || scores.isEmpty()) {
            result.put("totalAvg", 0);
            result.put("exceptedAvg", 0);
            return result;
        }
        
        // 총합 계산
        int total = 0;
        List<Integer> scoreValues = new ArrayList<>();
        
        for (Map<String, Object> score : scores) {
            int scoreValue = ((Number) score.get("SCORE")).intValue();
            total += scoreValue;
            scoreValues.add(scoreValue);
        }
        
        // 평균 계산
        int totalAvg = scores.size() > 0 ? total / scores.size() : 0;
        result.put("totalAvg", totalAvg);
        
        // 최고, 최저 제외 평균 계산
        if (scores.size() <= 2) {
            // 평가위원이 2명 이하면 그냥 평균 반환
            result.put("exceptedAvg", totalAvg);
        } else {
            // 최고, 최저값 찾기
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            
            for (int score : scoreValues) {
                if (score > max) max = score;
                if (score < min) min = score;
            }
            
            // 최고, 최저 제외한 합계 계산
            int exceptedTotal = total - max - min;
            int exceptedCount = scores.size() - 2;
            int exceptedAvg = exceptedCount > 0 ? exceptedTotal / exceptedCount : 0;
            
            result.put("exceptedAvg", exceptedAvg);
        }
        
        return result;
    }
}