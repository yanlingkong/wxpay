package cn.xiaoxin.wxpay.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoxin.wxpay.util.HttpClientUtil;
import cn.xiaoxin.wxpay.util.Util;

/**
 * HTTP请求基类
 * 
 * @author yxiao
 * @date 2016年3月23日
 */
public abstract class BaseService {
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	private String apiURL;

	public BaseService(String api) {
		apiURL = api;
	}

	protected String sendPost(Object xmlObj) throws UnrecoverableKeyException, IOException,
			NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		return sendPost(apiURL, xmlObj);
	}

	/**
	 * 通过Https往API post xml数据
	 */
	public static String sendPost(String url, Object xmlObj) throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
		// 将要提交给API的数据对象转换成XML格式数据Post给API
		String postDataXML = Util.getXmlStringFromObjec(xmlObj);
		logger.info("POST Data :{}", postDataXML);
		return HttpClientUtil.sendPost(url, postDataXML);
		// return HttpsRequestProxy.postByHttps(url, postDataXML, "text/xml",
		// "UTF-8");
	}
}
