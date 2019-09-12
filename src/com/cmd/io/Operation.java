package com.cmd.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 统一不处理请求路径，参数路径全部为绝对路径
 * 全部采用单线程，虽然这是io操作的大忌
 */
public class Operation {
	/*
	 * 查看目录所有文件 ls
	 */
	public void ls(File file) {
		String[] childList = file.list();
		for (String string : childList) {
			System.out.println(string);
		}
	}

	/*
	 * 切换目录 cd
	 */
	public File cd(File file, String url) {
		File target = new File(url);
		if(!target.exists()) {
			System.err.println("文件夹不存在");
			return file;
		}else {
			return target;
		}
	}

	/*
	 * 创建文件或文价夹 mkdir
	 */
	public void mkdir(File file, String FileName) {
		if (FileName == null || FileName.equals("")) {
			System.err.println("文件名错误");
		} else {
			if (FileName.contains(".")) {
				// 创建文件
				File target = new File(FileName);
				try {
					target.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("创建文件失败");
				}
			} else {
				// 创建文件夹
				File target = new File(FileName);
				target.mkdir();
			}
		}
	}

	/*
	 * 删除文件 rm
	 */
	public void rm(File file) {
		if (file != null&&file.exists()) {
			if (file.isFile()) {
				// 删除文件
				file.delete();
			} else if (file.isDirectory()) {
				// 深度删除文件夹
				File[] childfiles = file.listFiles();
				for (File file2 : childfiles) {
					rm(file2);
				}
				file.delete();
			}
		}else {
			System.err.println("文件不存在");
		}
	}

	/*
	 * 查看文件(采用utf8读取) cat
	 */
	public void cat(File file) {
		if (file == null || !file.exists()) {
			System.err.println("文件不存在");
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("读取失败");
		}
	}

	/*
	 * 复制文件 cp
	 */
	public void cp(String source, String target) {
		File fs = new File(source);
		File ft = new File(target);
		try {
			if (!fs.exists()) {
				System.err.println("源文件不存在");
				return;
			} else {
				if (!ft.exists()) {
					ft.createNewFile();
				}
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fs), "utf8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ft),"utf8"));
			String line;
			while ((line = br.readLine()) != null) {
				bw.write(line+'\n');
				bw.flush();
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			System.err.println("读取失败");
		}
	}
	
	/*
	 *移动命令
	 *mv 
	 */
	public void mv(String source, String target) {
		cp(source, target);
		File fs = new File(source);
		fs.delete();
	}
	/*
	 * 显示命令信息
	 * help
	 */
	public void help() {
		System.out.println("仿照linux的命令行小工具");
		System.out.println("查看目录所有文件：ls");
		System.out.println("切换目录：cd 路径");
		System.out.println("创建文件或文价夹：mkdir");
		System.out.println("删除文件：rm 路径");
		System.out.println("查看文件：cat 文件");
		System.out.println("复制文件：cp 源地址 目标地址");
		System.out.println("移动文件：mv 源文件 目标地址");
		System.out.println("退出：exit");
	}
	/*
	 * 退出
	 * exit
	 */
	public void exit() {
		//正常退出程序
		System.exit(1);
	}
}
