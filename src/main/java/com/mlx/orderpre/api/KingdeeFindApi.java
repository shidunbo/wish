package com.mlx.orderpre.api;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.orderpre.common.AjaxResult;
import com.mlx.orderpre.dict.ConstEC;
import com.mlx.orderpre.exception.BusiException;
import com.mlx.orderpre.from.ParamterFrom;
import com.mlx.orderpre.service.KingdeeFindService;
import com.mlx.orderpre.utils.ApplicationProperties;
import com.mlx.orderpre.utils.DateTimeUtil;

@Controller
@RequestMapping("/kingdee")
public class KingdeeFindApi extends BaseAPI {
	private static Logger logger = LoggerFactory.getLogger(KingdeeFindApi.class);
	@Autowired
	private KingdeeFindService kingdeeFindService;

	/**
	 * 金蝶统一入口方法，根据method映射对应方法
	 * @param paramterFrom
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")
	@ResponseBody
	public Map<String, Object> index(ParamterFrom paramterFrom, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = null;
		Class<KingdeeFindApi> clazz = KingdeeFindApi.class;
		String methodName = ConstEC.APIMAPINGMETHOD.get(paramterFrom.getMethod());
		logger.info("调用方法[{}]" + methodName);
		if (methodName == null) {
			throw new BusiException("0011", ApplicationProperties.getMessage("0011"));
		}
		Method method = clazz.getMethod(methodName, ParamterFrom.class, HttpServletRequest.class);
		resultMap = (Map<String, Object>) method.invoke(this, paramterFrom, request);
		return resultMap;
	}

	/**
	 * 分页查询交易
	 * @param paramterFrom
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listTrades(ParamterFrom paramterFrom, HttpServletRequest request) {
		try {
			Map<String, Object> requestMap = null;
			requestMap = new HashMap<String, Object>();
			requestMap.put("pageNo", paramterFrom.getPage_no());
			requestMap.put("pageSize", paramterFrom.getPage_size());
			requestMap.put("use_has_next", paramterFrom.getUse_has_next());
			// 当开始和结束时间为空时，默认返回三天数据
			if (StringUtils.isEmpty(paramterFrom.getStart_time()) || StringUtils.isEmpty(paramterFrom.getEnd_time())) {
				requestMap.put("startDate", DateTimeUtil.date8(new Timestamp(DateTimeUtil.beforeDay(3).getTime())));
				requestMap.put("endDate", DateTimeUtil.date8());
			} else {
				requestMap.put(
						"startDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getStart_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
				requestMap.put(
						"endDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getEnd_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
			}
			// 当订单流水号不为空时，只传入流水号
			if (!StringUtils.isEmpty(paramterFrom.getTid())) {
				requestMap = new HashMap<String, Object>();
				requestMap.put("orderJnId", paramterFrom.getTid());
				// 因为订单系统查询交易startDate和endDate必传,切割出流水号中的时间，当订单系统查询交易非比传时，可以去掉
				requestMap.put("startDate", paramterFrom.getTid().substring(0, 8));
				requestMap.put("endDate", paramterFrom.getTid().substring(0, 8));
			}
			// 只获取支付成功的
			requestMap.put("payStatus", "S");
			// 请求订单系统Api名称
			requestMap.put("methodType", "orderJnList");
			requestMap.put("requestId", ServletRequestUtils.getStringParameter(request, ConstEC.RPID));
			return kingdeeFindService.findTrades(requestMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error_response", new AjaxResult("0001", "查询交易失败"));
			return map;
		}
	}

	/**
	 * 分页退款
	 * @param paramterFrom
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listRefunds(ParamterFrom paramterFrom, HttpServletRequest request) {
		try {
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put("pageNo", paramterFrom.getPage_no());
			requestMap.put("pageSize", paramterFrom.getPage_size());
			requestMap.put("use_has_next", paramterFrom.getUse_has_next());
			// 当开始和结束时间为空时，默认返回七天数据
			if (StringUtils.isEmpty(paramterFrom.getStart_time()) || StringUtils.isEmpty(paramterFrom.getEnd_time())) {
				requestMap.put("startDate", DateTimeUtil.date8(new Timestamp(DateTimeUtil.beforeDay(7).getTime())));
				requestMap.put("endDate", DateTimeUtil.date8());
			} else {
				requestMap.put(
						"startDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getStart_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
				requestMap.put(
						"endDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getEnd_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
			}
			// 当退款号不为空时,只传入退款号
			if (!StringUtils.isEmpty(paramterFrom.getRefund_id())) {
				requestMap = new HashMap<String, Object>();
				requestMap.put("refundJnId", paramterFrom.getRefund_id());
				// 因为订单系统查询退款startDate和endDate必传,切割出退款号中的时间，当订单系统查询退款非比传时，可以去掉
				requestMap.put("startDate", paramterFrom.getRefund_id().substring(0, 8));
				requestMap.put("endDate", paramterFrom.getRefund_id().substring(0, 8));

			}
			// 只获取退款成功的
			requestMap.put("refundStatus", "RS");
			// 请求订单系统Api名称
			requestMap.put("methodType", "refundList");
			requestMap.put("requestId", ServletRequestUtils.getStringParameter(request, ConstEC.RPID));
			return kingdeeFindService.findRefunds(requestMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error_response", new AjaxResult("0001", "查询退款单失败"));
			return map;
		}
	}

	/**
	 * 分页下载商品
	 * @param paramterFrom
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listGoods(ParamterFrom paramterFrom, HttpServletRequest request) {
		try {
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put("requestId", ServletRequestUtils.getStringParameter(request, ConstEC.RPID));
			requestMap.put("pageNo", paramterFrom.getPage_no());
			requestMap.put("pageSize", paramterFrom.getPage_size());
			requestMap.put("goodsId", paramterFrom.getNum_iid());
			requestMap.put("utime", System.currentTimeMillis());
			if (!StringUtils.isEmpty(paramterFrom.getStart_time()) && !StringUtils.isEmpty(paramterFrom.getEnd_time())) {
				requestMap.put(
						"startDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getStart_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
				requestMap.put(
						"endDate",
						DateTimeUtil.date8(new Timestamp(DateTimeUtil.formatStringToDate(paramterFrom.getEnd_time(),
								"yyyy-MM-dd HH:mm:ss").getTime())));
			}
			return kingdeeFindService.findGoods(requestMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error_response", new AjaxResult("0001", "下载商品失败"));
			return map;
		}
	}
}
