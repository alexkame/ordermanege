package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.weixinfront.admin.service.WeixinAdminUserService;

@Controller
@RequestMapping(value = "/weixinadmin/adminUser")
public class WeixinAdminLoginController {
	
	@Autowired
	WeixinAdminUserService weixinAdminUserService;
	
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String,Object> login(String userName,String password,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			result.putAll(weixinAdminUserService.weixinLogin(userName,password,request));
			result.put("code", 1);
			result.put("message", "登陆成功");
		} catch (Exception e) {
			result.put("code", 900);
			result.put("message", "系统出错！");
		}
		return result;
	}

}
