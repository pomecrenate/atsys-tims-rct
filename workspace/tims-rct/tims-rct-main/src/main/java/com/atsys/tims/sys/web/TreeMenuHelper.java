/*************************************************************
  프로그램명 : TreeMenuHelper.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.web;

import org.springframework.stereotype.Component;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.core.web.servlet.handler.util.AbstractTreeHelper;

@Component("treeHelper")
public class TreeMenuHelper extends AbstractTreeHelper {

	@Override
	protected String getId() {
		return getTreeItem().get("menuId").toString();
	}

	@Override
	protected void addId(Object value) {
		getTreeItem().put(TreeElement.ID.name(), value);

	}

	@Override
	protected String getName() {
		return getTreeItem().get("menuNm").toString();
	}

	@Override
	protected void addName(Object value) {
		getTreeItem().put(TreeElement.NAME.name(), value);
	}

	@Override
	protected int getDepth() {
		Object level = getTreeItem().get("menuLevel");
		try {
			return Integer.parseInt(level.toString());
		} catch (NumberFormatException e) {
			Utilities.trace(e);
		}
		
		return -2;
	}

	@Override
	protected void addDepth(Object value) {
		getTreeItem().put(TreeElement.DEPTH.name(), value);
	}

	@Override
	protected String getUri() {
		Object uri = getTreeItem().get("executeFile");
		if (uri == null) {
			return null;
		}
		
		return uri.toString();
	}

	@Override
	protected void addUri(Object value) {
		getTreeItem().put(TreeElement.URI.name(), value);
	}

	@Override
	protected boolean isContainer() {
		return getDepth() < 3;
	}

	@Override
	protected void addContainer(Object value) {
		getTreeItem().put(TreeElement.CONTAINER.name(), value);
	}

}
