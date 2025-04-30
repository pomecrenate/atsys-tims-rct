package com.atsys.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atsys.base.util.Utilities;

public abstract class AbstractTreeVo extends BaseVo implements ITreeVo {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 8316221844275790561L;
	private List<ITreeVo> nodes = null;
	private String leafIcon ;
	private String folderIcon;
	private String color;
	private String backColor;
	private String href;
	private boolean selectable = true;
	private State state = new State();
	private List<String> tags;
	private ITreeVo parent;

	public AbstractTreeVo(){ 
		this.leafIcon = Utilities.getProperty("tims.icon.tree.leaf","fas fa-file"); 
		this.folderIcon = Utilities.getProperty("tims.icon.tree.folder","fas fa-align-justify");
	}
	public class State implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8364830645383836292L;
		private boolean checked = false;
		private boolean disabled = false;
		private boolean expanded = false;
		private boolean selected = false;

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isExpanded() {
			return expanded;
		}

		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}

	public static List<ITreeVo> makeHierarchy(List<? extends ITreeVo> list) {
		List<ITreeVo> ret = new ArrayList<ITreeVo>();
		if (Utilities.isEmpty(list))
			return ret;
		Map<String, ITreeVo> map = new HashMap<String, ITreeVo>();
		
		for (int i = 0; i < list.size(); i++) {
			ITreeVo vo = list.get(i);
			if (Utilities.isEmpty(vo.getId()))
				continue;
			map.put(vo.getId(), vo);
			if (Utilities.isEmpty(vo.getParentId())
					|| vo.getParentId().equals("0")) {
				ret.add(vo);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			ITreeVo vo = list.get(i);
			if (!map.containsKey(vo.getParentId()))
				continue;
			ITreeVo parent = map.get(vo.getParentId());
			if (parent == null)
				continue;
			parent.addChild(vo);
		}
		return ret;
	}
	
	@Override
	public void addChild(ITreeVo vo) {
		if(vo == null)
			return;
		List<ITreeVo> list = getNodes();
		if (list == null)
			list = createNodes();
		if (list == null)
			return;
		list.add(vo);
		vo.setParent(this);
	}

	public List<ITreeVo> createNodes() {
		setNodes(new ArrayList<ITreeVo>());
		return nodes;
	}

	@Override
	public List<ITreeVo> getNodes() {
		List<ITreeVo> ret = null;
		if(this.nodes != null) {
			ret = new ArrayList<ITreeVo>();
			ret = this.nodes; 
		}
		return ret;
//		return nodes;
	}

	public void setNodes(List<ITreeVo> nodes) {
		if(nodes != null) {
			this.nodes = new ArrayList<ITreeVo>();
			this.nodes.addAll(nodes);
		}
//		this.nodes = nodes;
	}

	public String getIcon() {
		List<ITreeVo> list = getNodes();
		if(Utilities.isEmpty(list))
			return getLeafIcon();
		else
			return getFolderIcon();
	}

	
	public String getSelectedIcon() {
		return getIcon();
	}
	public String getExpandIcon() {
		return getIcon();
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<String> getTags() {
		int cnt = getChildrenCount();
		if(cnt<1)
			return null;
		if(tags == null )
			tags = new ArrayList<String>();
		tags.clear();
		tags.add(cnt+"");
		return tags;
	}

	
	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	@Override
	public int getChildrenCount(){
		List<ITreeVo> list = getNodes();
		int ret = 0;
		for(int i=0;list!=null && i<list.size();i++){
			List<ITreeVo> l = list.get(i).getNodes();
			if(l==null || l.size()==0)
				ret++;
			else
				ret += list.get(i).getChildrenCount();
		}
		return ret;
	}

	public String getLeafIcon() {
		return leafIcon;
	}

	public void setLeafIcon(String leafIcon) {
		this.leafIcon = leafIcon;
	}

	public String getFolderIcon() {
		return folderIcon;
	}

	public void setFolderIcon(String folderIcon) {
		this.folderIcon = folderIcon;
	}

	@Override
	public ITreeVo parent() {
		return parent;
	}

	@Override
	public void setParent(ITreeVo parent) {
		this.parent = parent;
	}
	@Override
	public int getLevel(){
		
		ITreeVo p = parent();
		
		int level = 1;
		while(p!=null){
			level++;
			p=p.parent();
		}
		return level;
	}
}
