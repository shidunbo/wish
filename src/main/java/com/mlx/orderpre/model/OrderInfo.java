package com.mlx.orderpre.model;

import java.util.List;

public class OrderInfo {
	private String id;

	private String userId;

	private String userName;

	private String clientIp;

	private String orderId;

	private String orderDate;

	private String orderTime;

	private String orderType;

	private String orderStatus;

	private String totalMarketPrice;
	private String totalSellPrice;

	private String payFee;

	private String refundFlag;

	private String refundFee;

	private String currency;

	private String expTime;

	private String sysCnl;

	private String respCode;

	private String respMsg;

	private String remark;
	private String orderJnId;

	public String getOrderJnId() {
		return orderJnId;
	}

	public void setOrderJnId(String orderJnId) {
		this.orderJnId = orderJnId;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column order.create_time
	 *
	 * @mbggenerated Wed Mar 16 15:06:32 CST 2016
	 */
	private String createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column order.update_time
	 *
	 * @mbggenerated Wed Mar 16 15:06:32 CST 2016
	 */
	private String updateTime;

	/**
	 * 订单商品
	 */
	private List<OrderGoods> orderGoods;

	private String startDate;
	private String endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTotalMarketPrice() {
		return totalMarketPrice;
	}

	public void setTotalMarketPrice(String totalMarketPrice) {
		this.totalMarketPrice = totalMarketPrice;
	}

	public String getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(String totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

	public String getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExpTime() {
		return expTime;
	}

	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}

	public String getSysCnl() {
		return sysCnl;
	}

	public void setSysCnl(String sysCnl) {
		this.sysCnl = sysCnl;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}