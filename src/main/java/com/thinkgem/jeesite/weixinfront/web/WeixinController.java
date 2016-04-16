package com.thinkgem.jeesite.weixinfront.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.utils.WiexinSignUtil;
import com.thinkgem.jeesite.weixin.service.WeixinService;

/**
 * 测试Controller
 * 
 * @author ThinkGem
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${weixinPath}/weixin")
public class WeixinController extends BaseController {

	@Resource
	WeixinService weixinService;

	/**
	 * 
	 * @param signature
	 *            微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            随机数
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {

		System.out.println("=============================================== get start");
		for (Object o : request.getParameterMap().keySet()) {
			System.out.println(o + " = " + request.getParameter((String) o));
		}
		System.out.println("=============================================== get end");

		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (WiexinSignUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}

		return "";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		System.out.println("=============================================== post start");
		for (Object o : request.getParameterMap().keySet()) {
			System.out.println(o + " = " + request.getParameter((String) o));
		}
		System.out.println("=============================================== post end");

		String respXml = weixinService.processRequest(request);
		
		out.print(respXml);

		out.close();
		out = null;
		return null;
	}

}
