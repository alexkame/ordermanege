/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.parts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.weixinfront.parts.entity.PartsInfo;
import com.thinkgem.jeesite.weixinfront.parts.dao.PartsInfoDao;

/**
 * 配件Service
 * @author janson
 * @version 2016-04-25
 */
@Service
@Transactional(readOnly = true)
public class PartsInfoService extends CrudService<PartsInfoDao, PartsInfo> {

	public PartsInfo get(String id) {
		return super.get(id);
	}
	
	public List<PartsInfo> findList(PartsInfo partsInfo) {
		return super.findList(partsInfo);
	}
	
	public Page<PartsInfo> findPage(Page<PartsInfo> page, PartsInfo partsInfo) {
		return super.findPage(page, partsInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PartsInfo partsInfo) {
		super.save(partsInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PartsInfo partsInfo) {
		super.delete(partsInfo);
	}
	
}