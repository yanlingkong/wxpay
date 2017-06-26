package cn.xiaoxin.wxpay;

import cn.xiaoxin.wxpay.protocol.orderquery.OrderqueryReqData;
import cn.xiaoxin.wxpay.protocol.orderquery.OrderqueryRespData;
import cn.xiaoxin.wxpay.protocol.prepay.PrepayReqData;
import cn.xiaoxin.wxpay.service.OrderqueryService;
import cn.xiaoxin.wxpay.service.PrepayService;
import cn.xiaoxin.wxpay.util.Util;

public class TestWx {
	public static void main(String[] args) throws Exception {
		// System.out.println(Util.getXmlStringFromObjec(new
		// NotifyRespData("SUCCESS", "OK")));
		test1();
	}

	public static void test2() {
		OrderqueryReqData orderqueryReqData = new OrderqueryReqData("2016040611060425");
		String responseString = new OrderqueryService().request(orderqueryReqData);
		System.out.println(responseString);
		OrderqueryRespData respData = (OrderqueryRespData) Util.getObjectFromXML(responseString,
				OrderqueryRespData.class);
		System.out.println(respData);
	}

	public static void test1() throws Exception {
		// System.out.println(WxHttpRequest.getRequest("https://api2.100xiaoxin.cn/user/v1/filalnews/getFilialNewsList"));
		PrepayReqData prepayReqData = new PrepayReqData("APP支付测试", "yxiao", "19910523101X", 1,
				"14.23.150.211", "http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php", "APP");
		PrepayService prepayService = new PrepayService();
		String prepay_id = prepayService.getPrepay_id(prepayReqData);
		System.out.println(prepay_id);
	}
}
