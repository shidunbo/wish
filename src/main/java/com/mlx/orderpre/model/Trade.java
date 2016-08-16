package com.mlx.orderpre.model;

import java.util.List;
import java.util.Map;

public class Trade {
	private String tid; // 交易编号。
	// private String seller_nick; //卖家昵称。
	private String buyer_nick; // 买家昵称
	private String payment; // 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分。
	private String post_fee; // 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分。
	// private String discount_fee;
	// private String total_fee;
	private String receiver_name;
	private String receiver_state;
	private String receiver_address;
	private String receiver_zip;
	private String receiver_mobile;
	private String receiver_phone;
	private String receiver_city;
	private String receiver_district;
	// private String consign_time;
	private Map<String, List<Order>> orders;
	private String status;
	private String created;
	// private String pay_time;
	private String modified;

	// private String end_time;
	// private String buyer_message;
	// private String buyer_memo;
	// private String seller_memo;
	// private String invoice_name;
	// private String invoice_type;
	private String buyer_paymode;

	public String getBuyer_paymode() {
		return buyer_paymode;
	}

	public void setBuyer_paymode(String buyer_paymode) {
		this.buyer_paymode = buyer_paymode;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getBuyer_nick() {
		return buyer_nick;
	}

	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}

	public String getPost_fee() {
		return post_fee;
	}

	public void setPost_fee(String post_fee) {
		this.post_fee = post_fee;
	}

	public Map<String, List<Order>> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, List<Order>> orders) {
		this.orders = orders;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_state() {
		return receiver_state;
	}

	public void setReceiver_state(String receiver_state) {
		this.receiver_state = receiver_state;
	}

	public String getReceiver_zip() {
		return receiver_zip;
	}

	public void setReceiver_zip(String receiver_zip) {
		this.receiver_zip = receiver_zip;
	}

	public String getReceiver_mobile() {
		return receiver_mobile;
	}

	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getReceiver_city() {
		return receiver_city;
	}

	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}

	public String getReceiver_district() {
		return receiver_district;
	}

	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

}
