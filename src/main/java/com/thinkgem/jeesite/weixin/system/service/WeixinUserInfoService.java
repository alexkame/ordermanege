/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.dao.WeixinUserInfoDao;

/**
 * 微信粉丝信息Service
 * @author janson
 * @version 2016-04-16
 */
@Service
@Transactional(readOnly = true)
public class WeixinUserInfoService extends CrudService<WeixinUserInfoDao, WeixinUserInfo> {

	public WeixinUserInfo get(String id) {
		return super.get(id);
	}
	
	public List<WeixinUserInfo> findList(WeixinUserInfo weixinUserInfo) {
		return super.findList(weixinUserInfo);
	}
	
	public Page<WeixinUserInfo> findPage(Page<WeixinUserInfo> page, WeixinUserInfo weixinUserInfo) {
		return super.findPage(page, weixinUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinUserInfo weixinUserInfo) {
		super.save(weixinUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinUserInfo weixinUserInfo) {
		super.delete(weixinUserInfo);
	}

	public WeixinUserInfo findByOpenid(String openid) {
		return dao.findByOpenid(openid);
	}
	
}