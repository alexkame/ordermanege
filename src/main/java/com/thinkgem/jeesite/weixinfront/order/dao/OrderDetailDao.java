/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetail;

/**
 * 订单明细DAO接口
 * @author janson
 * @version 2016-04-25
 */
@MyBatisDao
public interface OrderDetailDao extends CrudDao<OrderDetail> {
	
}