package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixinfront.util.Util;

/**
 * 微信客户信息
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixinCustomer")
public class WeixinCustomerController extends BaseController {

	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;

	/**
	 * 微信获取客户信息
	 */
	@RequestMapping(value = "admin/findALl")
	@ResponseBody
	public List<WeixinUserInfo> findALl(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return weixinUserInfoService.findList(new WeixinUserInfo());
	}

	/**
	 * 微信获取客户信息根据ID
	 */
	@RequestMapping(value = "admin/findById")
	@ResponseBody
	public WeixinUserInfo findById(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String params_log = Util.aesDecrypt(params);
		WeixinUserInfo weixinUserInfo = (WeixinUserInfo) JsonMapper.fromJsonString(params_log, WeixinUserInfo.class);
		return weixinUserInfoService.findList(weixinUserInfo).get(0);
	}

	/**
	 * 微信获取客户信息根据ID
	 */
	@RequestMapping(value = "admin/save")
	@ResponseBody
	public Map<String, Object> save(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinUserInfo weixinUserInfo = (WeixinUserInfo) JsonMapper.fromJsonString(params_log,
					WeixinUserInfo.class);
			WeixinUserInfo oldweixinUserInfo = weixinUserInfoService
					.findList(new WeixinUserInfo(weixinUserInfo.getId())).get(0);
			oldweixinUserInfo.setUsername(weixinUserInfo.getUsername());
			oldweixinUserInfo.setTel(weixinUserInfo.getTel());
			oldweixinUserInfo.setAddress(weixinUserInfo.getAddress());
			weixinUserInfoService.save(oldweixinUserInfo);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("weixinCustomer save error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

}
