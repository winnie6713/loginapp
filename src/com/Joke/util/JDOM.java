package com.Joke.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class JDOM { 
	
	//ע���û���Ϣ
	public static String Write(String n,String p,String id){
		/**UserInfo.xml�ĵ���·��*/
		String path = "./UserInfo.xml";
		/**��xml�ĵ���װ��file*/
		File file = new File(path);
		/**ʹ��Ĭ�ϵ�sax������*/
		SAXBuilder sax = new SAXBuilder();
		Document doc;
		try {
			/**Ԫ�ض�Ӧ��xml�ĵ��о��Ǳ�ǩ*/
			doc = sax.build(file);
			//�õ���Ԫ��
			Element root = doc.getRootElement();
			//����userԪ��
			Element user = new Element("User");
			Element name = new Element("name");
			Element passwd = new Element("passwd");
			
			/**���ȼ��xml�ĵ����Ƿ��Ѿ�������ID����ͬ���û�����������ڲſ��Լ���ע��*/
			if(checkID(id,root)){
				user.setAttribute(new Attribute("id", id));
				name.setText(n);
				passwd.setText(p);
				
				user.addContent(name);
				user.addContent(passwd);
				
				root.addContent(user);
				
				XMLOutputter out = new XMLOutputter();
				out.output(doc, new FileOutputStream(file));
				return "successful registration";
			}else{
				return "ID already exists,please input again";
			}
			
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		
		
		return "ERROR";
		
		
	}

	private static boolean checkID(String id, Element root) {
		boolean flag = true;
		List<Element> list = root.getChildren("User");
		Iterator<Element> iterator = list.iterator();
		while(iterator.hasNext()){
			Element element = (Element)iterator.next();
			if(element.getAttribute("id").equals(id)){
				flag = false;
			}
		}
		
		return flag;
	}
	
	/**
	 * ��ȡxml�ĵ����ڵ�¼
	 */
	public static String read(String id,String passWord){
		String path = "./UserInfo.xml";
		File file = new File(path);
		SAXBuilder sax = new SAXBuilder();
		try {
			Document doc = sax.build(file);
			Element root = doc.getRootElement();
			String info = getPasswd(root).get(id);
			if(info == null){
				return "user does not exist";
			}
			
			String[] buf = info.split("/");
			if(buf[0].equals(passWord)){
				return "Sucessful landing/"+buf[1];
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "wrong password";
	}

	private static Map<String, String> getPasswd(Element root) {
		Map<String, String> map = new TreeMap<String, String>();
		List<Element> list = new ArrayList<Element>();
		list = root.getChildren("User");
		Iterator<Element> it = list.iterator();
		while(it.hasNext()){
			Element e = it.next();
			String id = e.getAttributeValue("id");
			String passwd = e.getChildText("passwd");
			String name = e.getChildText("name");
			map.put(id, getInfo(passwd,name));
			
		}
		return  map;
	}

	private static String getInfo(String passwd, String name) {
		// TODO Auto-generated method stub
		return passwd+"/"+name;
	}

	
}
