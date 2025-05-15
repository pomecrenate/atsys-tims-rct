/*************************************************************
 프로그램명 : Evl1102eDao.java
 설명 : 평가표관리
 작성자 : 이예찬
 일자 : 2025.05.13
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.TbEvalSheetQstVo;
import com.atsys.model.TbEvalSheetVo;

@Repository
@Mapper
public interface Evl1102eDao {

	List<TbEvalSheetVo> selectInfo(Map<String, Object> so);
    
    String selectSheetCd(Map<String, Object> so);

    List<TbEvalSheetQstVo> selectEvalSheetQsts(String sheetCd);
    
    List<String> selectQstCdsBySheetCd(String sheetCd);

    int insertEvalSheet(TbEvalSheetVo evalSheetVo);

    int deleteEvalSheet(String sheetCd);
    
    int deleteEvalSheetQstBySheetCd(String sheetCd);
    
    int checkSheetDeletable(String sheetCd);
    
    int insertEvalSheetQst(TbEvalSheetQstVo evalSheetQstVo);

    int updateEvalSheetQst(TbEvalSheetQstVo evalSheetQstVo);

    int deleteEvalSheetQst(String qstCd);
    
    int checkQstDeletable(String qstCd);
    
    List<TbEvalSheetVo> selectEvalSheetList(Map<String, Object> so);
} 