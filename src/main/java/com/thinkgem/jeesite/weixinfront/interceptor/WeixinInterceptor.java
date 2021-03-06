/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;

/**
 * 微信拦截器
 * 
 * @author yaominginfo
 *
 */
public class WeixinInterceptor extends BaseService implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("微信地址拦截 url : {}", request.getRequestURI());
		logger.info("微信地址拦截 model : {}", request.getMethod());
		String type = request.getParameter("type");
		WeixinUserInfo weixinUserInfo = (WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		if (weixinUserInfo == null) {
			logger.info("还没登录，被拦截！");
			String urlbegin = "";
			String urlcontent = "";
			String urlend = "";
			if (type.equals("admin")) {
				urlbegin = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinGlobal.getAppid()
						+ "&redirect_uri=" + Global.getSystemUrl();
				urlcontent = Global.getWeixinPath() + "/weixinIndex/oAuthAdmin";
				urlend = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			} else if (type.equals("userinfo")) {
				urlbegin = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinGlobal.getAppid()
						+ "&redirect_uri=" + Global.getSystemUrl();
				urlcontent = Global.getWeixinPath() + "/weixinIndex/oAuthUserinfo";
				urlend = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			} else {
				urlbegin = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinGlobal.getAppid()
						+ "&redirect_uri=" + Global.getSystemUrl();
				urlcontent = Global.getWeixinPath() + "/weixinIndex/oAuth";
				urlend = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}
			response.sendRedirect(urlbegin + urlcontent + urlend);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
