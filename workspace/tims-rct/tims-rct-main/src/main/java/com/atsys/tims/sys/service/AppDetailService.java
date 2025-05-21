package com.atsys.tims.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbAppAwardVo;
import com.atsys.model.TbAppCareerVo;
import com.atsys.model.TbAppEduVo;
import com.atsys.model.TbAppRecVo;
import com.atsys.model.TbAppSelfIntroVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbUpldFileVo;

@Transactional(readOnly = true)
public interface AppDetailService {
	
	public TbAppVo selectOneApp(String appCd);

	public List<TbAppEduVo> selectAppEduList(String appCd);

	public List<TbAppCareerVo> selectAppCareerList(String appCd);

	public List<TbAppAwardVo> selectAppAwardList(String appCd);

	public List<TbAppRecVo> selectAppRecList(String appCd);

	public TbAppSelfIntroVo selectSelfIntro(String appCd);

	public TbUpldFileVo selectAppProfileImg(String appCd); 

}
