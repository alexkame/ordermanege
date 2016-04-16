/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixin.system.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;

/**
 * 微信粉丝信息DAO接口
 * @author janson
 * @version 2016-04-16
 */
@MyBatisDao
public interface WeixinUserInfoDao extends CrudDao<WeixinUserInfo> {

	WeixinUserInfo findByOpenid(String openid);
	
}