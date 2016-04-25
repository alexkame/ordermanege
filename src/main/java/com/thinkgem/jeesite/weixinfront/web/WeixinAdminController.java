package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixinfront.admin.entity.WeixinAdminUser;
import com.thinkgem.jeesite.weixinfront.admin.service.WeixinAdminUserService;
import com.thinkgem.jeesite.weixinfront.util.Util;

import groovyjarjarasm.asm.commons.Method;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "${weixinPath}/adminUser")
public class WeixinAdminController extends BaseController{
	
	@Autowired
	WeixinAdminUserService weixinAdminUserService;
	
	@ModelAttribute
	public WeixinAdminUser get(@RequestParam(required=false) String id) {
		WeixinAdminUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinAdminUserService.get(id);
		}
		if (entity == null){
			entity = new WeixinAdminUser();
		}
		return entity;
	}
	
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String,Object> login(String params,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinAdminUser weixinAdminUser=(WeixinAdminUser) JsonMapper.fromJsonString(params_log, WeixinAdminUser.class);
			result.putAll(weixinAdminUserService.weixinLogin(weixinAdminUser.getUserName(),weixinAdminUser.getPassword(),request));
		} catch (Exception e) {
			result.put("code", 900);
			result.put("message", "系统出错！");
		}
		return result;
	}
	@RequestMapping(value = "islogin")
	@ResponseBody
	public Map<String,Object> islogin(String params,HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinAdminUser weixinAdminUser=(WeixinAdminUser) request.getSession().getAttribute("weixinAdminUser");
			if(weixinAdminUser==null){
				result.put("code", 0);
				result.put("message", "还未登录！");
			}else{
				result.put("code", 1);
				result.put("message", "已经登录！");
			}
		} catch (Exception e) {
			result.put("code", 900);
			result.put("message", "系统出错！");
		}
		return result;
	}
	
	/**
	 * 微信获取员工信息
	 */
	@RequestMapping(value = "findALl")
	@ResponseBody
	public List<WeixinAdminUser> findALl(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return weixinAdminUserService.findList(new WeixinAdminUser());
	}

	
	/**
	 * 保存信息
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinAdminUser weixinUserInfo = (WeixinAdminUser) JsonMapper.fromJsonString(params_log,
					WeixinAdminUser.class);
			weixinAdminUserService.save(weixinUserInfo);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("adminUser save error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	
	/**
	 * 删除员工信息
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinAdminUser weixinAdminUser = (WeixinAdminUser) JsonMapper.fromJsonString(params_log,
					WeixinAdminUser.class);
			weixinAdminUserService.delete(weixinAdminUser);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("adminUser delete error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	
	/**
	 * 微信获取员工信息根据ID
	 */
	@RequestMapping(value = "findById")
	@ResponseBody
	public WeixinAdminUser findById(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String params_log = Util.aesDecrypt(params);
		WeixinAdminUser weixinAdminUser = (WeixinAdminUser) JsonMapper.fromJsonString(params_log, WeixinAdminUser.class);
		return get(weixinAdminUser.getId());
	}
	
	/**
	 * 微信获取员工信息根据ID
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, Object> update(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			WeixinAdminUser weixinAdminUser = (WeixinAdminUser) JsonMapper.fromJsonString(params_log, WeixinAdminUser.class);
			WeixinAdminUser oldWeixinAdminUser=get(weixinAdminUser.getId());
			oldWeixinAdminUser.setUserName(weixinAdminUser.getUserName());
			oldWeixinAdminUser.setPassword(weixinAdminUser.getPassword());
			oldWeixinAdminUser.setName(weixinAdminUser.getName());
			oldWeixinAdminUser.setTel(weixinAdminUser.getTel());
			weixinAdminUserService.save(oldWeixinAdminUser);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("adminUser update error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

	
}
