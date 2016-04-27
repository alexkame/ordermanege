package com.thinkgem.jeesite.weixinfront.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @throws Exception 
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) throws Exception {
		System.out.println("WeixinIndexController.index()");
		
		String weixinUserinfoString=(String) request.getSession().getAttribute("weixinUserinfoString");
		weixinUserinfoString = AESSessionUtil.aesDecrypt(weixinUserinfoString);
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) JsonMapper.fromJsonString(weixinUserinfoString, WeixinUserInfo.class);
		if(weixinUserInfo!=null){
			WeixinUserInfo oldWeixinUserInfo=weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
			Date now=new Date();
			if(oldWeixinUserInfo==null){
				weixinUserInfo.setLastLoginTime(now);
				weixinUserInfo.setIsNewRecord(true);
				weixinUserInfoService.save(weixinUserInfo);
				//request.getSession().setAttribute("weixinUserInfo", weixinUserInfo);
			}else{
				oldWeixinUserInfo.setLastLoginTime(now);
				weixinUserInfoService.save(oldWeixinUserInfo);
				//request.getSession().setAttribute("weixinUserInfo", oldWeixinUserInfo);
			}
		}
		return "redirect:/weixin/front/index.html";
	}

	/**
	 * 微信授权
	 */
	@RequestMapping(value = "oAuth")
	public String oAuth(HttpServletRequest request, String code) {
		String weixinUserinfoString = "";
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
				//request.getSession().setAttribute("weixinUserInfo", weixinUserinfo);
				weixinUserinfoString=JsonMapper.toJsonString(weixinUserinfo);
				weixinUserinfoString = AESSessionUtil.aesEncrypt(weixinUserinfoString);
				request.getSession().setAttribute("weixinUserinfoString",weixinUserinfoString);

				logger.error("WeixinIndexController oAuth openId-->{}", openId);
			}
		} catch (Exception e) {
			logger.error("WeixinIndexController oAuth error-->{}", e.getMessage());
		}
		return "redirect:" + Global.getWeixinPath() + "/weixinIndex/index";
	}

}
