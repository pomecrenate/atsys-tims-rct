/*************************************************************
 프로그램명 : Evl1101eServiceImpl.java
 설명 : 평가위원관리
 작성자 : 이예찬
 일자 : 2025.05.08
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsys.base.util.Utilities;
import com.atsys.model.Evl1101eVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.model.TbSelEvalJdgVo;
import com.atsys.tims.evl.dao.Evl1101eDao;
import com.atsys.tims.evl.service.Evl1101eService;

@Service("Evl1101eService")
public class Evl1101eServiceImpl implements Evl1101eService {

    @Autowired
    private Evl1101eDao evl1101eDao;
    
    @Override
    public Evl1101eVo getInfo(Map<String, Object> so) throws Exception {
        return evl1101eDao.selectInfo(so);
    }
    
    @Override
    public List<TbNtcFieldVo> getFields(Map<String, Object> so) throws Exception {
        return evl1101eDao.selectFields(so);
    }
    
    @Override
    public List<TbSelEvalJdgVo> getSelectedJudges(Map<String, Object> so) throws Exception {
        return evl1101eDao.selectJudges(so);
    }
    
    @Override
    public List<TbSelEvalJdgVo> getJudgeListByType(String jdgDiv) throws Exception {
        return evl1101eDao.selectJudgeListByType(jdgDiv);
    }
    
    @Override
    @Transactional
    public List<Integer> insertJudges(List<Map<String, Object>> judges) throws Exception {
    	List<Integer> cnt = new ArrayList<>();
        int insertCnt = 0;
        int deleteCnt = 0;
        int updateCnt = 0;
        
        if (judges == null || judges.isEmpty()) {
            cnt.add(0);
            cnt.add(0);
            cnt.add(0);
            return cnt;
        }
        
        // 첫번째 평가위원의 정보에서 stepCd와 fieldCd를 가져옴
        String stepCd = (String) judges.get(0).get("stepCd");
        String fieldCd = (String) judges.get(0).get("fieldCd");
        
        // 해당 stepCd와 fieldCd로 현재 등록된 모든 평가위원 조회
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("stepCd", stepCd);
        searchParams.put("fieldCd", fieldCd);
        List<TbSelEvalJdgVo> existingJudges = evl1101eDao.selectJudgesId(searchParams);
        
        // 새 평가위원 목록의 usrId 리스트 추출
        List<String> newJudgeUsrIds = judges.stream()
                                           .map(judge -> (String) judge.get("usrId"))
                                           .collect(Collectors.toList());
        
        // 1. 기존 평가위원 중 새로운 목록에 없는 평가위원은 삭제(delYn="Y")
        for (TbSelEvalJdgVo existingJudge : existingJudges) {
            if (!newJudgeUsrIds.contains(existingJudge.getUsrId()) && "N".equals(existingJudge.getDelYn())) {
                Map<String, Object> params = new HashMap<>();
                params.put("jdgCd", existingJudge.getUsrCd());
                params.put("delYn", "Y");
                deleteCnt += evl1101eDao.updateJudgeStatus(params);
            }
        }

        if (deleteCnt > 0) {
            Utilities.regSearchHst("D", deleteCnt);
        }
        
        // 2. 기존에 삭제된 평가위원 중 새로운 목록에 있는 평가위원은 복구(delYn="N")
        for (TbSelEvalJdgVo existingJudge : existingJudges) {
            if (newJudgeUsrIds.contains(existingJudge.getUsrId()) && "Y".equals(existingJudge.getDelYn())) {
                Map<String, Object> params = new HashMap<>();
                params.put("jdgCd", existingJudge.getUsrCd());
                params.put("delYn", "N");
                updateCnt += evl1101eDao.updateJudgeStatus(params);
                
                // 복구된 평가위원은 newJudgeUsrIds에서 제거
                newJudgeUsrIds.remove(existingJudge.getUsrId());
            }
        }

        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        // 3. 새로운 평가위원 추가
        for (Map<String, Object> judge : judges) {
            String usrId = (String) judge.get("usrId");
            
            // 이미 존재하는 평가위원이면 건너뜀
            Map<String, Object> checkParams = new HashMap<>();
            checkParams.put("stepCd", stepCd);
            checkParams.put("fieldCd", fieldCd);
            checkParams.put("usrId", usrId);
            int exists = evl1101eDao.checkJudgeExists(checkParams);
            
            if (exists == 0) {
                // 새로운 평가위원 추가
                TbSelEvalJdgVo judgeVo = new TbSelEvalJdgVo();
                
                judgeVo.setStepCd((String) judge.get("stepCd"));
                judgeVo.setFieldCd((String) judge.get("fieldCd"));
                judgeVo.setJdgDiv((String) judge.get("jdgDiv"));
                judgeVo.setUsrId((String) judge.get("usrId"));
                judgeVo.setNm((String) judge.get("nm"));
                judgeVo.setChairYn((String) judge.get("chairYn"));
                
                insertCnt += evl1101eDao.insertJudge(judgeVo);
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
    
    @Override
    @Transactional
    public int confirmJudges(List<String> usrCds) throws Exception {
    	int updateCnt = 0;
    	
    	for (String usrCd : usrCds) {
            Map<String, Object> params = new HashMap<>();
            params.put("usrCd", usrCd);
            // 확정 상태로 업데이트
            params.put("confirmedYn", "Y");
	        updateCnt += evl1101eDao.confirmJudges(params);
    	}
        
        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        return updateCnt;
    }
    
    @Override
    @Transactional
    public int cancelConfirmJudges(List<String> usrCds) throws Exception {
    	int evalCount = 0;
    	int updateCnt = 0;
    	
    	for (String usrCd : usrCds) {
	        // 평가위원 확정 취소 가능 여부 확인 (이미 평가가 시작된 경우 취소 불가)
	        evalCount += evl1101eDao.checkConfirmCancelable(usrCd);
    	}
    	
        if (evalCount > 0) {
            return 0;
        }
        
        for (String usrCd : usrCds) {
            Map<String, Object> params = new HashMap<>();
            params.put("usrCd", usrCd);
	        // 미확정 상태로 업데이트
            params.put("confirmedYn", "N");
	        updateCnt += evl1101eDao.confirmJudges(params);
        }
        
        if (updateCnt > 0) {
            Utilities.regSearchHst("U", updateCnt);
        }
        
        return updateCnt;
    }
} 