package com.thinkgem.jeesite.weixinfront.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;

@Controller
@RequestMapping(value = "${weixinPath}/userInfo")
public class UserInfoController extends BaseController{
	
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;
	
	@ModelAttribute
	public WeixinUserInfo get(@RequestParam(required=false) String id) {
		WeixinUserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinUserInfoService.get(id);
		}
		if (entity == null){
			entity = new WeixinUserInfo();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"myInfo"})
	@ResponseBody
	public WeixinUserInfo myInfo(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
//		WeixinUserInfo weixinUserinfo=(WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		WeixinUserInfo oldweixinUserinfo=weixinUserInfoService.findByOpenid("oKYHCuE53KOOm2w5c32eg3_StJbA");
		return oldweixinUserinfo;
	}

}
