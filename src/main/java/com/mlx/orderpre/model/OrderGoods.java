package com.mlx.orderpre.model;

import org.springframework.util.StringUtils;

public class OrderGoods {
	private String id;

	private String orderId;

	private String goodsId;

	private String goodsName;

	private String goodsType;
	private String groupNo;

	private String tripDate;

	private String adultMarketPrice;

	private String adultSellPrice;

	private String childMarketPrice;

	private String childSellPrice;

	private String singleRoomPrice;
	private String singleRoomNum;

	private String visaNum;

	private String childNum;

	private String adultNum;

	private String goodsNum;

	private String childSellCount;

	private String adultSellCount;

	private String skuId;
	private String remark;

	private String visible;// 1上架，2下架
	private String createTime;
	private String price;
	private String updateTime;

	private String modified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGroupId() {
		return groupNo;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGoodsNum() {
		return StringUtils.isEmpty(goodsNum) ? "0" : goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public void setGroupId(String groupId) {
		this.groupNo = groupId;
	}

	public String getAdultMarketPrice() {
		return adultMarketPrice;
	}

	public void setAdultMarketPrice(String adultMarketPrice) {
		this.adultMarketPrice = adultMarketPrice;
	}

	public String getAdultSellPrice() {
		return adultSellPrice;
	}

	public void setAdultSellPrice(String adultSellPrice) {
		this.adultSellPrice = adultSellPrice;
	}

	public String getChildMarketPrice() {
		return childMarketPrice;
	}

	public void setChildMarketPrice(String childMarketPrice) {
		this.childMarketPrice = childMarketPrice;
	}

	public String getChildSellPrice() {
		return childSellPrice;
	}

	public void setChildSellPrice(String childSellPrice) {
		this.childSellPrice = childSellPrice;
	}

	public String getSingleRoomPrice() {
		return singleRoomPrice;
	}

	public void setSingleRoomPrice(String singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	public String getSingleRoomNum() {
		return singleRoomNum;
	}

	public void setSingleRoomNum(String singleRoomNum) {
		this.singleRoomNum = singleRoomNum;
	}

	public String getVisaNum() {
		return visaNum;
	}

	public void setVisaNum(String visaNum) {
		this.visaNum = visaNum;
	}

	public String getChildNum() {
		return StringUtils.isEmpty(childNum) ? "0" : childNum;
	}

	public void setChildNum(String childNum) {
		this.childNum = childNum;
	}

	public String getAdultNum() {
		return StringUtils.isEmpty(adultNum) ? "0" : adultNum;
	}

	public void setAdultNum(String adultNum) {
		this.adultNum = adultNum;
	}

	public String getChildSellCount() {
		return childSellCount;
	}

	public void setChildSellCount(String childSellCount) {
		this.childSellCount = childSellCount;
	}

	public String getAdultSellCount() {
		return adultSellCount;
	}

	public void setAdultSellCount(String adultSellCount) {
		this.adultSellCount = adultSellCount;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
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

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
}