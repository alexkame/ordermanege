/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.weixin.system.entity.WeixinUserInfo;

/**
 * 订单表Entity
 * @author janson
 * @version 2016-04-18
 */
public class Ordertable extends DataEntity<Ordertable> {
	
	private static final long serialVersionUID = 1L;
	private WeixinUserInfo weixinUserInfoId;		// 微信粉丝ID 父类
	private String orderNum;		// 订单号
	private Date createTime;		// 下单时间
	private Long status;		// 状态(1:待发货,2:已发货,3:作废)
	private String statusStr;		// 状态(1:待发货,2:已发货,3:作废)
	private String totalSquare;		// 总平方
	private Date deliveryTime;		// 发货时间
	
	public Ordertable() {
		super();
	}

	public Ordertable(String id){
		super(id);
	}

	public Ordertable(WeixinUserInfo weixinUserInfoId){
		this.weixinUserInfoId = weixinUserInfoId;
	}

	@Length(min=1, max=64, message="微信粉丝ID长度必须介于 1 和 64 之间")
	public WeixinUserInfo getWeixinUserInfoId() {
		return weixinUserInfoId;
	}

	public void setWeixinUserInfoId(WeixinUserInfo weixinUserInfoId) {
		this.weixinUserInfoId = weixinUserInfoId;
	}
	
	@Length(min=1, max=64, message="订单号长度必须介于 1 和 64 之间")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="下单时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@NotNull(message="状态(1:待发货,2:已发货,3:作废)不能为空")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getTotalSquare() {
		return totalSquare;
	}

	public void setTotalSquare(String totalSquare) {
		this.totalSquare = totalSquare;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
}