package com.mlx.orderpre.dict;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author feihangchen
 *
 */
public class ConstEC {

	public static final String DEFAULT_PAGESIZE = "100";
	public static final String DEFAULT_PAGENO = "1";
	public static final String ENCODE_UTF8 = "utf-8";
	public static final String ENCODE_GBK = "gbk";
	public static final String MIMETYPE = "text/plain";
	public static final String SUCCESS = "success";
	public static final String CODE_0000 = "0000";
	public static final String VERSION = "1.0";
	public static final String SIGN = "sign";
	public static final String RPID = "rpId";
	public static final String METHOD = "method";
	public static final String PARAMTER_VERIFY_PREFIX = "paramter_verify.";
	// 订单交易状态映射集合
	public static final Map<String, String> ORDERJNSTATUSMAPPING = new HashMap<String, String>();
	// 订单退款状态映射集合
	public static final Map<String, String> ORDERREFUNDSTATUSMAPPING = new HashMap<String, String>();
	// API接口映射方法名称
	public static final Map<String, String> APIMAPINGMETHOD = new HashMap<String, String>();
	// 上下架映射
	public static final Map<String, String> APPROVE_STATUS = new HashMap<String, String>();
	static {
		ORDERJNSTATUSMAPPING.put("TRADE_WAIT_BUYER_PAY", "W");
		ORDERJNSTATUSMAPPING.put("TRADE_SELLER_SEND_GOODS", "S");
		ORDERJNSTATUSMAPPING.put("TRADE_WAIT_BUYER_CONFIRM_GOODS", "SF");
		ORDERJNSTATUSMAPPING.put("TRADE_FINISHED", "SF");
		ORDERJNSTATUSMAPPING.put("TRADE_AUTOMATIC_CLOSED", "DN");

		ORDERREFUNDSTATUSMAPPING.put("WAIT_SELLER_AGREE", "RR");
		ORDERREFUNDSTATUSMAPPING.put("WAIT_BUYER_RETURN_GOODS", "RC");
		ORDERREFUNDSTATUSMAPPING.put("SELLER_REFUSE_BUYER", "F");
		ORDERREFUNDSTATUSMAPPING.put("SUCCESS", "RS");

		APIMAPINGMETHOD.put("kingdee.trades.get", "listTrades");
		APIMAPINGMETHOD.put("kingdee.refunds.get", "listRefunds");
		APIMAPINGMETHOD.put("kingdee.items.get", "listGoods");

		APPROVE_STATUS.put("1", "onsale");
		APPROVE_STATUS.put("2", "instock");

	}

	public static final String MLX_PRIVATEKEY = "mlx.privatekey";
}
