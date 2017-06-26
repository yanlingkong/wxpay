package cn.xiaoxin.wxpay.protocol.wxpay;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import cn.xiaoxin.wxpay.Configure;
import cn.xiaoxin.wxpay.util.MD5;
import cn.xiaoxin.wxpay.util.RandomStringGenerator;

/**
 * APP端调起支付的参数列表
 * 
 * @author yxiao
 * @date 2016年4月5日
 */
public class WxPayRespData {
	private String appid;
	private String partnerid;
	private String prepayid;
	// package是关键字。用packageName代替
	private String packageName;
	private String noncestr;
	private String timestamp;
	private String sign;

	public WxPayRespData(String prepayid) {
		super();
		this.appid = Configure.getAppID();
		this.partnerid = Configure.getMchID();
		this.prepayid = prepayid;
		this.packageName = "Sign=WXPay";
		setNoncestr(RandomStringGenerator.getRandomStringByLength(32));
		this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		try {
			setSign(getSign(this));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * package是关键字。用packageName代替，生成签名的时候必须使用package
	 */
	private String getSign(Object o) throws IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class<? extends Object> cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(o) != null && f.get(o) != "") {
				if ("packageName".equals(f.getName())) {
					list.add("package" + "=" + f.get(o) + "&");
				} else {
					list.add(f.getName() + "=" + f.get(o) + "&");
				}
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + Configure.getKey();
		result = MD5.MD5Encode(result).toUpperCase();
		return result;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
