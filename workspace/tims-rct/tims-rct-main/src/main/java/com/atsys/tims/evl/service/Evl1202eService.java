package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.Evl1202eVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbEvalSchVo;

@Transactional(readOnly = true)
public interface Evl1202eService {

	public List<Evl1202eVo> selectList(Map<String, Object> so);

	public int selectTotalCount(Map<String, Object> so);

	public TbAppVo selectAppNm(TbAppVo vo);
 
	public int insertEvalSch(TbEvalSchVo vo) throws Exception;

	public TbEvalSchVo selectEvalSchData(TbEvalSchVo vo); 

}
