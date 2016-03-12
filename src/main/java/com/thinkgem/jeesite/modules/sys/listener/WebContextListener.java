package com.thinkgem.jeesite.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.weixin.system.service.WeixinInfoService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		
		//加载系统信息
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}

		return super.initWebApplicationContext(servletContext);
	}
}
