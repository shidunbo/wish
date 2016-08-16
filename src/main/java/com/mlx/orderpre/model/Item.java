package com.mlx.orderpre.model;

public class Item {
	private String num_iid; // 商品数字Id。

	// private String num; //商品数量
	private String outer_id; // 商家设置的外部Id。

	private String price;// 商品价格，格式：5.00；单位：元；精确到：分
	// private String skus; // Sku列表
	private String approve_status; // 商品上传后的状态。商品上传后的状态。onsale出售中，instock库中
	// private String barcode; // 商品条形码。
	private String title; // 商品标题，不能超过60字节
	// private String desc; // 商品描述。
	// private String created; // 商品创建时间。（格式：yyyy-MM-dd HH:mm:ss）
	private String modified; // 商品创建时间。（格式：yyyy-MM-dd HH:mm:ss）

	// private String pic_url; // 商品主图片地址。
	// private String detail_url; // 商品URL链接。
	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getOuter_id() {
		return outer_id;
	}

	public void setOuter_id(String outer_id) {
		this.outer_id = outer_id;
	}

}
