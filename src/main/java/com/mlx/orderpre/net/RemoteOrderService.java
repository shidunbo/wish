package com.mlx.orderpre.net;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mlx.orderpre.dict.ConstEC;
import com.mlx.orderpre.sign.Sign;
import com.mlx.orderpre.utils.ApplicationProperties;
import com.mlx.orderpre.utils.HttpClientUtil;

/**
 * 远程调用业务
 * @author shidb
 *
 */
@Service
public class RemoteOrderService {

	private static Logger log = LoggerFactory.getLogger(RemoteOrderService.class);

	public String orderRefunds(Map<String, Object> requestMap) throws Exception {
		return this.sendHttp("refunds", requestMap);
	}

	public String orderTrades(Map<String, Object> requestMap) throws Exception {
		return this.sendHttp("orderTrades", requestMap);
	}

	public String findGoods(Map<String, Object> requestMap) throws Exception {
		return this.sendHttpGoods("findGoods", requestMap);
	}

	private String sendHttp(String adapterStr, Map<String, Object> requestMap) throws Exception {
		String rpId = (String) requestMap.get(ConstEC.RPID);
		log.info("请求流水号rpId[{}], 请求远程消费参数[{}], adapterStr[{}]", rpId, requestMap, adapterStr);
		String[] keys = ApplicationProperties.getMessage("order.req_param_" + adapterStr).split(",");
		String plain = Sign.getOrderPlain(requestMap, keys);
		plain += ApplicationProperties.getMessage(ConstEC.MLX_PRIVATEKEY);
		String sign = Sign.orderSign(plain);
		requestMap.put("sign", sign);

		Map<String, Object> remoteParam = new HashMap<String, Object>();
		for (String key : keys) {
			if (null != requestMap.get(key)) {
				remoteParam.put(key, requestMap.get(key));
			}
		}
		String respStr = HttpClientUtil.httpPost(ApplicationProperties.getMessage("order.req_url_" + adapterStr),
				remoteParam);

		log.info("请求流水号rpId[{}], 收到的响应报文[{}]", rpId, respStr);

		return respStr;
	}

	private String sendHttpGoods(String adapterStr, Map<String, Object> requestMap) throws Exception {
		String rpId = (String) requestMap.get(ConstEC.RPID);
		log.info("请求流水号rpId[{}], 请求远程消费参数[{}], adapterStr[{}]", rpId, requestMap, adapterStr);
		String[] keys = ApplicationProperties.getMessage("order.req_param_" + adapterStr).split(",");
		String plain = Sign.getGoodsPlain(requestMap, keys);
		String sign = Sign.MD5Sign(plain + requestMap.get("utime"));
		requestMap.put("sign", sign);

		Map<String, Object> remoteParam = new HashMap<String, Object>();
		for (String key : keys) {
			if (null != requestMap.get(key)) {
				remoteParam.put(key, requestMap.get(key));
			}
		}
		String respStr = HttpClientUtil.httpPost(ApplicationProperties.getMessage("order.req_url_" + adapterStr),
				remoteParam);

		log.info("请求流水号rpId[{}], 收到的响应报文[{}]", rpId, respStr);

		return respStr;
	}
}
