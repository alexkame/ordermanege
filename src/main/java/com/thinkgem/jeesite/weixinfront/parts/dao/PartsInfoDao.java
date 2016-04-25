/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.parts.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.weixinfront.parts.entity.PartsInfo;

/**
 * 配件DAO接口
 * @author janson
 * @version 2016-04-25
 */
@MyBatisDao
public interface PartsInfoDao extends CrudDao<PartsInfo> {
	
}