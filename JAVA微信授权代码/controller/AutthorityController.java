package com.authority.controller;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AutthorityController {
	
	@ResponseBody
	@RequestMapping(value = "/autthority", method = RequestMethod.GET)
	public void autthority(HttpServletRequest request , HttpServletResponse response) throws Exception {
	
	//获取授权URL
	String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb06f0068723cc6f0&redirect_uri="
								+ URLEncoder.encode("http://192.168.0.139:8080/xiaobai_bak/openid", "UTF-8")
								+ "&response_type=code&scope=snsapi_userinfo#wechat_redirect";
			//ֱ响应返回
			response.sendRedirect(url);
			

			
	}

}
