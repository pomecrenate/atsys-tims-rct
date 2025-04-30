package com.atsys.common.base;

import java.util.LinkedHashMap;
import java.util.Map;

import com.atsys.base.util.Utilities;

public class Row extends LinkedHashMap<String, Object> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507099778772610701L;
	boolean autoCamel = true;
	public boolean isAutoCamel()
	{
		return autoCamel;
	}
	public void setAutoCamel(boolean bAuto)
	{
		autoCamel = bAuto;
	}
	
	public String getString(String strKey)
	{
		if(!containsKey(strKey))
			return "";
		return Utilities.nullCheck(get(strKey));
	}
	public int getInt(String strKey)
	{
		return Utilities.parseInt(getString(strKey));
	}
	public long getLong(String strKey)
	{
		return Utilities.parseLong(getString(strKey));
	}
	public double getDouble(String strKey)
	{
		return Utilities.parseDouble(getString(strKey));
	}
	public float getFloat(String strKey)
	{
		return Utilities.parseFloat(getString(strKey));
	}
	public boolean getBoolean(String strKey)
	{
		return Utilities.parseBoolean(getString(strKey));
	}
	public void set(String strKey, boolean b) 
	{
		if(containsKey(strKey))
			remove(strKey);
		put(strKey,b?1:0);
	}
	public void set(String strKey, Object obj) 
	{
		if(containsKey(strKey))
			remove(strKey);
		put(strKey,obj);
	}
	public void copy(Row row)
	{
		if(row == null || row.size()==0)
			return;
		putAll(row);
	}
	public void copy(Map<String,?> row)
	{
		if(row == null || row.size()==0)
			return;
		putAll(row);
	}
	
	@Override
	public Object put(String key, Object value) 
	{
		String key2 = key;
		key2=checkKey(key2);
		if(isAutoCamel())
			key2 = Utilities.convert2CamelCase(key2);
		return super.put(key2 , value);
	}
	@Override
	public boolean containsKey(Object key) {
		Object key2 = key;
		key2=checkKey(key2);
		if(isAutoCamel())
		{
			if(super.containsKey(key2))
				return true;
			else
				return super.containsKey(Utilities.convert2CamelCase(Utilities.nullCheck(key2)));
		}
		else
			return super.containsKey(key2);
		
	}
	@Override
	public Object get(Object key) 
	{
		Object key2 = key;	
		key2=checkKey(key2);
		if(isAutoCamel())
		{
			if(super.containsKey(key2))
				return super.get(key2);
			else
				return super.get(Utilities.convert2CamelCase(Utilities.nullCheck(key2)));
		}
		else
			return super.get(key2);
	}
	
	public String checkKey(Object obj)
	{
		if(obj==null)
			return null;
		return obj.toString();
	}

}
