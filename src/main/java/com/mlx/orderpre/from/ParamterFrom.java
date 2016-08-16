package com.mlx.orderpre.from;

public class ParamterFrom {
	private String start_time; // 开始时间
	private String end_time; // 结束时间
	private int page_no; // 页数 默认：1
	private int page_size; // 页大小 默认：100
	private String use_has_next; // 是否使用下一页
	private String status; // 状态
	private String datetype; // 1按创建时间，2按修改时间。
	private String tid; // 订单号
	private String refund_id;// 退款号
	private String method; // API名称
	private String num_iid;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getPage_no() {
		return page_no == 0 ? 1 : page_no;
	}

	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}

	public int getPage_size() {
		return page_size == 0 ? 100 : page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public String getUse_has_next() {
		return use_has_next;
	}

	public void setUse_has_next(String use_has_next) {
		this.use_has_next = use_has_next;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatetype() {
		return datetype;
	}

	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

}
