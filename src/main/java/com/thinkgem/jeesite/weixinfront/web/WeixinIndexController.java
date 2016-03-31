/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.utils.WiexinSignUtil;
import com.thinkgem.jeesite.weixin.service.WeixinService;

/**
 * 微信首页
 * @author yaominginfo
 *
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixinIndex")
public class WeixinIndexController extends BaseController {

	/**
	 * 微信首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {
		
		return "redirect:/weixin/front/index.html";
	}


}
