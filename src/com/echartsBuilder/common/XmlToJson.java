package com.echartsBuilder.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONObject;
public class XmlToJson {
	public JSONObject convertXmlToJson(String str,String path){
		JSONObject jobj = new JSONObject();
		try {
			InputStream stream = new FileInputStream(new File(path));
			//读取xml文件
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(stream);
			Element element= doc.getRootElement();
			List list = element.elements(str);
			for(int i= 0 ; i < list.size(); i ++){
				//解析xml转化为json
				Element ele = (Element) list.get(i);
				List<Element> items = ele.elements();
				for(int j = 0 ; j < items.size() ; j++){
					Element item = items.get(j);
					jobj.put(item.getName(), item.getTextTrim());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put(str, jobj);
		return json;
	}
}
