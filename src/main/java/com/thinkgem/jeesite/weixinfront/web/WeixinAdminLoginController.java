package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixinfront.admin.entity.WeixinAdminUser;
import com.thinkgem.jeesite.weixinfront.admin.service.WeixinAdminUserService;
import com.thinkgem.jeesite.weixinfront.util.Util;

import groovyjarjarasm.asm.commons.Method;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "${weixinPath}/adminUser")
public class WeixinAdminLoginController extends BaseController{
	
	@Autowired
	WeixinAdminUserService weixinAdminUserService;
	
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

}
