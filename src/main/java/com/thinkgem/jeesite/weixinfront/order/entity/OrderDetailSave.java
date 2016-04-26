package com.thinkgem.jeesite.weixinfront.order.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailSave {
	List<OrderDetailSimple> items=new ArrayList<OrderDetailSimple>();
	
	List<OrderDetailSimple> fits=new ArrayList<OrderDetailSimple>();

	public List<OrderDetailSimple> getItems() {
		return items;
	}

	public void setItems(List<OrderDetailSimple> items) {
		this.items = items;
	}

	public List<OrderDetailSimple> getFits() {
		return fits;
	}

	public void setFits(List<OrderDetailSimple> fits) {
		this.fits = fits;
	}
	 
}
