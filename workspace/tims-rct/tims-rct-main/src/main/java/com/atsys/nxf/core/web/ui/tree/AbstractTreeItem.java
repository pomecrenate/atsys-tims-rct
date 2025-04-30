/*************************************************************
  프로그램명 : AbstractTreeItem.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.ui.tree;

import java.io.Serializable;

public abstract class AbstractTreeItem implements Serializable {
	private static final long serialVersionUID = -1342569575249879965L;
	
	private boolean isContainer;
	
	abstract public String getId();
	
	abstract public String getName();
	
	abstract public int getDepth();
	
	abstract public String getUri();
	
	public boolean isContainer() {
		return isContainer;
	}
	
	public void setContainer(boolean isContainer) {
		this.isContainer = isContainer;
	}

}
