package com.atsys.tims.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbAppAwardVo;
import com.atsys.model.TbAppCareerVo;
import com.atsys.model.TbAppEduVo;
import com.atsys.model.TbAppRecVo;
import com.atsys.model.TbAppSelfIntroVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbUpldFileVo;
import com.atsys.tims.sys.dao.AppDetailDao;
import com.atsys.tims.sys.service.AppDetailService;

@Service("AppDetailService")
public class AppDetailServiceImpl implements AppDetailService { 
	
	@Autowired
	private AppDetailDao appDetailDao;


	/* 지원서 인적사항 조회 */
	@Override
	public TbAppVo selectOneApp(String appCd) { 
		return appDetailDao.selectOneApp(appCd);
	}

	/* 지원서 학력사항 조회 */
	@Override
	public List<TbAppEduVo> selectAppEduList(String appCd) { 
		return appDetailDao.selectAppEduList(appCd);
	}

	/* 지원서 경력사항 조회 */
	@Override
	public List<TbAppCareerVo> selectAppCareerList(String appCd) {
		return appDetailDao.selectAppCareerList(appCd);
	}

	/* 지원서 수상 및 자격증 조회 */
	@Override
	public List<TbAppAwardVo> selectAppAwardList(String appCd) {
		return appDetailDao.selectAppAwardList(appCd);
	}

	/* 지원서 추천인 조회 */
	@Override
	public List<TbAppRecVo> selectAppRecList(String appCd) { 
		return appDetailDao.selectAppRecList(appCd);
	}

	/* 지원서 자기소개 조회 */
	@Override
	public TbAppSelfIntroVo selectSelfIntro(String appCd) { 
		return appDetailDao.selectSelfIntro(appCd);
	}

	/* 지원서 프로필 이미지 조회 */
	@Override
	public TbUpldFileVo selectAppProfileImg(String appCd) { 
		return appDetailDao.selectAppProfileImg(appCd);
	}
	
}
