package com.mlx.orderpre.model;

public class Refund {
	private String refund_id;
	private String tid;
	private String oid;
	// private String total_fee;
	private String created;
	private String modified;
	private String status;
	// private String has_good_return;
	// private String payment;
	private String reason;

	// private String desc;

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason == null ? "正在审核退款" : reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
