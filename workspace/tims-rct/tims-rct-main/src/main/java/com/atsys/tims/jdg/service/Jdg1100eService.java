/*************************************************************
 프로그램명 : Jdg1100eService.java
 설명 : 외부 평가위원 관리
 작성자 : 이예찬
 일자 : 2025.04.22
*************************************************************/
package com.atsys.tims.jdg.service;

import java.util.List;
import java.util.Map;

import com.atsys.model.TbExEvalJdgVo;

public interface Jdg1100eService {
    List<TbExEvalJdgVo> getExJudgeList(Map<String, Object> so) throws Exception;
    int getExJudgeCount(Map<String, Object> so) throws Exception;
    int insertExJudge(List<Map<String, Object>> judges) throws Exception;
    int deleteExJudge(List<String> usrCds) throws Exception;
}