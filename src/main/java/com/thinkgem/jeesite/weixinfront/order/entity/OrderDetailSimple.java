/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.weixinfront.order.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.weixinfront.parts.entity.PartsInfo;

/**
 * 订单明细Entity
 * @author janson
 * @version 2016-04-25
 */
public class OrderDetailSimple extends DataEntity<OrderDetailSimple> {
	
	private static final long serialVersionUID = 1L;
	private Ordertable orderId;		// 订单ID 父类
	private PartsInfo myFit;		// 配件ID
	private Long num;		// 数量
	private Long type;		// 类型(1:配件,2:瓦片)
	private String nodeNum;		// 节数
	private String tableNum;		// 片数
	private Long color;		// 颜色(1:深灰,2:枣红,3:砖红,4:蓝色)
	private Long thickness;		// 厚度(1:2.5mm,2:3.0mm)
	private String pieceLength;		// 每片长度(米)
	private String totalSquare;		// 总平方
	
	public OrderDetailSimple() {
		super();
	}

	public OrderDetailSimple(String id){
		super(id);
	}

	public OrderDetailSimple(Ordertable orderId){
		this.orderId = orderId;
	}

	@Length(min=1, max=64, message="订单ID长度必须介于 1 和 64 之间")
	public Ordertable getOrderId() {
		return orderId;
	}

	public void setOrderId(Ordertable orderId) {
		this.orderId = orderId;
	}

	public PartsInfo getMyFit() {
		return myFit;
	}

	public void setMyFit(PartsInfo myFit) {
		this.myFit = myFit;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	public String getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(String nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}
	
	public Long getColor() {
		return color;
	}

	public void setColor(Long color) {
		this.color = color;
	}
	
	public Long getThickness() {
		return thickness;
	}

	public void setThickness(Long thickness) {
		this.thickness = thickness;
	}
	
	public String getPieceLength() {
		return pieceLength;
	}

	public void setPieceLength(String pieceLength) {
		this.pieceLength = pieceLength;
	}
	
	public String getTotalSquare() {
		return totalSquare;
	}

	public void setTotalSquare(String totalSquare) {
		this.totalSquare = totalSquare;
	}
	
}