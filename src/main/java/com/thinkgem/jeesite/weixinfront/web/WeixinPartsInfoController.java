/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.weixinfront.parts.entity.PartsInfo;
import com.thinkgem.jeesite.weixinfront.parts.service.PartsInfoService;
import com.thinkgem.jeesite.weixinfront.util.Util;

/**
 * 配件Controller
 * 
 * @author janson
 * @version 2016-04-25
 */
@Controller
@RequestMapping(value = "${weixinPath}/parts/partsInfo")
public class WeixinPartsInfoController extends BaseController {

	@Autowired
	private PartsInfoService partsInfoService;

	@ModelAttribute
	public PartsInfo get(@RequestParam(required = false) String id) {
		PartsInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = partsInfoService.get(id);
		}
		if (entity == null) {
			entity = new PartsInfo();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	@ResponseBody
	public List<PartsInfo> list(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return partsInfoService.findList(new PartsInfo());
	}

	@RequestMapping(value = "form")
	public String form(PartsInfo partsInfo, Model model) {
		model.addAttribute("partsInfo", partsInfo);
		return "weixinfront/parts/partsInfoForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			PartsInfo partsInfo = (PartsInfo) JsonMapper.fromJsonString(params_log, PartsInfo.class);
			partsInfoService.save(partsInfo);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("parts/partsInfo save error : {}", e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			PartsInfo partsInfo = (PartsInfo) JsonMapper.fromJsonString(params_log, PartsInfo.class);
			partsInfoService.delete(partsInfo);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("parts/partsInfo delete error : {}", e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

	/**
	 * 微信获取客户信息根据ID
	 */
	@RequestMapping(value = "findById")
	@ResponseBody
	public PartsInfo findById(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String params_log = Util.aesDecrypt(params);
		PartsInfo partsInfo = (PartsInfo) JsonMapper.fromJsonString(params_log, PartsInfo.class);
		return get(partsInfo.getId());
	}

	/**
	 * 微信获取员工信息根据ID
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public Map<String, Object> update(String params, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String params_log = Util.aesDecrypt(params);
			PartsInfo partsInfo = (PartsInfo) JsonMapper.fromJsonString(params_log, PartsInfo.class);
			PartsInfo oldPartsInfo = get(partsInfo.getId());
			oldPartsInfo.setName(partsInfo.getName());
			oldPartsInfo.setSpec(partsInfo.getSpec());
			partsInfoService.save(oldPartsInfo);
			result.put("code", true);
		} catch (Exception e) {
			logger.error("parts/partsInfo update error : {}", e.getMessage());
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

	@RequestMapping(value = { "upload" })
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws IllegalStateException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String filePath = request.getSession().getServletContext().getRealPath("/");
			String path= "/upload/"+System.currentTimeMillis()+ file.getOriginalFilename();
			String pathurl=Global.getSystemUrl()+path;
			File saveDir = new File(filePath,path);
			if (!saveDir.getParentFile().exists())
				saveDir.getParentFile().mkdirs();
			// 转存文件
			file.transferTo(saveDir);
			result.put("code", true);
			result.put("message", pathurl);
		} catch (Exception e) {
			result.put("code", false);
			result.put("message", "系统出错");
		}
		return result;
	}

}