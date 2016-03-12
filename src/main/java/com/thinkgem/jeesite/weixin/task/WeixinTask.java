package com.thinkgem.jeesite.weixin.task;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.weixin.pojo.AccessToken;
import com.thinkgem.jeesite.weixin.pojo.JsapiTicket;
import com.thinkgem.jeesite.weixin.util.WeixinUtil;

@Service
@Lazy(false)
public class WeixinTask{

	/**
	 * 核心处理流程[定时器] 每隔2小时执行执行
	 * 更新Token
	 */
	@Scheduled(fixedRate = 1000*60*60)
    public void updateToken(){  
		AccessToken accessToken = null;
		JsapiTicket jsapiTicket = null;
		
		accessToken = WeixinUtil.getAccessToken(weixininfo.getAppid(),
				weixininfo.getAppsecret());
		jsapiTicket = WeixinUtil.getJsapiTicket(accessToken);
		if (null != accessToken) {
			log.info(weixininfo.getWeixinId()
					+ " 获取access_token成功，有效时长{}秒 token:{}",
					accessToken.getExpiresIn(), accessToken.getToken());
			log.info(weixininfo.getWeixinId()
					+ " 获取jsapiTicket成功，有效时长{}秒 Ticket:{}",
					jsapiTicket.getExpiresIn(), jsapiTicket.getTicket());
			weixininfo.setAccessToken(accessToken.getToken());
			weixininfo.setJsapiTicket(jsapiTicket.getTicket());
			weixininfoService.update(weixininfo);
			// 休眠7000秒
			Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
		} else {
			// 如果access_token为null，60秒后再获取
			Thread.sleep(60 * 1000);
		}
    }
   
}
