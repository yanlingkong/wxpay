package cn.xiaoxin.wxpay;

import cn.xiaoxin.wxpay.protocol.notify.NotifyRespData;
import cn.xiaoxin.wxpay.util.Util;

/**
 * 微信参数
 * 
 * @author yxiao
 * @date 2016年3月23日
 */
public class Configure {
	private static String key = "Yu1BmwRswe1SusD0WHxo3KFSj7MGkqu9";

	// 微信开放平台审核通过的应用APPID
	private static String appID = "wx88ae3382500475c1";

	// 微信支付分配的商户号
	private static String mchID = "1286629101";

	private static String sucessStr = Util
			.getXmlStringFromObjec(new NotifyRespData("SUCCESS", "OK"));
	// 统一下单
	private static String prePayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 查询订单
	private static String orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

	public static String getSucessStr() {
		return sucessStr;
	}

	public static String getKey() {
		return key;
	}

	public static String getAppID() {
		return appID;
	}

	public static String getMchID() {
		return mchID;
	}

	public static String getPrePayUrl() {
		return prePayUrl;
	}

	public static String getOrderqueryUrl() {
		return orderqueryUrl;
	}
}
