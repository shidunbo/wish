package com.mlx.orderpre.from;

import java.util.List;

import com.mlx.orderpre.model.OrderJn;

public class OrderJnFrom {

	private String code;

	private String msg;

	private Integer page;

	private Integer pageSize;

	private Integer pageCount;

	private Integer rowCount;

	private Integer total;

	private List<OrderJn> result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<OrderJn> getResult() {
		return result;
	}

	public void setResult(List<OrderJn> result) {
		this.result = result;
	}

}