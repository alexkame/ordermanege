package com.thinkgem.jeesite.weixinfront.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.pojo.WeixinOauth2Token;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixin.util.AdvancedUtil;

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
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {
		System.out.println("WeixinIndexController.index()");
		
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		if(weixinUserInfo!=null){
			WeixinUserInfo oldWeixinUserInfo=weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
			Date now=new Date();
			if(oldWeixinUserInfo==null){
				weixinUserInfo.setLastLoginTime(now);
				weixinUserInfo.setIsNewRecord(true);
				weixinUserInfoService.save(weixinUserInfo);
			}else{
				oldWeixinUserInfo.setLastLoginTime(now);
				weixinUserInfoService.save(oldWeixinUserInfo);
			}
		}
		return "redirect:/weixin/front/index.html";
	}

	/**
	 * 微信授权
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
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index";
	}

}
