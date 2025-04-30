package com.atsys.common.base;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Rows extends ArrayList<Row > {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4142178478258931986L;
	public void copy(Rows row)
	{
		
		this.addAll(row);
	}
	public List<Map<String, Object>> toList() {

		ArrayList<Map<String, Object>> arr = new ArrayList<Map<String, Object>> ();
		arr.addAll(this);
		return arr;
	}

}
