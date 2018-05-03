package com.wy.configure.pojo;
/*
 * 系统配置类
 */
public class Configure {
	private Integer cfid;
	private String name;
	private String val;
	private String remark;
	
	public Configure() {
		super();
	}

	public Integer getCfid() {
		return cfid;
	}

	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
