package com.thinkgem.jeesite.weixinfront.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixinfront.order.entity.Ordertable;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetailSave;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderTableDetail;
import com.thinkgem.jeesite.weixinfront.order.service.OrdertableService;
import com.thinkgem.jeesite.weixinfront.util.Util;

/**
 * 微信订单表
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixinOrder")
public class WeixinOrderController extends BaseController {
	
	@Autowired
	private OrdertableService ordertableService;

	/**
	 * 微信首页订单
	 * @throws Exception 
	 */
	@RequestMapping(value = "findALlByUser")
	@ResponseBody
	public List<Ordertable> findALlByUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println(JsonMapper.toJsonString(ordertableService.findALlByUser(request)));
		return ordertableService.findALlByUser(request);
	}
	
	/**
	 * 管理员未完成订单
	 */
	@RequestMapping(value = "admin/undoneOrder")
	@ResponseBody
	public List<Ordertable> undoneOrder(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return ordertableService.findAdminUndoneOrder();
	}
	/**
	 * 管理员完成订单
	 */
	@RequestMapping(value = "admin/doneOrder")
	@ResponseBody
	public List<Ordertable> doneOrder(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return ordertableService.findAdmindoneOrder();
	}
	/**
	 *获取根据时间所有订单明细
	 */
	@RequestMapping(value = "admin/getOrderByBeginAndEnd")
	@ResponseBody
	public Map<String,Object> getOrderByBeginAndEnd(String params,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String params_log = Util.aesDecrypt(params);
		Map<String,String> datemap=(Map<String, String>) JsonMapper.fromJsonString(params_log, Map.class);
		return ordertableService.getOrderByBeginAndEnd(datemap);
	}
	
	
	/**
	 * 微信获取客户信息根据ID
	 */
	@RequestMapping(value = "admin/delete")
	@ResponseBody
	public Map<String, Object> delete(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			Ordertable ordertable = (Ordertable) JsonMapper.fromJsonString(params_log,
					Ordertable.class);
			ordertableService.delete(ordertable);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("weixinOrder delete error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	/**
	 * 根据订单ID作废
	 */
	@RequestMapping(value = "admin/cancel")
	@ResponseBody
	public Map<String, Object> cancel(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			Ordertable ordertable = (Ordertable) JsonMapper.fromJsonString(params_log,
					Ordertable.class);
			Ordertable oldordertable=ordertableService.get(ordertable);
			oldordertable.setStatus(3L);
			oldordertable.setReason(ordertable.getReason());
			ordertableService.save(oldordertable);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("weixinOrder cancel error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	/**
	 * 根据订单ID发货
	 */
	@RequestMapping(value = "admin/deliver")
	@ResponseBody
	public Map<String, Object> deliver(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			Ordertable ordertable = (Ordertable) JsonMapper.fromJsonString(params_log,
					Ordertable.class);
			Ordertable oldordertable=ordertableService.get(ordertable);
			oldordertable.setStatus(2L);
			oldordertable.setDeliveryTime(ordertable.getDeliveryTime());
			ordertableService.save(oldordertable);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("weixinOrder cancel error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	
	/**
	 * 保存订单
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			OrderDetailSave orderDetailSave=(OrderDetailSave) JsonMapper.fromJsonString(params_log,
					OrderDetailSave.class);
			//System.out.println(JsonMapper.toJsonString(orderDetailSave));
			ordertableService.saveOrder(orderDetailSave,request);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("weixinOrder save error : {}",e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}
	
	
	/**
	 * 根据ID查询订单
	 */
	@RequestMapping(value = "findById")
	@ResponseBody
	public OrderTableDetail findById(String params,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String params_log = Util.aesDecrypt(params);
		OrderTableDetail orderTableDetail=(OrderTableDetail) JsonMapper.fromJsonString(params_log,
				OrderTableDetail.class);
		System.out.println(JsonMapper.toJsonString(ordertableService.findOrderTableDetailById(orderTableDetail.getId())));
		return ordertableService.findOrderTableDetailById(orderTableDetail.getId());
	}
}
