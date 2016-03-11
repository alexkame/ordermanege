/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;
import com.thinkgem.jeesite.weixin.system.dao.WeixinInfoDao;

/**
 * 微信信息Service
 * @author janson
 * @version 2016-03-11
 */
@Service
@Transactional(readOnly = true)
public class WeixinInfoService extends CrudService<WeixinInfoDao, WeixinInfo> {

	public WeixinInfo get(String id) {
		return super.get(id);
	}
	
	public List<WeixinInfo> findList(WeixinInfo weixinInfo) {
		return super.findList(weixinInfo);
	}
	
	public Page<WeixinInfo> findPage(Page<WeixinInfo> page, WeixinInfo weixinInfo) {
		return super.findPage(page, weixinInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinInfo weixinInfo) {
		super.save(weixinInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinInfo weixinInfo) {
		super.delete(weixinInfo);
	}

	public static boolean updateToken() {
		System.out.println("定时更新");
		
		return true;
	}
	
}