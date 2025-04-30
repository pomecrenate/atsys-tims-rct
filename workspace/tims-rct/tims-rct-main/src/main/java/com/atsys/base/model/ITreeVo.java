package com.atsys.base.model;

import java.util.List;

public interface ITreeVo {
	
	String getId();
	String getText();
	String getParentId();
	ITreeVo parent();
	void setParent(ITreeVo vo);
	int getLevel();
	List<ITreeVo> getNodes();
	void addChild(ITreeVo vo);
	int getChildrenCount();
}
