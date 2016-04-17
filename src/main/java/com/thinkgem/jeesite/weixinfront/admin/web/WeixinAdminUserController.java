/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.admin.web;

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
import com.thinkgem.jeesite.weixinfront.admin.entity.WeixinAdminUser;
import com.thinkgem.jeesite.weixinfront.admin.service.WeixinAdminUserService;

/**
 * 微信管理员Controller
 * @author janson
 * @version 2016-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/admin/weixinAdminUser")
public class WeixinAdminUserController extends BaseController {

	@Autowired
	private WeixinAdminUserService weixinAdminUserService;
	
	@ModelAttribute
	public WeixinAdminUser get(@RequestParam(required=false) String id) {
		WeixinAdminUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinAdminUserService.get(id);
		}
		if (entity == null){
			entity = new WeixinAdminUser();
		}
		return entity;
	}
	
	@RequiresPermissions("admin:weixinAdminUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinAdminUser weixinAdminUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinAdminUser> page = weixinAdminUserService.findPage(new Page<WeixinAdminUser>(request, response), weixinAdminUser); 
		model.addAttribute("page", page);
		return "weixinfront/admin/weixinAdminUserList";
	}

	@RequiresPermissions("admin:weixinAdminUser:view")
	@RequestMapping(value = "form")
	public String form(WeixinAdminUser weixinAdminUser, Model model) {
		model.addAttribute("weixinAdminUser", weixinAdminUser);
		return "weixinfront/admin/weixinAdminUserForm";
	}

	@RequiresPermissions("admin:weixinAdminUser:edit")
	@RequestMapping(value = "save")
	public String save(WeixinAdminUser weixinAdminUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinAdminUser)){
			return form(weixinAdminUser, model);
		}
		weixinAdminUserService.save(weixinAdminUser);
		addMessage(redirectAttributes, "保存微信管理员成功");
		return "redirect:"+Global.getAdminPath()+"/admin/weixinAdminUser/?repage";
	}
	
	@RequiresPermissions("admin:weixinAdminUser:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinAdminUser weixinAdminUser, RedirectAttributes redirectAttributes) {
		weixinAdminUserService.delete(weixinAdminUser);
		addMessage(redirectAttributes, "删除微信管理员成功");
		return "redirect:"+Global.getAdminPath()+"/admin/weixinAdminUser/?repage";
	}

}