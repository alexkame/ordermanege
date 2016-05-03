package com.thinkgem.jeesite.qiniu;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Youzh
 * @version 1.0
 * @date create on：2016年3月30日 下午4:58:22
 * @describe
 * @since
 * @return
 */
@Controller
@RequestMapping("/qiNiuController")
public class QiNiuController {
	Logger logger = LoggerFactory.getLogger(QiNiuController.class);

	@Autowired
	QiNiuUtils qiniu;

	/**
	 * 获取七牛云上传token
	 */
	@RequestMapping("/getQiNiuUploadToken")
	@ResponseBody
	public Map<String, String> getQiNiuUploadToken(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, String> token = new HashMap();
		token.put("uptoken", qiniu.getQiNiuToken());
		return token;
	}

}
