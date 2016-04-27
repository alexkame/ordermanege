/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;
import com.thinkgem.jeesite.weixin.util.AESSessionUtil;
import com.thinkgem.jeesite.weixinfront.order.dao.OrdertableDao;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetail;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetailSimple;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderTableDetail;
import com.thinkgem.jeesite.weixinfront.order.entity.Ordertable;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetailSave;

/**
 * 订单表Service
 * 
 * @author janson
 * @version 2016-04-25
 */
@Service
@Transactional(readOnly = true)
public class OrdertableService extends CrudService<OrdertableDao, Ordertable> {

	@Autowired
	WeixinUserInfoService weixinUserInfoService;
	@Autowired
	OrderDetailService orderDetailService;

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

	public List<Ordertable> findAdminUndoneOrder() {
		return dao.findAdminUndoneOrder();
	}

	public List<Ordertable> findALlByUser(HttpServletRequest request) throws Exception {
//		String weixinUserinfoString=(String) request.getSession().getAttribute("weixinUserinfoString");
//		weixinUserinfoString = AESSessionUtil.aesDecrypt(weixinUserinfoString);
//		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) JsonMapper.fromJsonString(weixinUserinfoString, WeixinUserInfo.class);
		WeixinUserInfo weixinUserInfo= (WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		WeixinUserInfo userInfo = null;
		if (weixinUserInfo != null) {
			userInfo = weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
		}
		return dao.findALlByUser(userInfo);
	}

	public List<Ordertable> findAdmindoneOrder() {
		return dao.findAdmindoneOrder();
	}
	
	@Transactional(readOnly = false)
	public void saveOrder(OrderDetailSave orderDetailSave, HttpServletRequest request) throws Exception {

		double totalSquare = 0;
		for (OrderDetailSimple detailSimple : orderDetailSave.getItems()) {
			totalSquare += Double.parseDouble(detailSimple.getTotalSquare());
		}

//		String weixinUserinfoString=(String) request.getSession().getAttribute("weixinUserinfoString");
//		weixinUserinfoString = AESSessionUtil.aesDecrypt(weixinUserinfoString);
//		WeixinUserInfo weixinUserInfo=(WeixinUserInfo) JsonMapper.fromJsonString(weixinUserinfoString, WeixinUserInfo.class);
		WeixinUserInfo weixinUserInfo= (WeixinUserInfo) request.getSession().getAttribute("weixinUserInfo");
		WeixinUserInfo userInfo = null;
		if (weixinUserInfo != null) {
			userInfo = weixinUserInfoService.findByOpenid(weixinUserInfo.getOpenid());
		}

		Ordertable ordertable = new Ordertable();
		ordertable.setWeixinUserInfoId(userInfo);
		ordertable.setStatus(1L);
		ordertable.setCreateTime(new Date());
		ordertable.setTotalSquare(totalSquare + "");
		ordertable.setOrderNum((orderDetailSave.getFits().size()+orderDetailSave.getItems().size())+"");
		save(ordertable);
		System.out.println(ordertable.getId());
		
		//保存详情
		for (OrderDetailSimple detailSimple : orderDetailSave.getItems()) {
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrderId(ordertable);
			orderDetail.setColor(detailSimple.getColor());
			orderDetail.setThickness(detailSimple.getThickness());
			orderDetail.setNodeNum(detailSimple.getNodeNum());
			orderDetail.setTableNum(detailSimple.getTableNum());
			orderDetail.setPieceLength(detailSimple.getPieceLength());
			orderDetail.setTotalSquare(detailSimple.getTotalSquare());
			orderDetail.setType(2L);
			orderDetailService.save(orderDetail);
		}
		for (OrderDetailSimple detailSimple : orderDetailSave.getFits()) {
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrderId(ordertable);
			orderDetail.setType(1L);
			orderDetail.setPartsInfoId(detailSimple.getMyFit());
			orderDetail.setNum(detailSimple.getNum());
			orderDetailService.save(orderDetail);
		}
	}

	public OrderTableDetail findOrderTableDetailById(String id) {
		return dao.findOrderTableDetailById(id);
	}

	public Map<String,Object> getOrderByBeginAndEnd(Map<String, String> datemap) {
		Map<String,Object> result=new HashMap<String, Object>();
		List<OrderTableDetail> tableDetails=dao.getOrderByBeginAndEnd(datemap);
		List<OrderDetail> items=new ArrayList<OrderDetail>();
		List<OrderDetail> fits=new ArrayList<OrderDetail>();
		for(OrderTableDetail tableDetail:tableDetails){
			for(OrderDetail detail:tableDetail.getFits()){
				fits.add(detail);
			}
			for(OrderDetail detail:tableDetail.getItems()){
				items.add(detail);
			}
		}
		result.put("fits", fits);
		result.put("items", items);
		return result;
	}
}