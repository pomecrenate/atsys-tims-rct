package com.atsys.common.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class BaseListConverter implements Converter {
	private Logger LOGGER = Logger.getLogger(this.getClass());
	@SuppressWarnings("rawtypes")
	private static Map<Class<? extends BaseList>, Object> map = new HashMap<Class<? extends BaseList>, Object>();
	private static BaseListConverter converter = new BaseListConverter();
	@SuppressWarnings("rawtypes")
	public static void register(Class<? extends BaseList> type) {
		if (map.containsKey(type))
			return;
		
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		beanUtilsBean.getConvertUtils().register(converter, type);
		map.put(type, type);

	}

//	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object convert(Class type, Object value) {
		try {
			@SuppressWarnings("deprecation")
			Object object = type. newInstance();
			if (object instanceof BaseList) {
				if (value instanceof List) {
					BaseList list = (BaseList) object;
					list.fromArray((List) value);
					return list;
				}
			}
		} catch (ArrayStoreException e) {
			LOGGER.debug("info to ArrayStoreException");
		} catch (Exception e) {
			LOGGER.debug("info to Exception");
		}
		return null;
	}

}
