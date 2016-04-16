/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.web;

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
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinUserInfoService;

/**
 * 微信粉丝信息Controller
 * @author janson
 * @version 2016-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/system/weixinUserInfo")
public class WeixinUserInfoController extends BaseController {

	@Autowired
	private WeixinUserInfoService weixinUserInfoService;
	
	@ModelAttribute
	public WeixinUserInfo get(@RequestParam(required=false) String id) {
		WeixinUserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinUserInfoService.get(id);
		}
		if (entity == null){
			entity = new WeixinUserInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("system:weixinUserInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinUserInfo weixinUserInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinUserInfo> page = weixinUserInfoService.findPage(new Page<WeixinUserInfo>(request, response), weixinUserInfo); 
		model.addAttribute("page", page);
		return "weixin/system/weixinUserInfoList";
	}

	@RequiresPermissions("system:weixinUserInfo:view")
	@RequestMapping(value = "form")
	public String form(WeixinUserInfo weixinUserInfo, Model model) {
		model.addAttribute("weixinUserInfo", weixinUserInfo);
		return "weixin/system/weixinUserInfoForm";
	}

	@RequiresPermissions("system:weixinUserInfo:edit")
	@RequestMapping(value = "save")
	public String save(WeixinUserInfo weixinUserInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinUserInfo)){
			return form(weixinUserInfo, model);
		}
		weixinUserInfoService.save(weixinUserInfo);
		addMessage(redirectAttributes, "保存微信粉丝信息成功");
		return "redirect:"+Global.getAdminPath()+"/system/weixinUserInfo/?repage";
	}
	
	@RequiresPermissions("system:weixinUserInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinUserInfo weixinUserInfo, RedirectAttributes redirectAttributes) {
		weixinUserInfoService.delete(weixinUserInfo);
		addMessage(redirectAttributes, "删除微信粉丝信息成功");
		return "redirect:"+Global.getAdminPath()+"/system/weixinUserInfo/?repage";
	}

}