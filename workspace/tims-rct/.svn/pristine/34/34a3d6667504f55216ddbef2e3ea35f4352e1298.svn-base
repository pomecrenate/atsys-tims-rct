package com.atsys.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.atsys.base.util.Utilities;

public abstract  class BaseElement implements IBaseElement,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887360537348835991L;

	Attributes attributes;
	@Override
	public Attributes getAttributes()
	{
		return attributes;
	}
	@Override
	public void setAttributes(Attributes attr){
		this.attributes = attr;
	}
	@Override
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		if( Utilities.beanToMap(map, this))
			return map;
		else 
			return null;
	}

	@Override
	public boolean fromMap(Map<String, Object> map) {
		return Utilities.mapToBean(map, this);
	}
	
	public <T> void copy(T obj)
	{
		Utilities.beanToBean(this, obj);
	}
	
	@Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
