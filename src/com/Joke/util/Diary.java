package com.Joke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
/**
 * 实现将用户的记录的日志写入到本地瓷盘中
 * @author Administrator
 *
 */
public class Diary {
	
	public static void addDiary(String pathname,String title,String txt){
		//pathname是以用户名命名的文件夹
		File dirFile = new File(pathname);
		BufferedWriter write = null;
		//建立文件夹
		dirFile.mkdirs();
		//建立日志文件，后缀为.kz
		File file = new File(dirFile, title+".kz");
		//写入文件
		try {
			write = new BufferedWriter(new FileWriter(file,true));
			write.write(txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(write == null){
				try {
					write.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void read(File fie,Document doc){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fie));
			String txt = null;
			String line = System.getProperty("line.separator");
			while((txt = reader.readLine())!= null){
				doc.insertString(doc.getLength(),txt+line,null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

}
