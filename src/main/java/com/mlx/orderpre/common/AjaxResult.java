package com.mlx.orderpre.common;

public class AjaxResult {

	private String sub_code;
	private String sub_msg;

	public AjaxResult() {
		super();
	}

	public AjaxResult(String sub_code, String sub_msg) {
		super();
		this.sub_code = sub_code;
		this.sub_msg = sub_msg;

	}

	public String getSub_code() {
		return sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getSub_msg() {
		return sub_msg;
	}

	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}

}