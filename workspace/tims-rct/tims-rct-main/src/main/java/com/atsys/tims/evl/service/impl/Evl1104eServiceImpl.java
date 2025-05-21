/*************************************************************
 프로그램명 : Evl1103eServiceImpl.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.Evl1103eVo;
import com.atsys.tims.evl.dao.Evl1104eDao;
import com.atsys.tims.evl.service.Evl1104eService;

@Service("Evl1104eService")
public class Evl1104eServiceImpl implements Evl1104eService {

    @Autowired
    private Evl1104eDao evl1104eDao;
    
    @Override
    public List<Evl1103eVo> getInfo(Map<String, Object> so) throws Exception {
        return evl1104eDao.selectInfo(so);
    }
}