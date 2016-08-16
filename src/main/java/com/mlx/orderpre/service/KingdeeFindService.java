package com.mlx.orderpre.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mlx.orderpre.dict.ConstEC;
import com.mlx.orderpre.exception.BusiException;
import com.mlx.orderpre.from.ItemFrom;
import com.mlx.orderpre.from.OrderJnFrom;
import com.mlx.orderpre.from.OrderRefundFrom;
import com.mlx.orderpre.model.Item;
import com.mlx.orderpre.model.Order;
import com.mlx.orderpre.model.OrderGoods;
import com.mlx.orderpre.model.OrderInfo;
import com.mlx.orderpre.model.OrderJn;
import com.mlx.orderpre.model.OrderRefund;
import com.mlx.orderpre.model.Refund;
import com.mlx.orderpre.model.Trade;
import com.mlx.orderpre.net.RemoteOrderService;
import com.mlx.orderpre.utils.DateTimeUtil;
import com.mlx.orderpre.utils.FieldUtil;

@Service
public class KingdeeFindService {

	// private static Logger logger = LoggerFactory.getLogger(KingdeeFindService.class);
	@Autowired
	private RemoteOrderService remoteOrderService;

	/**
	 * 查询交易
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findTrades(Map<String, Object> requestMap) throws Exception {

		String orderTrades = null;
		Gson gson = new Gson();
		OrderJnFrom fromJson = null;
		orderTrades = remoteOrderService.orderTrades(requestMap);
		try {
			fromJson = gson.fromJson(orderTrades, new TypeToken<OrderJnFrom>() {
			}.getType());
		} catch (Exception e) {
			// 解析异常 返回的是异常格式的json格式
			return this.errorHandle(gson, orderTrades);
		}
		List<Trade> trades = new LinkedList<Trade>();
		Trade trade = null;
		Map<String, List<Order>> orders = null;
		for (OrderJn orderJn : fromJson.getResult()) {
			// 生成Trade 不包括时间和order,status
			trade = FieldUtil.filedSet(Trade.class, "kingdee.orderJn.", orderJn);
			// 设置trade状态
			for (Entry<String, String> entry : ConstEC.ORDERJNSTATUSMAPPING.entrySet()) {
				if (entry.getValue().equals(orderJn.getPayStatus())) {
					trade.setStatus(entry.getKey());
				}
			}
			trade.setCreated(DateTimeUtil.formatTimestamp2String(new Date(Long.parseLong(orderJn.getCreateTime())),
					"yyyy-MM-dd HH:mm:ss"));
			trade.setModified(DateTimeUtil.formatTimestamp2String(new Date(Long.parseLong(orderJn.getUpdateTime())),
					"yyyy-MM-dd HH:mm:ss"));
			List<OrderInfo> orderInfos = orderJn.getOrderInfos();
			// 循环生成orders
			orders = new HashMap<String, List<Order>>();
			List<Order> list = new LinkedList<Order>();
			orders.put("order", list);
			for (OrderInfo orderInfo : orderInfos) {
				Order order = FieldUtil.filedSet(Order.class, "kingdee.orderInfo.", orderInfo);
				List<OrderGoods> orderGoods = orderInfo.getOrderGoods();
				// 拼接商品名称,id,库存数等
				StringBuffer title = new StringBuffer();
				StringBuffer num_iid = new StringBuffer();
				StringBuffer sku_id = new StringBuffer();
				int num = 0;
				for (OrderGoods orderGood : orderGoods) {
					title.append("," + orderGood.getGoodsName());
					num_iid.append("," + orderGood.getGoodsId());
					sku_id.append("," + orderGood.getSkuId());
					// 总数
					num = num + Integer.parseInt(orderGood.getChildNum()) + Integer.parseInt(orderGood.getAdultNum())
							+ Integer.parseInt(orderGood.getGoodsNum());
				}
				if (title.toString().length() > 0) {
					order.setTitle(title.toString().substring(1));
				}
				if (num_iid.toString().length() > 0) {
					order.setNum_iid(num_iid.toString().substring(1));
				}
				if (sku_id.toString().length() > 0) {
					order.setSku_id(sku_id.toString().substring(1));
				}

				order.setNum("" + num);
				list.add(order);
			}
			trade.setOrders(orders);
			trades.add(trade);
		}

		return this.successHandle("trade", trades, requestMap, fromJson.getPage(), fromJson.getPageCount(),
				fromJson.getTotal());

	}

	/**
	 * 查询退款单
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findRefunds(Map<String, Object> requestMap) throws Exception {
		String orderRefunds = null;
		Gson gson = new Gson();
		OrderRefundFrom fromJson = null;
		orderRefunds = remoteOrderService.orderRefunds(requestMap);
		try {
			fromJson = gson.fromJson(orderRefunds, new TypeToken<OrderRefundFrom>() {
			}.getType());
		} catch (Exception e) {
			// 解析异常 返回的是异常格式的json格式
			return this.errorHandle(gson, orderRefunds);
		}
		List<Refund> refunds = new LinkedList<Refund>();
		Refund refund = null;
		for (OrderRefund orderRefund : fromJson.getResult()) {
			refund = FieldUtil.filedSet(Refund.class, "kingdee.orderRefund.", orderRefund);
			// 设置trade状态
			for (Entry<String, String> entry : ConstEC.ORDERREFUNDSTATUSMAPPING.entrySet()) {
				if (entry.getValue().equals(orderRefund.getRefundStatus())) {
					refund.setStatus(entry.getKey());
				}
			}
			refund.setCreated(DateTimeUtil.formatTimestamp2String(
					new Date(Long.parseLong(orderRefund.getCreateTime())), "yyyy-MM-dd HH:mm:ss"));
			refund.setModified(DateTimeUtil.formatTimestamp2String(
					new Date(Long.parseLong(orderRefund.getUpdateTime())), "yyyy-MM-dd HH:mm:ss"));
			if (StringUtils.isEmpty(refund.getReason())) {
				refund.setReason("退款原因无");
			}
			refunds.add(refund);
		}
		return this.successHandle("refund", refunds, requestMap, fromJson.getPage(), fromJson.getPageCount(),
				fromJson.getTotal());
	}

	/**
	 * 下载商品
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findGoods(Map<String, Object> requestMap) throws Exception {
		String items = null;
		Gson gson = new Gson();
		items = remoteOrderService.findGoods(requestMap);
		ItemFrom fromJson = null;
		try {
			fromJson = gson.fromJson(items, new TypeToken<ItemFrom>() {
			}.getType());
		} catch (Exception e) {
			// 解析异常
			throw new BusiException("0013", "查询商品失败");
		}
		List<Item> itemList = new LinkedList<Item>();
		Item item = null;
		for (OrderGoods orderGoods : fromJson.getResult()) {
			item = FieldUtil.filedSet(Item.class, "kingdee.orderGoods.", orderGoods);
			item.setApprove_status(ConstEC.APPROVE_STATUS.get(orderGoods.getVisible()));
			itemList.add(item);
		}
		return this.successHandle("item", itemList, requestMap, fromJson.getPage(), fromJson.getPageCount(),
				fromJson.getTotal());
	}

	// order系统异常数据解析
	private Map<String, Object> errorHandle(Gson gson, String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = gson.fromJson(result, new TypeToken<Map<String, String>>() {
		}.getType());
		map1.put("sub_code", map2.get("code"));
		map1.put("sub_msg", map2.get("msg"));
		map.put("error_response", map1);
		return map;
	}

	// 组装结果
	private Map<String, Object> successHandle(String target, List<? extends Object> result,
			Map<String, Object> requestMap, int page, int pageCount, int total) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put(target, result);
		// 是否启用has_next的分页方式
		if (requestMap.get("use_has_next") != null
				&& "true".equalsIgnoreCase(requestMap.get("use_has_next").toString())) {
			// 当前页大于等于最后一页
			if (page >= pageCount) {
				map1.put("has_next", "false");
			} else {
				map1.put("has_next", "true");
			}
		} else {
			map1.put("total_results", total);
		}
		map1.put(target + "s", map2);
		map.put(target + "s_get_response", map1);
		return map;
	}

}
