/*************************************************************
  프로그램명 : EntityInitializer.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.bind.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

public class EntityInitializer {
	
	private List<IEntityInitailizable> initializers = Collections.emptyList();

	public void setInitializers(List<IEntityInitailizable> initializers) {
		Assert.notEmpty(initializers, "'initializers' must not be empty");
		this.initializers = new ArrayList<IEntityInitailizable>(initializers);
	}

	public void initializeEntity(Object entity, HttpServletRequest request) {
		IEntityInitailizable initializer = findSupportableInitializer(entity.getClass());
		
		if (initializer != null) {
			initializer.initializeEntity(entity, request);
		}
	}
	
	private IEntityInitailizable findSupportableInitializer(Class<?> clazz) {
		
		for (IEntityInitailizable initializer : initializers) {
			
			if (isAssignableFrom(initializer.getInitializableType(), clazz)) {
				return initializer;
			}
		}
		
		return null;
	}
	
	protected boolean isAssignableFrom(Class<?> sourceType, Class<?> targetType) {
		return sourceType.isAssignableFrom(targetType);
	}

}
