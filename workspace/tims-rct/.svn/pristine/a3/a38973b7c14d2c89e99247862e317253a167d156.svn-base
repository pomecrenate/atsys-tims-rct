package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.Evl1202eVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbEvalSchVo;

@Repository
@Mapper 
public interface Evl1202eDao {

	public List<Evl1202eVo> selectList(Map<String, Object> so);

	public int selectTotalCount(Map<String, Object> so);

	public TbAppVo selectAppNm(TbAppVo vo); 

	public int insertEvalSch(TbEvalSchVo vo);

	public TbEvalSchVo selectEvalSchData(TbEvalSchVo vo);

}
