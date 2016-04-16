/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.Servlets;

/**
 * 全局配置类
 * 
 * @author ThinkGem
 * @version 2014-06-25
 */
public class WeixinGlobal {

	/**
	 * 当前对象实例
	 */
	private static WeixinGlobal weixinGlobal = new WeixinGlobal();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("weixin.properties");

	/**
	 * 当前accessToken
	 */
	public static String accessToken = "";
	/**
	 * 当前jsapiTicket
	 */
	public static String jsapiTicket = "";

	/**
	 * 获取当前对象实例
	 */
	public static WeixinGlobal getInstance() {
		return weixinGlobal;
	}

	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	public static String getWeixinUrl() {
		return getConfig("weixinUrl");
	}
	
	public static String getToken() {
		return getConfig("token");
	}

	public static String getAppid() {
		return getConfig("appid");
	}

	public static String getAppsecret() {
		return getConfig("appsecret");
	}

	public static String getPartner() {
		return getConfig("partner");
	}

	public static String getPartnerkey() {
		return getConfig("partnerkey");
	}

}
