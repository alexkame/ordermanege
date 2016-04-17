/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.weixinfront.admin.entity.WeixinAdminUser;
import com.thinkgem.jeesite.weixinfront.admin.dao.WeixinAdminUserDao;

/**
 * 微信管理员Service
 * 
 * @author janson
 * @version 2016-04-17
 */
@Service
@Transactional(readOnly = true)
public class WeixinAdminUserService extends CrudService<WeixinAdminUserDao, WeixinAdminUser> {

	public WeixinAdminUser get(String id) {
		WeixinAdminUser weixinAdminUser = super.get(id);
		return weixinAdminUser;
	}

	public List<WeixinAdminUser> findList(WeixinAdminUser weixinAdminUser) {
		return super.findList(weixinAdminUser);
	}

	public Page<WeixinAdminUser> findPage(Page<WeixinAdminUser> page, WeixinAdminUser weixinAdminUser) {
		return super.findPage(page, weixinAdminUser);
	}

	@Transactional(readOnly = false)
	public void save(WeixinAdminUser weixinAdminUser) {
		super.save(weixinAdminUser);
	}

	@Transactional(readOnly = false)
	public void delete(WeixinAdminUser weixinAdminUser) {
		super.delete(weixinAdminUser);
	}

	public Map<String, Object> weixinLogin(String userName, String password, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userName = userName.trim();
			password = password.trim();
			if (userName == null && "".equals(userName) || password == null || "".equals(password)) {
				result.put("code", 200);
				result.put("message", "参数格式错误！");
			} else {
				WeixinAdminUser weixinAdminUser = new WeixinAdminUser();
				weixinAdminUser.setUserName(userName);
				weixinAdminUser.setPassword(password);
				List<WeixinAdminUser> adminUsers = super.findList(weixinAdminUser);
				if (adminUsers.size() > 0) {
					result.put("code", 1);
					result.put("message", "登陆成功！");
					request.getSession().setAttribute("weixinAdminUser", adminUsers.get(0));
				} else {
					result.put("code", 0);
					result.put("message", "用户名或账号不存在！");
				}
			}
		} catch (Exception e) {
			result.put("code", 900);
			result.put("message", "系统出错！");
		}
		return result;
	}

}