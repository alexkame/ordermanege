/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixin.system.entity.WeixinInfo;

/**
 * 微信信息DAO接口
 * @author janson
 * @version 2016-03-11
 */
@MyBatisDao
public interface WeixinInfoDao extends CrudDao<WeixinInfo> {
	
}