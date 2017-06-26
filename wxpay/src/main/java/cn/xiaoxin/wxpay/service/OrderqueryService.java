package cn.xiaoxin.wxpay.service;

import cn.xiaoxin.wxpay.Configure;
import cn.xiaoxin.wxpay.protocol.orderquery.OrderqueryReqData;

public class OrderqueryService extends BaseService {
	public OrderqueryService() {
		super(Configure.getOrderqueryUrl());
	}

	public String request(OrderqueryReqData orderqueryReqData) {
		String responseString = null;
		try {
			responseString = sendPost(orderqueryReqData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseString;
	}
}
