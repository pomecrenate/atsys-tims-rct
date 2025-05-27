/*************************************************************
 프로그램명 : Evl1105eServiceImpl.java
 설명 : 결과관리
 작성자 : 이예찬
 일자 : 2025.05.23
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsys.base.util.Utilities;
import com.atsys.model.Evl1104eVo;
import com.atsys.model.Evl1105eVo;
import com.atsys.model.Evl1105p1Vo;
import com.atsys.model.TbEvalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;
import com.atsys.tims.evl.dao.Evl1105eDao;
import com.atsys.tims.evl.service.Evl1105eService;

@Service("Evl1105eService")
public class Evl1105eServiceImpl implements Evl1105eService {

    @Autowired
    private Evl1105eDao evl1105eDao;
    
    @Override
    public List<Evl1105eVo> getInfo(Map<String, Object> so) throws Exception {
        return evl1105eDao.selectInfo(so);
    }
    
    @Override
    public List<Evl1104eVo> getApplicants(Map<String, Object> so) throws Exception {
        List<Evl1104eVo> applicants = evl1105eDao.selectApplicants(so);
        calculateRanks(applicants);
        return applicants;
    }
    
    @Override
    public List<TbSelEvalJdgVo> getJudges(Map<String, Object> so) throws Exception {
        return evl1105eDao.selectJudges(so);
    }
    
    @Override
    @Transactional
    public int updatePassStep(Map<String, Object> so) throws Exception {
        int updateCnt = evl1105eDao.updatePassStep(so);
        
        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        return updateCnt;
    }
    
    @Override
    public Evl1105p1Vo getResultInfo(Map<String, Object> so) throws Exception {
        return evl1105eDao.selectResultInfo(so);
    }
    
    @Override
    public List<TbEvalScoreVo> getResultScores(Map<String, Object> so) throws Exception {
        return evl1105eDao.selectResultScores(so);
    }
    
    // 순위 계산
    private void calculateRanks(List<Evl1104eVo> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            return;
        }
        
        // 최고,최저 제외 평균 기준으로 정렬
        applicants.sort((a, b) -> {
            int scoreA = a.getExceptedAvg();
            int scoreB = b.getExceptedAvg();
            
            if (scoreA == scoreB) {
                // 동점인 경우 이름 순
                return a.getNm().compareTo(b.getNm());
            }
            
            return scoreB - scoreA; // 내림차순 정렬
        });
        
        // 순위 부여
        int currentRank = 1;
        int prevScore = -1;
        
        for (int i = 0; i < applicants.size(); i++) {
            Evl1104eVo applicant = applicants.get(i);
            int score = applicant.getExceptedAvg();
            
            // 이전 점수와 같다면 같은 순위 부여
            if (score == prevScore) {
                applicant.setRank(currentRank);
            } else {
                // 이전과 다른 점수라면 새로운 순위 부여
                currentRank = i + 1;
                applicant.setRank(currentRank);
            }
            
            prevScore = score;
        }
    }
}