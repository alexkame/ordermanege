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
import com.thinkgem.jeesite.weixinfront.order.entity.Ordertable;
import com.thinkgem.jeesite.weixinfront.order.service.OrdertableService;

/**
 * 订单表Controller
 * @author janson
 * @version 2016-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/order/ordertable")
public class OrdertableController extends BaseController {

	@Autowired
	private OrdertableService ordertableService;
	
	@ModelAttribute
	public Ordertable get(@RequestParam(required=false) String id) {
		Ordertable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ordertableService.get(id);
		}
		if (entity == null){
			entity = new Ordertable();
		}
		return entity;
	}
	
	@RequiresPermissions("order:ordertable:view")
	@RequestMapping(value = {"list", ""})
	public String list(Ordertable ordertable, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Ordertable> page = ordertableService.findPage(new Page<Ordertable>(request, response), ordertable); 
		model.addAttribute("page", page);
		return "weixinfront/order/ordertableList";
	}

	@RequiresPermissions("order:ordertable:view")
	@RequestMapping(value = "form")
	public String form(Ordertable ordertable, Model model) {
		model.addAttribute("ordertable", ordertable);
		return "weixinfront/order/ordertableForm";
	}

	@RequiresPermissions("order:ordertable:edit")
	@RequestMapping(value = "save")
	public String save(Ordertable ordertable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ordertable)){
			return form(ordertable, model);
		}
		ordertableService.save(ordertable);
		addMessage(redirectAttributes, "保存订单表成功");
		return "redirect:"+Global.getAdminPath()+"/order/ordertable/?repage";
	}
	
	@RequiresPermissions("order:ordertable:edit")
	@RequestMapping(value = "delete")
	public String delete(Ordertable ordertable, RedirectAttributes redirectAttributes) {
		ordertableService.delete(ordertable);
		addMessage(redirectAttributes, "删除订单表成功");
		return "redirect:"+Global.getAdminPath()+"/order/ordertable/?repage";
	}

}