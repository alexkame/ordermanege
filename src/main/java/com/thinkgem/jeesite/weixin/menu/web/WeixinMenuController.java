/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.menu.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.weixin.config.WeixinGlobal;
import com.thinkgem.jeesite.weixin.menu.entity.WeixinMenu;
import com.thinkgem.jeesite.weixin.menu.pojo.Button;
import com.thinkgem.jeesite.weixin.menu.pojo.ComplexButton;
import com.thinkgem.jeesite.weixin.menu.pojo.Menu;
import com.thinkgem.jeesite.weixin.menu.pojo.ViewButton;
import com.thinkgem.jeesite.weixin.menu.service.WeixinMenuService;
import com.thinkgem.jeesite.weixin.util.WeixinUtil;

/**
 * 微信菜单Controller
 * @author janson
 * @version 2016-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/menu/weixinMenu")
public class WeixinMenuController extends BaseController {
	
	Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	private WeixinMenuService weixinMenuService;
	
	@ModelAttribute
	public WeixinMenu get(@RequestParam(required=false) String id) {
		WeixinMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinMenuService.get(id);
		}
		if (entity == null){
			entity = new WeixinMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("menu:weixinMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinMenu weixinMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<WeixinMenu> list = weixinMenuService.findList(weixinMenu); 
		model.addAttribute("list", list);
		return "weixin/menu/weixinMenuList";
	}

	@RequiresPermissions("menu:weixinMenu:view")
	@RequestMapping(value = "form")
	public String form(WeixinMenu weixinMenu, Model model) {
		if (weixinMenu.getParent()!=null && StringUtils.isNotBlank(weixinMenu.getParent().getId())){
			weixinMenu.setParent(weixinMenuService.get(weixinMenu.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(weixinMenu.getId())){
				WeixinMenu weixinMenuChild = new WeixinMenu();
				weixinMenuChild.setParent(new WeixinMenu(weixinMenu.getParent().getId()));
				List<WeixinMenu> list = weixinMenuService.findList(weixinMenu); 
				if (list.size() > 0){
					weixinMenu.setSort(list.get(list.size()-1).getSort());
					if (weixinMenu.getSort() != null){
						weixinMenu.setSort(weixinMenu.getSort() + 30);
					}
				}
			}
		}
		if (weixinMenu.getSort() == null){
			weixinMenu.setSort(30);
		}
		model.addAttribute("weixinMenu", weixinMenu);
		return "weixin/menu/weixinMenuForm";
	}

	@RequiresPermissions("menu:weixinMenu:edit")
	@RequestMapping(value = "save")
	public String save(WeixinMenu weixinMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinMenu)){
			return form(weixinMenu, model);
		}
		weixinMenuService.save(weixinMenu);
		addMessage(redirectAttributes, "保存微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/menu/weixinMenu/?repage";
	}
	
	@RequiresPermissions("menu:weixinMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinMenu weixinMenu, RedirectAttributes redirectAttributes) {
		weixinMenuService.delete(weixinMenu);
		addMessage(redirectAttributes, "删除微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/menu/weixinMenu/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<WeixinMenu> list = weixinMenuService.findList(new WeixinMenu());
		for (int i=0; i<list.size(); i++){
			WeixinMenu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 生成菜单
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("menu:weixinMenu:edit")
    @RequestMapping(value = "createMenu", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			
		 	Menu menu = getMenu();
			int result = WeixinUtil.createMenu(menu, WeixinGlobal.accessToken);

			// 判断菜单创建结果
			if (0 == result)
				logger.info("菜单创建成功！");
			else
				logger.error("菜单创建失败，错误码：" + result);

			addMessage(redirectAttributes, "生成菜单成功！");
		} catch (Exception e) {
			addMessage(redirectAttributes, "生成菜单失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/menu/weixinMenu/?repage";
    }
	
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private Menu getMenu() {
		
		Menu menu = new Menu();
		List<WeixinMenu> list = weixinMenuService.findParent();
		
		Button[] mainBtn=new Button[list.size()];
		
		for(int i=0;list!=null&&i<list.size();i++){
			ComplexButton parentBtn = new ComplexButton();
			parentBtn.setName(list.get(i).getName());
			List<WeixinMenu> childList = weixinMenuService.findChildrentByParent(list.get(i));
			
			ViewButton[] childrent=new ViewButton[childList.size()];
			
			for(int j=0;childList!=null&&j<childList.size();j++){
				ViewButton btn = new ViewButton();
				btn.setName(childList.get(j).getName());
				btn.setType("view");
				btn.setUrl(childList.get(j).getUrl());
				childrent[j]=btn;
			}
			parentBtn.setSub_button(childrent);
			mainBtn[i]=parentBtn;
		}
		menu.setButton(mainBtn);
		return menu;
	}
	
}