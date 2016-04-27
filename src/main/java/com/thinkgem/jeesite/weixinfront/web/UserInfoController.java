package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixin.util.AESSessionUtil;

@Controller
@RequestMapping(value = "${weixinPath}/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private WeixinUserInfoService weixinUserInfoService;

	@ModelAttribute
	public WeixinUserInfo get(@RequestParam(required = false) String id) {
		WeixinUserInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = weixinUserInfoService.get(id);
		}
		if (entity == null) {
			entity = new WeixinUserInfo();
		}
		return entity;
	}

	@RequestMapping(value = { "myInfo" })
	@ResponseBody
	public WeixinUserInfo myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String weixinUserinfoString=(String) request.getSession().getAttribute("weixinUserinfoString");
		weixinUserinfoString = AESSessionUtil.aesDecrypt(weixinUserinfoString);
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) JsonMapper.fromJsonString(weixinUserinfoString, WeixinUserInfo.class);
		WeixinUserInfo userInfo = null;
		if (weixinUserInfo != null) {
			userInfo = weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
		}
		return userInfo;
	}

	@RequestMapping(value = { "isFull" })
	@ResponseBody
	public Map<String,Object> isFull(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			WeixinUserInfo weixinUserInfo = (WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
			WeixinUserInfo userInfo = null;
			if (weixinUserInfo != null) {
				userInfo = weixinUserInfoService.isFull(weixinUserInfo.getOpenid());
			}
//			userInfo = weixinUserInfoService.isFull("oKYHCuE53KOOm2w5c32eg3_StJbA");
			if(userInfo != null){
				result.put("code", true);
			}else{
				result.put("code", false);
				result.put("message", "请先完善个人信息");
			}
		} catch (Exception e) {
			logger.error("weixinCustomer save error : {}", e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
}
