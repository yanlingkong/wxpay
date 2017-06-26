package cn.xiaoxin.wxpay.protocol.prepay;

import cn.xiaoxin.wxpay.Configure;
import cn.xiaoxin.wxpay.util.RandomStringGenerator;
import cn.xiaoxin.wxpay.util.Signature;

/**
 * 统一下单请求数据
 * 
 * @author yxiao
 * @date 2016年3月23日
 */
public class PrepayReqData {
	// 每个字段具体的意思请查看API文档
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String body;
	private String attach;
	private String out_trade_no;
	private int total_fee = 0;
	private String spbill_create_ip;
	private String time_start;
	private String time_expire;
	private String notify_url;
	private String trade_type = "APP";

	public PrepayReqData(String body, String attach, String out_trade_no, int total_fee,
			String spbill_create_ip, String notify_url, String trade_type) {
		super();
		this.appid = Configure.getAppID();
		this.mch_id = Configure.getMchID();
		this.body = body;
		this.attach = attach;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		try {
			setSign(Signature.getSign(this));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

}
