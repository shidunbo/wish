package com.mlx.orderpre.sign;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.mlx.orderpre.dict.ConstEC;
import com.mlx.orderpre.utils.HexStr;

public class Sign {

	/**
	 * 金蝶加密方式
	 * @param requestMap
	 * @param keys
	 * @throws Exception
	 */
	public static String getKingdeePlain(Map<String, Object> requestMap, String[] keys) throws Exception {

		String secret = "192.168.1.1";
		StringBuffer plain = new StringBuffer("");
		Arrays.sort(keys);
		Object value = null;
		plain.append(secret);
		for (String key : keys) {
			value = requestMap.get(key);
			if (null != value) {
				plain.append(key + requestMap.get(key.toString().trim()));
			}
		}
		plain.append(secret);
		return plain.toString();
	}

	/**
	 * 调用查询商品加密方式
	 * @return
	 * @throws Exception
	 */
	public static String getGoodsPlain(Map<String, Object> requestMap, String[] keys) throws Exception {
		StringBuffer plain = new StringBuffer("");
		Arrays.sort(keys);
		Object value = null;
		for (String key : keys) {
			value = requestMap.get(key);
			if (null != value) {
				plain.append(requestMap.get(key.toString()));
			}
		}
		return Sign.MD5Sign(plain.toString());
	}

	/**
	 * 订单系统加密方式
	 * @param requestMap
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static String getOrderPlain(Map<String, Object> requestMap, String[] keys) throws Exception {
		StringBuffer plain = new StringBuffer("");

		Arrays.sort(keys);

		Object value = null;
		for (String key : keys) {
			value = requestMap.get(key);
			if (null != value) {
				plain.append(requestMap.get(key.toString().trim()));
			}
		}

		return plain.toString();
	}

	/**
	 * order签名 MD5+Base64
	 * @param plain
	 * @return
	 * @throws Exception
	 */
	public static String orderSign(String plain) throws Exception {
		byte[] data;
		data = MD5Sign.encode(plain.getBytes(ConstEC.ENCODE_UTF8));
		return Base64.encodeBase64String(HexStr.bytesToHexString(data).getBytes());
	}

	/**
	 * MD5签名
	 * @param plain
	 * @return
	 * @throws Exception
	 */
	public static String MD5Sign(String plain) throws Exception {
		byte[] data = MD5Sign.encode(plain.getBytes(ConstEC.ENCODE_UTF8));
		return HexStr.bytesToHexString(data);
	}

	/**
	 * kingdee校验
	 * @param plain
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String plain, String sign) throws Exception {
		if (sign.equalsIgnoreCase(MD5Sign(plain))) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MD5Sign("1000.001orderRefundCheck45678924341457123"));
	}
}
