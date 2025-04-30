package com.atsys.common.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.atsys.base.util.Utilities;

public class Attributes extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630581588958331711L;
	private Logger LOGGER = Logger.getLogger(this.getClass());
	private boolean autoCamel = false;
	private JSONArray jsonArray=null;
	
	public String getRequestBody() {
		return Utilities.getRequestBody();
	}
	
	public String getString(String key) {
		return (String)getObject(key);
	}
	 
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public <T> T getObject(Class<T> cls) {
		try {
			T obj = null;
			if(String.class.equals(cls)) {
				return (T) getRequestBody();
			}
			else if(cls.isInterface()) {
				if(List.class.equals(cls)) {
					obj = (T) new ArrayList();
				} else {
					return null;
				}
			} else {
				obj = cls.newInstance();
			}
			
			if(obj instanceof BaseList && getJsonArray() != null) {
				BaseList list = (BaseList)obj;
				list.fromArray(getJsonArray());
			} else if(obj instanceof List && jsonArray != null) {
				List list = (List)obj;
				list.addAll(jsonArray);
			} else {
				Utilities.beanToBean(obj, this);
			}

			if(obj instanceof IBaseElement) {
				((IBaseElement)obj).setAttributes(this);
			}
			return obj;
		} catch (IllegalStateException e) {
			LOGGER.debug("info to IllegalStateException");
			return null;
		} catch (Exception e) {
			LOGGER.debug("info to Exception");
			return null;
		} 
	}
	
	public Object getObject(String key) {
		if(this.containsKey(key)) {
			return get(key);
		} else {
			return null;
		}
	}
	
	public void set(String strKey, boolean b) {
		if(containsKey(strKey)) {
			remove(strKey);
		}
		put(strKey,b?1:0);
	}
	
	public void set(String strKey, Object obj) {
		if(containsKey(strKey)) {
			remove(strKey);
		}
		put(strKey,obj);
	}
	
	public void copy(Attributes row) {
		if(row == null || row.size() == 0) {
			return;
		}
		putAll(row);
	}
	
	public void copy(Map<String,String> row) {
		if(row == null || row.size() == 0) {
			return;
		}
		putAll(row);
	}
	
	public boolean isAutoCamel() {
		return autoCamel;
	}
	
	public void setAutoCamel(boolean bAuto) {
		autoCamel = bAuto;
	}
	
	@Override
	public Object put(String key, Object value) {
		String key2 = key;
		if(isAutoCamel()) {
			key2 = Utilities.convert2CamelCase(key2);
		}
		return super.put(key2 , value);
	}
	
	@Override
	public boolean containsKey(Object key) {
		if(isAutoCamel()) {
			if(super.containsKey(key)) {
				return true;
			} else {
				return super.containsKey(Utilities.convert2CamelCase(Utilities.nullCheck(key)));
			}
		} else {
			return super.containsKey(key);
		}
	}
	
	public JSONArray getJsonArray() { 
		return jsonArray;
	}
	
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
}