/*************************************************************
 프로그램명 : Evl1103eService.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.Evl1103eVo;

@Transactional(readOnly = true)
public interface Evl1104eService {
    List<Evl1103eVo> getInfo(Map<String, Object> so) throws Exception;
} 