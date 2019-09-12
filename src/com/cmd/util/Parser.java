package com.cmd.util;

import java.io.File;

import com.cmd.entity.Orders;

public class Parser {
	/*
	 * 解析路径 将路径格式化
	 */
	public static String parsePath(File file, String url) {
		File ftemp=null;
		//绝对路径
		if(url.contains(":")) {
			ftemp = new File(url);
		}else {
			ftemp = new File(file.getAbsolutePath());
			String[] temp = url.split("/");
			for (String string : temp) {
				if (string.equals(".") || string.equals("")) {
					// ./ / 代表当前路径
					// 啥也不做
				} else if (string.equals("..")) {
					ftemp = ftemp.getParentFile();
				} else {
					ftemp = new File(ftemp.getAbsoluteFile() + "\\" + string);
				}
			}
		}
		return ftemp.getAbsolutePath();
	}

	/*
	 * 解析指令 将指令格式化
	 */
	public static Orders parseOrder(String str) {
		Orders or = new Orders();
		// 去掉首部空格
		char[] temp = str.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != ' ') {
				if (i != 0) {
					str = new String(temp, i, temp.length - i - 1);
				}
				break;
			}
		}
		if (str.startsWith("cd")) {
			or.setOrder("cd");
			str = new String(temp, 3, temp.length - 3).replaceAll(" ", "");
			or.setSource(str);
			or.setTarget(null);

		} else if (str.startsWith("ls")) {
			or.setOrder("ls");
			or.setSource(null);
			or.setTarget(null);
		} else if (str.startsWith("mkdir")) {
			or.setOrder("mkdir");
			str = new String(temp, 6, temp.length - 6).replaceAll(" ", "");
			or.setSource(str);
			or.setTarget(null);
		} else if (str.startsWith("rm")) {
			or.setOrder("rm");
			str = new String(temp, 3, temp.length - 3).replaceAll(" ", "");
			or.setSource(str);
			or.setTarget(null);
		} else if (str.startsWith("cat")) {
			or.setOrder("cat");
			str = new String(temp, 4, temp.length - 4).replaceAll(" ", "");
			or.setSource(str);
			or.setTarget(null);
		} else if (str.startsWith("cp")) {
			str = new String(temp, 3, temp.length - 3);
			or.setOrder("cp");
			String[] ta = str.split(" ");
			for (int i = 0; i < ta.length; i++) {
				if (!ta[i].equals("") && or.getSource() == null) {
					or.setSource(ta[i]);
				} else if (!ta[i].equals(" ") && or.getSource() != null) {
					or.setTarget(ta[i]);
					break;
				}
			}
		} else if (str.startsWith("mv")) {
			str = new String(temp, 3, temp.length - 3);
			or.setOrder("mv");
			String[] ta = str.split(" ");
			for (int i = 0; i < ta.length; i++) {
				if (!ta[i].equals("") && or.getSource() == null) {
					or.setSource(ta[i]);
				} else if (!ta[i].equals(" ") && or.getSource() != null) {
					or.setTarget(ta[i]);
					break;
				}
			}
		} else if (str.startsWith("help")) {
			or.setOrder("help");
			or.setSource(null);
			or.setTarget(null);
		} else if (str.startsWith("exit")) {
			or.setOrder("exit");
			or.setSource(null);
			or.setTarget(null);
		} else {
			or.setOrder("error");
			or.setSource(null);
			or.setTarget(null);

		}
		return or;
	}
}
