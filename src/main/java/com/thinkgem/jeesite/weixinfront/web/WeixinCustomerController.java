package com.thinkgem.jeesite.weixinfront.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixinfront.order.entity.Ordertable;

/**
 * 微信客户信息
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixinCustomer")
public class WeixinCustomerController extends BaseController {
	
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;

	/**
	 * 微信获取客户信息
	 */
	@RequestMapping(value = "admin/findALl",produces="application/json;charset=utf-8")
	@ResponseBody
	public List<WeixinUserInfo> findALl(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return weixinUserInfoService.findList(new WeixinUserInfo());
	}
	
}
