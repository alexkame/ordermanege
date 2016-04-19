/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixinfront.order.dao.OrdertableDao;
import com.thinkgem.jeesite.weixinfront.order.entity.Ordertable;

/**
 * 订单表Service
 * @author janson
 * @version 2016-04-18
 */
@Service
@Transactional(readOnly = true)
public class OrdertableService extends CrudService<OrdertableDao, Ordertable> {

	public Ordertable get(String id) {
		return super.get(id);
	}
	
	public List<Ordertable> findList(Ordertable ordertable) {
		return super.findList(ordertable);
	}
	
	public Page<Ordertable> findPage(Page<Ordertable> page, Ordertable ordertable) {
		return super.findPage(page, ordertable);
	}
	
	@Transactional(readOnly = false)
	public void save(Ordertable ordertable) {
		super.save(ordertable);
	}
	
	@Transactional(readOnly = false)
	public void delete(Ordertable ordertable) {
		super.delete(ordertable);
	}

	public List<Ordertable> findALlByUser(HttpServletRequest request) {
		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		Ordertable ordertable=new Ordertable();
		ordertable.setWeixinUserInfoId(weixinUserInfo);
		return dao.findALlByUser(ordertable);
	}

	public List<Ordertable> findAdminUndoneOrder() {
		return dao.findAdminUndoneOrder();
	}
	
}