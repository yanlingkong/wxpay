package cn.xiaoxin.wxpay.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoxin.wxpay.Configure;
import cn.xiaoxin.wxpay.protocol.prepay.PrepayReqData;
import cn.xiaoxin.wxpay.util.Signature;
import cn.xiaoxin.wxpay.util.Util;

public class PrepayService extends BaseService {
	private static final Logger logger = LoggerFactory.getLogger(PrepayService.class);

	public PrepayService() {
		super(Configure.getPrePayUrl());
	}

	public String request(PrepayReqData prepayReqData) {
		String responseString = null;
		try {
			responseString = sendPost(prepayReqData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseString;
	}

	public String getPrepay_id(PrepayReqData prepayReqData) throws Exception {
		String responseString = request(prepayReqData);
		if (responseString == null) {
			return null;
		}
		Map<String, Object> map = Util.getMapFromXML(responseString);
		if (!Signature.checkIsSignValidFromResponseString(map)) {
			logger.error("数据签名数据错误");
			return null;
		}
		if (!"SUCCESS".equals(map.get("return_code"))) {
			logger.error("Result_code:FAIL,Return_msg:" + map.get("return_msg"));
			return null;
		}
		if (!"SUCCESS".equals(map.get("result_code"))) {
			logger.error("Result_code:FAIL,Erro_code:" + map.get("err_code") + ",Err_code_des:"
					+ map.get("err_code_des"));
			return null;
		}
		String prepay_id = String.valueOf(map.get("prepay_id"));
		logger.info("下单成功:" + prepay_id);
		return prepay_id;
	}
}
