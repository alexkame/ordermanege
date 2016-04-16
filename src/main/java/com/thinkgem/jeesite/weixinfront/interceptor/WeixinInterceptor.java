/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;

/**
 * 微信拦截器
 * @author yaominginfo
 *
 */
public class WeixinInterceptor extends BaseService implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("微信地址拦截modelAndView");
		
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		if(weixinUserInfo==null){
			
			String urlbegin = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ WeixinGlobal.getAppid()
					+ "&redirect_uri="
					+ Global.getSystemUrl();
			String urlcontent=Global.getWeixinPath()+"/weixinIndex/oAuth";
			String urlend = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			System.out.println(urlbegin+urlcontent+urlend);
			modelAndView.setViewName("redirect:"+urlbegin+urlcontent+urlend);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
