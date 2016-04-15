package com.thinkgem.jeesite.weixin.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.weixin.pojo.AccessToken;
import com.thinkgem.jeesite.weixin.pojo.JsapiTicket;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinInfoService;
import com.thinkgem.jeesite.weixin.util.AdvancedUtil;

@Service
@Lazy(false)
public class WeixinTask {

	/**
	 * 日志对象
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private WeixinInfoService weixinInfoService;

	/**
	 * 核心处理流程[定时器] 每隔2小时执行执行 更新Token
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void updateToken() {
		
		/**
		 * 查询微信信息列表
		 */
		List<WeixinInfo> weixininfoList = weixinInfoService.findList(new WeixinInfo());

		/**
		 * 遍历
		 */
		for (WeixinInfo weixininfo : weixininfoList) {
			AccessToken accessToken = AdvancedUtil.getAccessToken(weixininfo.getAppid(), weixininfo.getAppsecret());
			JsapiTicket jsapiTicket = AdvancedUtil.getJsapiTicket(accessToken);
			if (null != accessToken) {
				log.info(weixininfo.getWeixinid() + " 获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(),
						accessToken.getToken());
				log.info(weixininfo.getWeixinid() + " 获取jsapiTicket成功，有效时长{}秒 Ticket:{}", jsapiTicket.getExpiresIn(),
						jsapiTicket.getTicket());
				weixininfo.setAccesstoken(accessToken.getToken());
				weixininfo.setJsapiticket(jsapiTicket.getTicket());
				weixinInfoService.updateTokenAndTicket(weixininfo);
			}
		}
	}

}
