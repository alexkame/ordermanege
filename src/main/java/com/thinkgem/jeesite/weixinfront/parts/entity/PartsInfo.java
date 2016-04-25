/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.parts.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配件Entity
 * @author janson
 * @version 2016-04-25
 */
public class PartsInfo extends DataEntity<PartsInfo> {
	
	private static final long serialVersionUID = 1L;
	private String picture;		// 图片
	private String name;		// 名称
	private String spec;		// 规格
	private String purpose;		// 用途
	
	public PartsInfo() {
		super();
	}

	public PartsInfo(String id){
		super(id);
	}

	@Length(min=0, max=200, message="图片长度必须介于 0 和 200 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Length(min=1, max=50, message="名称长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="规格长度必须介于 0 和 50 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=50, message="用途长度必须介于 0 和 50 之间")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
}