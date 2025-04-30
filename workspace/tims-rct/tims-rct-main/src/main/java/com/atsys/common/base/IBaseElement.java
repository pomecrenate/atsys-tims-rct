package com.atsys.common.base;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface IBaseElement {
	Map<String, Object> toMap();
	boolean fromMap(Map<String, Object> map);
	Object execute(String name);
	Attributes getAttributes();
	void setAttributes(Attributes map);
}
