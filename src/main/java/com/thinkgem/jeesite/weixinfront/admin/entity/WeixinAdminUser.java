/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.admin.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;

/**
 * 微信管理员Entity
 * @author janson
 * @version 2016-04-17
 */
public class WeixinAdminUser extends DataEntity<WeixinAdminUser> {
	
	private static final long serialVersionUID = 1L;
	private WeixinUserInfo weisinUserInfo;		// 微信粉丝表ID 父类
	private String userName;		// 用户名
	private String password;		// 密码
	private String name;		// 姓名
	private String tel;		// 联系方式
	private Date lastLoginTime;		// 最后登录时间
	
	public WeixinAdminUser() {
		super();
	}

	public WeixinAdminUser(String id){
		super(id);
	}

	public WeixinAdminUser(WeixinUserInfo weisinUserInfo){
		this.weisinUserInfo = weisinUserInfo;
	}

	@Length(min=0, max=64, message="微信粉丝表ID长度必须介于 0 和 64 之间")
	public WeixinUserInfo getWeisinUserInfo() {
		return weisinUserInfo;
	}

	public void setWeisinUserInfo(WeixinUserInfo weisinUserInfo) {
		this.weisinUserInfo = weisinUserInfo;
	}
	
	@Length(min=1, max=64, message="用户名长度必须介于 1 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=64, message="密码长度必须介于 1 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}