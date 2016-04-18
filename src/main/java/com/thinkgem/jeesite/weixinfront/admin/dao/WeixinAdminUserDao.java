/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.admin.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixinfront.admin.entity.WeixinAdminUser;

/**
 * 微信管理员DAO接口
 * @author janson
 * @version 2016-04-17
 */
@MyBatisDao
public interface WeixinAdminUserDao extends CrudDao<WeixinAdminUser> {

	List<WeixinAdminUser> findUserNameAndPassword(WeixinAdminUser weixinAdminUser);
	
}