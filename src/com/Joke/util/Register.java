package com.Joke.util;

import com.Joke.entity.User;

public class Register {
	static User user = new User();
	
	public static String checkName(String name){
		user.setName(name);
		return null;
	}
	
	public static String checkID(String ID){
		if(ID.matches("\\d{1,8}")){
			user.setID(ID);
			return null;
		}
		else return "ID not conform to the rules";
		
	}
	
	public static String checkPassword(String passwd){
		if(passwd.matches("\\d{6,15}")){
			user.setPassWord(passwd);
			return null;
		}else 
			return "password not conform to the rules";
		
	}
	/**
	 * 如果以上信息都没有错误信息返回，则执行写入用户信息
	 * @param name
	 * @param passwd
	 * @param ID
	 * @return
	 */
	public static String register(String name,String passwd,String ID){
		user.setName(name);
		user.setID(ID);
		user.setPassWord(passwd);
		return (JDOM.Write(user.getName(), user.getPassWord(), user.getID()));
	}
	

}
