package com.mlx.orderpre.model;

public class Order {
	private String oid;
	private String outer_iid;
	private String title;
	private String price;
	private String num_iid;
	// private String outer_id;

	private String sku_id;
	private String num;
	// private String outer_sku_id;
	private String total_fee;
	private String payment;

	// private String discount_fee;
	// private Date modified;
	// private String sku_properties_name;
	// private Date end_time;
	// private Date consign_time;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getOuter_iid() {
		return outer_iid;
	}

	public void setOuter_iid(String outer_iid) {
		this.outer_iid = outer_iid;
	}

}
