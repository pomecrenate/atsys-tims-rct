/*************************************************************
 프로그램명 : Evl1103eDao.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.Evl1103eVo;

@Repository
@Mapper
public interface Evl1104eDao {
    List<Evl1103eVo> selectInfo(Map<String, Object> so);
} 