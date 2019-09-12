package com.cmd.service;

import java.io.File;

import com.cmd.entity.Orders;
import com.cmd.io.Operation;
import com.cmd.util.Parser;

public class Service {
	private Operation op = new Operation();

	// 解析用户指令
	public Orders getOrder(File source, String str) {
		Orders order = Parser.parseOrder(str);
		if (order.getSource() != null) {
			order.setSource(Parser.parsePath(source, order.getSource()));
		}
		if (order.getTarget() != null) {
			order.setTarget(Parser.parsePath(source, order.getTarget()));
		}
		return order;
	}

	// 根据指令调用io操作
	public File doOrder(File file, Orders order) {
		if (order != null) {
			if ("cd".equals(order.getOrder())) {
				file = op.cd(file, order.getSource());
			} else if ("ls".equals(order.getOrder())) {
				op.ls(file);
			} else if ("mkdir".equals(order.getOrder())) {
				op.mkdir(file, order.getSource());
			} else if ("rm".equals(order.getOrder())) {
				op.rm(new File(order.getSource()));
			} else if ("cat".equals(order.getOrder())) {
				op.cat(new File(order.getSource()));
			} else if ("cp".equals(order.getOrder())) {
				op.cp(order.getSource(), order.getTarget());
			} else if ("mv".equals(order.getOrder())) {
				op.mv(order.getSource(), order.getTarget());
			} else if ("help".equals(order.getOrder())) {
				op.help();
			} else if ("exit".equals(order.getOrder())) {
				op.exit();
			} else {
				System.err.println("指令错误,输入help试试");
			}
		} else {
			System.err.println("指令错误,输入help试试");
		}
		return file;
	}
	// 方法组装
	public File begin(File file,String str) {
		Orders order = getOrder(file,str);
		return doOrder(file,order);
	}
}
