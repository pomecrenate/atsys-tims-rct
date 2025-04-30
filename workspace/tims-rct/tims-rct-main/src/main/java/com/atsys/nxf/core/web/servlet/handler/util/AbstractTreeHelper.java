/*************************************************************
  프로그램명 : AbstractTreeHelper.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.util;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

abstract public class AbstractTreeHelper {
	public enum TreeElement {
		ID, NAME, DEPTH, URI, CONTAINER;

		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
		
	}

	private EgovMap item;
	 
	
	protected EgovMap getTreeItem() {
		return item;
	}

	public void setTreeItem(Object item) {
		if (item instanceof EgovMap) {
			this.item = (EgovMap)item;
		}
	}
	 
	
	abstract protected String getId();
	
	abstract protected void addId(Object value);
	
	abstract protected String getName();
	
	abstract protected void addName(Object value);
	
	abstract protected int getDepth();
	
	abstract protected void addDepth(Object value);
	
	abstract protected String getUri();
	
	abstract protected void addUri(Object value);
	
	abstract protected boolean isContainer();
	
	abstract protected void addContainer(Object value);

	public Object get(TreeElement element) {
		Object value = null;
		
		switch (element) {
		case ID:
			value = getId();
			break;
		case NAME:
			value = getName();
			break;
		case DEPTH:
			value = getDepth();
			break;
		case URI:
			value = getUri();
			break;
		case CONTAINER:
			value = isContainer();
			break;
		default :
			break;
		}
		
		return value;
	}
	
	public void toTreeAttributes(List<?> items) {
		
		if (items == null || items.size() == 0 || !(items.get(0) instanceof EgovMap)) {
			return;
		}

		int compensator = -999;
		
		for (Object item : items) {

			setTreeItem(item);
			
			if (compensator == -999) {
				compensator = -(Integer.valueOf(get(TreeElement.DEPTH).toString()));
			}
			
			TreeElement[] elements = TreeElement.values();
			for (TreeElement element : elements) {
				put(element, get(element), compensator, (EgovMap)item);
			}
		}
	}

	private void put(TreeElement element, Object value, int compensator, EgovMap item) {
		Object value2 = value;
		if (item != null) {
			switch (element) { 
			
			case DEPTH:
				value2 = Integer.valueOf(value2.toString()) + compensator;
				break;
			
			default : break;
			}
			
			item.put(element.name(), value2);

		} else {
			switch (element) {
			case ID:
				addId(value2);
				break;
			case NAME:
				addName(value2);
				break;
			case DEPTH:
				int depth = Integer.valueOf(value2.toString()) + compensator;
				addDepth(depth);
				break;
			case URI:
				addUri(value2);
				break;
			case CONTAINER:
				addContainer(value2);
				break;
			default :
				break;
			}
		}
	}

}
