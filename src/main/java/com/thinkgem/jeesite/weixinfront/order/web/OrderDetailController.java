/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.weixinfront.order.entity.OrderDetail;
import com.thinkgem.jeesite.weixinfront.order.service.OrderDetailService;

/**
 * 订单明细Controller
 * @author janson
 * @version 2016-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderDetail")
public class OrderDetailController extends BaseController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@ModelAttribute
	public OrderDetail get(@RequestParam(required=false) String id) {
		OrderDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderDetailService.get(id);
		}
		if (entity == null){
			entity = new OrderDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderDetail> page = orderDetailService.findPage(new Page<OrderDetail>(request, response), orderDetail); 
		model.addAttribute("page", page);
		return "weixinfront/order/orderDetailList";
	}

	@RequiresPermissions("order:orderDetail:view")
	@RequestMapping(value = "form")
	public String form(OrderDetail orderDetail, Model model) {
		model.addAttribute("orderDetail", orderDetail);
		return "weixinfront/order/orderDetailForm";
	}

	@RequiresPermissions("order:orderDetail:edit")
	@RequestMapping(value = "save")
	public String save(OrderDetail orderDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderDetail)){
			return form(orderDetail, model);
		}
		orderDetailService.save(orderDetail);
		addMessage(redirectAttributes, "保存订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderDetail/?repage";
	}
	
	@RequiresPermissions("order:orderDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderDetail orderDetail, RedirectAttributes redirectAttributes) {
		orderDetailService.delete(orderDetail);
		addMessage(redirectAttributes, "删除订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderDetail/?repage";
	}

}