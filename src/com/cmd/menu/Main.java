package com.cmd.menu;

import java.io.File;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import com.cmd.service.Service;

//程序入口
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Service service = new Service();
		// 默认启动路径为桌面
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File file = fsv.getHomeDirectory();
		while(true) {
			System.out.print(file.getPath() + ">:");
			String str  = sc.nextLine();
			file = service.begin(file,str);
		}
	}
}
