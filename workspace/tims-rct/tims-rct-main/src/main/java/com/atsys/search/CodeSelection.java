/*************************************************************
  프로그램명 : CodeSelection.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atsys.nxf.app.vo.BaseBean;
import com.atsys.tims.sys.constants.CodeCollsConstants.UseStatus;

/**
 * @Class Name : CodeSelection.java
 * @Description : CodeSelection class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class CodeSelection extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549880261768173862L;
	
	private String id;	// 화면에서 접근할 코드목록 id
	private String cls;	// 해당 코드를 조회할 테이블 id
	private String cdg;	// 대구분코드
    private List<String> inc;	// 포함할 소구분 코드[]
    private List<String> exc;	// 제외할 소구분 코드[]
    private String use = UseStatus.ALL.getUse();	// useYn 조건 :  A(모두 검색), Y(useYn = 'Y'), N(useYn = 'N')
    private List<OrderSo> sorts;	// 코드 표시순서 목록 : ordering 컬럼
	private List<RelationSo> rels;	// 기타 매칭 조검 기술
	private String cdg2;	// 대구분코드1
	private String cond;	// 제약조건
	private String smd;	// 소구분코드
	private Map<String, Object> params = new HashMap<String, Object>();
	private List<String> sctr;	// 포함할 조직 코드[]
	
	public CodeSelection(){
		inc = new ArrayList<String>();
		exc = new ArrayList<String>();
		sorts = new ArrayList<OrderSo>();
		rels = new ArrayList<RelationSo>();
		sctr = new ArrayList<String>();
	};
	
	public CodeSelection(String id, String cls, String cdg){
		this();
		this.id = id;
		this.cls = cls;
		this.cdg = cdg;
	};
	
	public CodeSelection(String id, String cls, String cdg, String[] exc){
		this(id, cls, cdg);
		this.exc = Arrays.asList(exc);
	};
	
	public CodeSelection(String id, String cls, String cdg, String[] inc, String[] exc){
		this(id, cls, cdg, exc);
		this.inc = Arrays.asList(inc);
	};

	public CodeSelection(String id, String cls, String cdg, String[] inc, String[] exc, String[] sctr){
		this(id, cls, cdg, exc);
		this.inc = Arrays.asList(inc);
		this.sctr = Arrays.asList(sctr);
	};
	
	public CodeSelection(String id, String cls, String cdg, OrderSo[] sorts, RelationSo[] rels){
		this(id, cls, cdg, sorts);
		this.rels = Arrays.asList(rels);
	};
	
	public CodeSelection(String id, String cls, String cdg, OrderSo[] sorts){
		this(id, cls, cdg);
		this.sorts = Arrays.asList(sorts);
	};
	
	public CodeSelection(String id, String cls, String cdg, RelationSo[] rels){
		this(id, cls, cdg);
		this.rels = Arrays.asList(rels);
	};
	
	public CodeSelection(String id, String cls, String cdg, String cdg2){
		this(id, cls, cdg);
		this.cdg2 = cdg2;
	};
	
	public CodeSelection(String id, String cls, String cdg, Map<String, Object> params){
		this(id, cls, cdg);
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.putAll(params);
		this.params = params2;
	};
	
	public CodeSelection(String id, String cls, String cdg, OrderSo[] sorts, String[] inc, String[] exc){
		this(id, cls, cdg, inc, exc);
		this.sorts = Arrays.asList(sorts);
	};
	
	public CodeSelection(String id, String cls, String cdg, OrderSo[] sorts, String[] inc, String[] exc, RelationSo[] rels){
		this(id, cls, cdg, sorts, inc, exc);
		this.rels = Arrays.asList(rels);
	};
	
	public CodeSelection(String id, String cls, String cdg, String cdg2, OrderSo[] sorts, String[] inc, String[] exc, RelationSo[] rels){
		this(id, cls, cdg, sorts, inc, exc, rels);
		this.cdg2 = cdg2;
	};
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getCdg() {
		return cdg;
	}

	public void setCdg(String cdg) {
		this.cdg = cdg;
	}

	public List<String> getInc() {
		List<String> ret = null;
		if(this.inc != null) {
			ret = new ArrayList<String>();
			ret = this.inc; 
		}
		return ret;
//		return inc;
	}

	public void setInc(List<String> inc) {
		if(inc != null) {
			this.inc = new ArrayList<String>();
			this.inc.addAll(inc);
		}
//		this.inc = inc;
	}

	public List<String> getExc() {
		List<String> ret = null;
		if(this.exc != null) {
			ret = new ArrayList<String>();
			ret = this.exc; 
		}
		return ret;
//		return exc;
	}

	public void setExc(List<String> exc) {
		if(exc != null) {
			this.exc = new ArrayList<String>();
			this.exc.addAll(exc);
		}
//		this.exc = exc;
	}

	public List<String> getSctr() {
		List<String> ret = null;
		if(this.sctr != null) {
			ret = new ArrayList<String>();
			ret = this.sctr; 
		}
		return ret;
	}

	public void setSctr(List<String> sctr) {
		if(sctr != null) {
			this.sctr = new ArrayList<String>();
			this.sctr.addAll(sctr);
		}
	}
	
	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public List<OrderSo> getSorts() {
		List<OrderSo> ret = null;
		if(this.sorts != null) {
			ret = new ArrayList<OrderSo>();
			ret = this.sorts; 
		}
		return ret;
//		return sorts;
	}

	public void setSorts(List<OrderSo> sorts) {
		if(sorts != null) {
			this.sorts = new ArrayList<OrderSo>();
			this.sorts.addAll(sorts);
		}
//		this.sorts = sorts;
	}

	public List<RelationSo> getRels() {
		List<RelationSo> ret = null;
		if(this.rels != null) {
			ret = new ArrayList<RelationSo>();
			ret = this.rels; 
		}
		return ret;
//		return rels;
	}

	public void setRels(List<RelationSo> rels) {
		if(rels != null) {
			this.rels = new ArrayList<RelationSo>();
			this.rels.addAll(rels);
		}
//		this.rels = rels;
	}

	public String getCdg2() {
		return cdg2;
	}

	public void setCdg2(String cdg2) {
		this.cdg2 = cdg2;
	}

	public Map<String, Object> getParams() {
		Map<String, Object> ret = null;
		if(this.params != null) {
			ret = new HashMap<>();
			ret = this.params; 
		}
		return ret;
	}

	public void setParams(Map<String, Object> params) {
		if(params != null) {
			this.params = new HashMap<>();
			this.params.putAll(params);
		}
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getSmd() {
		return smd;
	}

	public void setSmd(String smd) {
		this.smd = smd;
	}
	
}
