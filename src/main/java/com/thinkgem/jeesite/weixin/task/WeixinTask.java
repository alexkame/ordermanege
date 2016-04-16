package com.thinkgem.jeesite.weixin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.pojo.AccessToken;
import com.thinkgem.jeesite.weixin.pojo.JsapiTicket;
import com.thinkgem.jeesite.weixin.util.AdvancedUtil;

@Service
@Lazy(false)
public class WeixinTask {

	/**
	 * 日志对象
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 核心处理流程[定时器] 每隔2小时执行执行 更新Token
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void updateToken() {

		/**
		 * 遍历
		 */
		AccessToken accessToken = AdvancedUtil.getAccessToken(WeixinGlobal.getAppid(), WeixinGlobal.getAppsecret());
		JsapiTicket jsapiTicket = AdvancedUtil.getJsapiTicket(accessToken);
		if (null != accessToken) {
			log.info(" 获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());
			log.info(" 获取jsapiTicket成功，有效时长{}秒 Ticket:{}", jsapiTicket.getExpiresIn(), jsapiTicket.getTicket());
			WeixinGlobal.accessToken = accessToken.getToken();
			WeixinGlobal.jsapiTicket = jsapiTicket.getTicket();
		}
	}
}
