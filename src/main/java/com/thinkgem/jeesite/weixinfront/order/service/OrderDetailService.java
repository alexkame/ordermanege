/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetail;
import com.thinkgem.jeesite.weixinfront.order.dao.OrderDetailDao;

/**
 * 订单明细Service
 * @author janson
 * @version 2016-04-25
 */
@Service
@Transactional(readOnly = true)
public class OrderDetailService extends CrudService<OrderDetailDao, OrderDetail> {

	public OrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<OrderDetail> findList(OrderDetail orderDetail) {
		return super.findList(orderDetail);
	}
	
	public Page<OrderDetail> findPage(Page<OrderDetail> page, OrderDetail orderDetail) {
		return super.findPage(page, orderDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderDetail orderDetail) {
		super.save(orderDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderDetail orderDetail) {
		super.delete(orderDetail);
	}
	
}