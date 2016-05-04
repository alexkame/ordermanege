package com.thinkgem.jeesite.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.weixin.config.WeixinGlobal;

import net.sf.json.JSONObject;

public class AdvancedModelUtil {

	private static Logger log = LoggerFactory
			.getLogger(AdvancedModelUtil.class);
	
	public static void main(String[] args) {
		//String msg=makeRepaymentSuccessMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","2015年10月1日","1100元","#173177","小明经济");
		//String msg=makeRepaymentFailMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","2015年10月1日","1100元","手动还款","失败","#173177","小明经济");
		//String msg=makeLoanSuccessMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","1100元","1000元","100元","2015年10月1日","#173177","小明经济");
		//String msg=makeLoanFailMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","信息不完整","1000元","#173177","小明经济");
		//String msg=makeLoanRepaymentMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","编号-001","1000元","2015年10月1日","#173177","小明经济");
		//String msg=makeLoanLoanMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","编号-001","1000元","2015年10月1日","#173177","小明经济");
		String msg=makeLoanCheckMessage("oWKiiv2u2BPjvdfKl10rEIrwTJNE","","first","1000元","3月","5%","通过","#173177","小明经济");
		
		
		String accessToken = AdvancedUtil.getAccessToken(WeixinGlobal.getAppid(),
				WeixinGlobal.getAppsecret()).getToken();
		sendModelMessage(accessToken,msg);
	}

	/**
	 * 还款成功消息
	 */
	public static String makeRepaymentSuccessMessage(String openId,String url,
			String first,String rayDate,String rayMoney
			,String color,String remark) {
		
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"3kKes7GGJqPFUYDXjUEeUa6XrAWPN-6FWpZVHAI8Z98\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款时间
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+rayDate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+rayMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 还款失败消息
	 */
	public static String makeRepaymentFailMessage(String openId,String url,String first,
			String rayDate,String rayMoney,String rayType,String rayStatus
			,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"PIWPh5fej0DcD7W8cppdYLzw76TRbtWs8suxZIZGRVk\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款金额
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+rayMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+rayType+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款金额
		sb.append("\"keyword3\":{");
		sb.append("\"value\":");
		sb.append("\""+rayStatus+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//还款时间
		sb.append("\"keyword4\":{");
		sb.append("\"value\":");
		sb.append("\""+rayDate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 借款成功消息
	 */
	public static String makeLoanSuccessMessage(String openId,String url,String first,
			String loanMoney,String loanedMoney,String serviceMoney,String createDate
			,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"9pRzHfnu2m0Ei85iDyElBO1w1gisl2qhd4wN-Fg7Yk0\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//借款金额
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+loanMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//放款金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+loanedMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//服务费
		sb.append("\"keyword3\":{");
		sb.append("\"value\":");
		sb.append("\""+serviceMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//申请时间
		sb.append("\"keyword4\":{");
		sb.append("\"value\":");
		sb.append("\""+createDate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 借款失败消息
	 */
	public static String makeLoanFailMessage(String openId,String url,String first,
			String failReason,String loanMoney,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"eeG5XdNShAjYemJWllBzQhdaqmjzwQaNV9OYW96PDaM\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//失败原因
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+failReason+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//借款金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+loanMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 借款还款消息
	 */
	public static String makeLoanRepaymentMessage(String openId,String url,String first,
			String loanNo,String payMoney,String loanDate
			,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"VXd1uN_b9RxI4I9IieVhQ9KNpKURuvMr_etr7PI60eE\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//借款编号
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+loanNo+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//本期应还金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+payMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//本期应还时间
		sb.append("\"keyword3\":{");
		sb.append("\"value\":");
		sb.append("\""+loanDate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 借款放款消息
	 */
	public static String makeLoanLoanMessage(String openId,String url,String first,
			String loanNo,String loanMoney,String loanDate
			,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"pCQcsAXKcA-jAx3VzJwHCgHlYlzAyq2XcauyhZWiyJ4\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//借款编号
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+loanNo+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//放款金额
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+loanMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//放款时间
		sb.append("\"keyword3\":{");
		sb.append("\"value\":");
		sb.append("\""+loanDate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 贷款审核消息
	 */
	public static String makeLoanCheckMessage(String openId,String url,String first,
			String loanMoney,String loanMonth,String monthRate,String status
			,String color,String remark) {
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"touser\":");
		sb.append("\""+openId+"\"");
		sb.append(",");
		sb.append("\"template_id\":");
		sb.append("\"_1JCo-dOL-NgwEUXiz6hTtjuTbWrhqXyOGSf94fJHfQ\"");
		sb.append(",");
		sb.append("\"url\":");
		sb.append("\""+url+"\"");
		sb.append(",");
		
		sb.append("\"data\":{");
		//第一个值
		sb.append("\"first\":{");
		sb.append("\"value\":");
		sb.append("\""+first+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//贷款金额
		sb.append("\"keyword1\":{");
		sb.append("\"value\":");
		sb.append("\""+loanMoney+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//贷款期限
		sb.append("\"keyword2\":{");
		sb.append("\"value\":");
		sb.append("\""+loanMonth+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//月利率
		sb.append("\"keyword3\":{");
		sb.append("\"value\":");
		sb.append("\""+monthRate+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		//审核状态
		sb.append("\"keyword4\":{");
		sb.append("\"value\":");
		sb.append("\""+status+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("},");
		
		
		//最后一个值
		sb.append("\"remark\":{");
		sb.append("\"value\":");
		sb.append("\""+remark+"\"");
		sb.append(",");
		sb.append("\"color\":");
		sb.append("\""+color+"\"");
		sb.append("}");
		
		sb.append("}");
		
		sb.append("}");
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 发送模板消息
	 * 
	 * @param jsonMsg
	 * @return true | false
	 */
	public static boolean sendModelMessage(String accessToken, String jsonMsg) {
		log.info("消息内容：{}", jsonMsg);
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST",
				jsonMsg);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			String msgid = null;
			if (0 == errorCode) {
				result = true;
				msgid = jsonObject.getString("msgid");
				log.info("模板消息消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
				log.info("模板消息发送成功 errcode:{} msgid:{}", errorCode, msgid);
			} else {
				log.error("模板消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}
	
	
}
