package com.atsys.common.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.atsys.base.util.Utilities;

public abstract class BaseList<T> extends ArrayList<T> implements IBaseList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6848074911070029418L;
	private Logger LOGGER = Logger.getLogger(this.getClass());
	public static final String LIST = "list";
	public static final String SEARCH = "selectList";

	Attributes attributes;
	
	public BaseList() { 
		BaseListConverter.register(this.getClass());
	}
	
	@Override
	public Attributes getAttributes() {
		return attributes;
	}
	
	@Override
	public void setAttributes(Attributes attr){
		this.attributes = attr;
	}
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < size(); i++) {
			Object obj = this.get(i);
			if(obj instanceof IBaseElement) {
				list.add(((IBaseElement) obj).toMap());
			} else {
				list.add(Utilities.beanToMap(obj));
			}
		}
		Utilities.beanToMap(map, this);
		map.put(LIST, list);
		return map;
	}
	
	public boolean fromArray(List<?> list) {
		try{
			if(list != null){
				for(int i = 0; i < list.size(); i++) {
					T t = getNewInstance();
					Object obj = list.get(i);
					Utilities.beanToBean(t, obj);
					add(t);
				}
			}
		}catch(IllegalStateException e){
			LOGGER.debug("info to IllegalStateException");
			return false;
		}catch(Exception e){
			LOGGER.debug("info to Exception");
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean fromMap(Map<String, Object> map) {
		if (map == null) {
			return false;
		}
		Utilities.mapToBean(map, this);
		if (map.containsKey(LIST)) {
			try {
				List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(LIST);
				for (int i = 0; i < list.size(); i++) {
					T t = getNewInstance();
					Utilities.mapToBean(list.get(i),t);
					this.add(t);
				}
			} catch (IllegalStateException ex) {
				LOGGER.debug("info to IllegalStateException");
			} catch (Exception ex) {
				LOGGER.debug("info to Exception");
			}

		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParameterClass() {
		return (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
//	@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	@Override
	public T getNewInstance() {
		try {
			Class<T> cls = getParameterClass();
			return cls.newInstance();
		} catch (IllegalStateException e) {
			LOGGER.debug("info to IllegalStateException");
			return null;
		} catch (Exception e) {
			LOGGER.debug("info to Exception");
			return null;
		}
	}
	@Override
	public boolean load(JSONArray array) {
		if(array == null) {
			return false;
		}
		for (Object object : array) {
			T t = getNewInstance();
			if(t == null) {
				return false;
			}
			Utilities.beanToBean(t, object);
			add(t);
		}
		return false;
	}
}
