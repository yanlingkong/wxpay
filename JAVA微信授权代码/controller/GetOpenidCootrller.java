package com.authority.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.authority.entity.Winauthority;

@Controller
public class GetOpenidCootrller {

	private InputStream is;

	/**
	 * 获取openid
	 */
	@RequestMapping(value = "/openid")
	public void GetOpenidServlet(String code, HttpServletResponse response) {

		//微信提供的字段参数
		StringBuffer tempString = new StringBuffer();
		String APPID = "wxb06f0068723cc6f0";
		String SECRET = "c6650593e85b01120c3ddaab6d983d9c";
		String TYPE = "authorization_code";
		//获取openid  url
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + SECRET + "&code="
				+ code + "&grant_type=" + TYPE;

		HttpClient httpClient = new HttpClient();
		// 创建URL对象
		PostMethod postMethod = new PostMethod(url);

		// 执行postMethod
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
			// 301或者302
			if (statusCode == 200) {
				is = postMethod.getResponseBodyAsStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				String str;
				//获取到openid等信息
				while ((str = reader.readLine()) != null) {
					tempString.append(str);
					
				//入力参数
				Winauthority winauthority = new Winauthority();
				//解析字符串
				winauthority = JSON.parseObject(str, Winauthority.class);
				
				String access_token = winauthority.getAccess_token();  //网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
				String openid = winauthority.getOpenid();  //用户唯一标识
				
				//拼接获取用户信息url
				String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token + "&openid="+ openid + "&lang=zh_CN";
				//响应跳转
				response.sendRedirect(url2);
				
				System.out.println("-------------------------->"+ url2);
				}
				System.out.println("tempString---->>" + tempString.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

}
