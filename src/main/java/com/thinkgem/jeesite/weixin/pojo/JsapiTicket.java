package com.thinkgem.jeesite.weixin.pojo;

public class JsapiTicket {

	private String ticket;
	private int expires_in;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpiresIn() {
		return expires_in;
	}
	
	public void setExpiresIn(int expires_in) {
		this.expires_in = expires_in;
	}
	
}
