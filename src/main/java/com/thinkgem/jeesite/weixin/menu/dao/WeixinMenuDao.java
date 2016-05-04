/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.menu.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixin.menu.entity.WeixinMenu;

/**
 * 微信菜单DAO接口
 * @author janson
 * @version 2016-05-04
 */
@MyBatisDao
public interface WeixinMenuDao extends TreeDao<WeixinMenu> {

	List<WeixinMenu> findParent();

	List<WeixinMenu> findChildrentByParent(WeixinMenu weixinMenu);
	
}