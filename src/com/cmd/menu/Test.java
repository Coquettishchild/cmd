package com.cmd.menu;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import com.cmd.io.Operation;
import com.cmd.util.Parser;

//指令清单
public class Test {
	
	public void showList() {
		
	}
	public static void main(String[] args) {
//		 FileSystemView fsv=FileSystemView.getFileSystemView();
//		  //将桌面的那个文件目录赋值给file
//		  File file=fsv.getHomeDirectory();
//		  //输出桌面那个目录的路径
//		  System.out.println(file.getPath());
		File file = new File("C:\\Users\\majie\\Documents\\河大新区网站");
		Parser.parseOrder(" exit  ./a");
//		String path = ParsePath.Parse(file, "postman");
//		System.out.println(path);
//		Operation op = new Operation();
//		op.ls(file);
//		File target = op.cd(file,"images\\logo.png");
//		op.rm(file);
//		op.cat(file);
//		op.cp("C:\\Users\\majie\\Documents\\河大新区网站\\a.html", "C:\\Users\\majie\\Documents\\河大新区网站\\b.html");
//		if(target!=null) {
//			op.ls(target);
//		}
	}
}
