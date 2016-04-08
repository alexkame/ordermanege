/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinInfoService;
import com.thinkgem.jeesite.weixinfront.entity.WeixinUserInfo;

/**
 * 微信拦截器
 * @author yaominginfo
 *
 */
public class WeixinInterceptor extends BaseService implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		System.out.println("微信地址拦截");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("微信地址拦截modelAndView");
		
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		if(weixinUserInfo==null){
			
			WeixinInfoService weixinInfoService= SpringContextHolder.getBean(WeixinInfoService.class);
			WeixinInfo weixinInfo= weixinInfoService.findList(new WeixinInfo()).get(0);
			String urlbegin = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ weixinInfo.getAppid()
					+ "&redirect_uri="
					+ "http://120.24.179.160/ordermanage";
			String urlend = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			
			
			modelAndView.setViewName("redirect:http://www.baidu.com");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
