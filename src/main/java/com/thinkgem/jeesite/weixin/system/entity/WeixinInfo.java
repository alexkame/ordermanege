/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信信息Entity
 * @author janson
 * @version 2016-03-11
 */
public class WeixinInfo extends DataEntity<WeixinInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 微信名
	private String weixinid;		// 微信号
	private String nickname;		// 微信昵称
	private String url;		// 微信URL
	private String token;		// 微信token
	private String appid;		// 微信appid
	private String appsecret;		// 微信appsecret
	private String accesstoken;		// 微信accessToken
	private String jsapiticket;		// 微信jspaiTicket
	private String partner;		// 微信商家账号
	private String partnerkey;		// 微信商家秘钥
	
	public WeixinInfo() {
		super();
	}

	public WeixinInfo(String id){
		super(id);
	}

	@Length(min=1, max=255, message="微信名长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="微信号长度必须介于 1 和 255 之间")
	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	
	@Length(min=1, max=255, message="微信昵称长度必须介于 1 和 255 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=1, max=255, message="微信URL长度必须介于 1 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=1, max=255, message="微信token长度必须介于 1 和 255 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=1, max=255, message="微信appid长度必须介于 1 和 255 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=1, max=255, message="微信appsecret长度必须介于 1 和 255 之间")
	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
	@Length(min=0, max=255, message="微信accessToken长度必须介于 0 和 255 之间")
	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
	@Length(min=0, max=255, message="微信jspaiTicket长度必须介于 0 和 255 之间")
	public String getJsapiticket() {
		return jsapiticket;
	}

	public void setJsapiticket(String jsapiticket) {
		this.jsapiticket = jsapiticket;
	}
	
	@Length(min=0, max=255, message="微信商家账号长度必须介于 0 和 255 之间")
	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	@Length(min=0, max=255, message="微信商家秘钥长度必须介于 0 和 255 之间")
	public String getPartnerkey() {
		return partnerkey;
	}

	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	
}