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
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;
import com.thinkgem.jeesite.weixin.system.service.WeixinInfoService;

/**
 * 微信信息Controller
 * @author janson
 * @version 2016-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/system/weixinInfo")
public class WeixinInfoController extends BaseController {

	@Autowired
	private WeixinInfoService weixinInfoService;
	
	@ModelAttribute
	public WeixinInfo get(@RequestParam(required=false) String id) {
		WeixinInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinInfoService.get(id);
		}
		if (entity == null){
			entity = new WeixinInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("system:weixinInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinInfo weixinInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinInfo> page = weixinInfoService.findPage(new Page<WeixinInfo>(request, response), weixinInfo); 
		model.addAttribute("page", page);
		return "weixin/system/weixinInfoList";
	}

	@RequiresPermissions("system:weixinInfo:view")
	@RequestMapping(value = "form")
	public String form(WeixinInfo weixinInfo, Model model) {
		if(weixinInfo.getId()==null){
			weixinInfo.setToken(IdGen.uuid());
			weixinInfo.setUrl(Global.getWeixinUrl());
		}
		model.addAttribute("weixinInfo", weixinInfo);
		return "weixin/system/weixinInfoForm";
	}

	@RequiresPermissions("system:weixinInfo:edit")
	@RequestMapping(value = "save")
	public String save(WeixinInfo weixinInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinInfo)){
			return form(weixinInfo, model);
		}
		weixinInfoService.save(weixinInfo);
		addMessage(redirectAttributes, "保存微信信息成功");
		return "redirect:"+Global.getAdminPath()+"/system/weixinInfo/?repage";
	}
	
	@RequiresPermissions("system:weixinInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinInfo weixinInfo, RedirectAttributes redirectAttributes) {
		weixinInfoService.delete(weixinInfo);
		addMessage(redirectAttributes, "删除微信信息成功");
		return "redirect:"+Global.getAdminPath()+"/system/weixinInfo/?repage";
	}

}