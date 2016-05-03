package com.thinkgem.jeesite.qiniu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.qiniu.util.Auth;

/**
 * @author  Youzh
 * @describe 七牛云工具类
 * @version 1.0 
 * @date  2016年3月28日 上午11:08:19 
 * @return  
 */
@Component
@PropertySource("classpath:/qiniu.properties")
public class QiNiuUtils {
	
	@Value("${qiniu.access_key}") private String access_key;
	@Value("${qiniu.secret_key}") private String secret_key;
	@Value("${qiniu.bucketname}") private String bucketname;
	
	Auth qiniuauth;
	
	public String getQiNiuToken(){
		
		return qiniuauth.uploadToken(this.bucketname, null, 36000000, null);
	}
	
	@PostConstruct
	public void init(){
		qiniuauth= Auth.create(access_key, secret_key);
	}
}
