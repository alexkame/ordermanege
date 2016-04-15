/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.pojo.WeixinOauth2Token;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinInfoService;
import com.thinkgem.jeesite.weixin.util.AdvancedUtil;
import com.thinkgem.jeesite.weixinfront.entity.WeixinUserInfo;

/**
 * 微信首页
 * 
 * @author yaominginfo
 *
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixinIndex")
public class WeixinIndexController extends BaseController {

	@Autowired
	WeixinInfoService weixinInfoService;

	/**
	 * 微信首页
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {

		return "redirect:/weixin/front/index.html";
	}

	/**
	 * 微信授权
	 */
	@RequestMapping(value = "oAuth")
	public String oAuth(HttpServletRequest request, String code) {

		try {
			logger.error("WeixinIndexController oAuth code-->", code);
			// 用户同意授权
			if (!"authdeny".equals(code)) {

				// 获取微信信息
				WeixinInfo weixininfo = weixinInfoService.findList(new WeixinInfo()).get(0);

				// 获取网页授权access_token
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(weixininfo.getAppid(),
						weixininfo.getAppsecret(), code);
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();

				// 获取用户信息
				WeixinUserInfo weixinUserinfo = AdvancedUtil.getWeixinUserinfo(accessToken, openId);

				// 添加session
				request.getSession().setAttribute("weixinUserInfo", weixinUserinfo);

				logger.error("WeixinIndexController oAuth openId-->", openId);
			}
		} catch (Exception e) {
			logger.error("WeixinIndexController oAuth-->", e.getMessage());
		}
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index";
	}

}
