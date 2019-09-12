package com.cmd.entity;

public class Orders {
	private String order;
	private String source;
	private String target;
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "Orders [order=" + order + ", source=" + source + ", target=" + target + "]";
	}	
}
