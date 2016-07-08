package com.thinkgem.jeesite.weixinfront.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.pojo.WeixinOauth2Token;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixin.util.AESSessionUtil;
import com.thinkgem.jeesite.weixin.util.AdvancedUtil;
import com.thinkgem.jeesite.weixinfront.util.AESUtil;
import com.thinkgem.jeesite.weixinfront.util.Util;

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
	private WeixinUserInfoService weixinUserInfoService;

	/**
	 * 微信首页
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request, String type) throws Exception {
		System.out.println("WeixinIndexController.index()");

		WeixinUserInfo weixinUserInfo = (WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		if (weixinUserInfo != null) {
			WeixinUserInfo oldWeixinUserInfo = weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
			Date now = new Date();
			if (oldWeixinUserInfo == null) {
				weixinUserInfo.setLastLoginTime(now);
				weixinUserInfoService.save(weixinUserInfo);
			} else {
				oldWeixinUserInfo.setLastLoginTime(now);
				weixinUserInfoService.save(oldWeixinUserInfo);
			}
		}
		if ("userinfo".equals(type)) {
			return "redirect:/weixinfront/front/index.html#/myInfo";
		} else if ("admin".equals(type)) {
			return "redirect:/weixinfront/front/index.html#/admin/adminLogin";
		} else {
			return "redirect:/weixinfront/front/index.html";
		}
	}

	/**
	 * 微信授权首页
	 */
	@RequestMapping(value = "oAuth")
	public String oAuth(HttpServletRequest request, String code) {
		try {
			logger.info("WeixinIndexController oAuth code-->{}", code);
			// 用户同意授权
			if (!"authdeny".equals(code)) {

				// 获取网页授权access_token
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(WeixinGlobal.getAppid(),
						WeixinGlobal.getAppsecret(), code);
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();

				// 获取用户信息
				WeixinUserInfo weixinUserinfo = AdvancedUtil.getWeixinUserinfo(accessToken, openId);

				// 添加session
				request.getSession().setAttribute("weixinUserInfo", weixinUserinfo);
				logger.error("WeixinIndexController oAuth openId-->{}", openId);
			}
		} catch (Exception e) {
			logger.error("WeixinIndexController oAuth error-->{}", e.getMessage());
		}
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index?type=index";
	}

	/**
	 * 微信授权管理员
	 */
	@RequestMapping(value = "oAuthAdmin")
	public String oAuthAdmin(HttpServletRequest request, String code) {
		try {
			logger.info("WeixinIndexController oAuthAdmin code-->{}", code);
			// 用户同意授权
			if (!"authdeny".equals(code)) {

				// 获取网页授权access_token
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(WeixinGlobal.getAppid(),
						WeixinGlobal.getAppsecret(), code);
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();

				// 获取用户信息
				WeixinUserInfo weixinUserinfo = AdvancedUtil.getWeixinUserinfo(accessToken, openId);

				// 添加session
				request.getSession().setAttribute("weixinUserInfo", weixinUserinfo);
				logger.error("WeixinIndexController oAuthAdmin openId-->{}", openId);
			}
		} catch (Exception e) {
			logger.error("WeixinIndexController oAuthAdmin error-->{}", e.getMessage());
		}
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index?type=admin";
	}

	/**
	 * 微信授权用户信息
	 */
	@RequestMapping(value = "oAuthUserinfo")
	public String oAuthUserinfo(HttpServletRequest request, String code) {
		try {
			logger.info("WeixinIndexController oAuthUserinfo code-->{}", code);
			// 用户同意授权
			if (!"authdeny".equals(code)) {

				// 获取网页授权access_token
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(WeixinGlobal.getAppid(),
						WeixinGlobal.getAppsecret(), code);
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();

				// 获取用户信息
				WeixinUserInfo weixinUserinfo = AdvancedUtil.getWeixinUserinfo(accessToken, openId);

				// 添加session
				request.getSession().setAttribute("weixinUserInfo", weixinUserinfo);

				logger.error("WeixinIndexController oAuthUserinfo openId-->{}", openId);
			}
		} catch (Exception e) {
			logger.error("WeixinIndexController oAuthUserinfo error-->{}", e.getMessage());
		}
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index?type=userinfo";
	}
}
