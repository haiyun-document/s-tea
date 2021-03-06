package org.sky.auto.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sky.auto.load.SourceLoader;





public class XMLLoader {
	private static Logger logger = Logger.getLogger(XMLLoader.class);
	private static Map<String,XMLElement> xmlmap = new HashMap<String,XMLElement>();
	
	public static List<String> getXMLs(String path){
		XMLScaner scaner = new XMLScaner(path);
		return scaner.getXMLFiles();
	}
	
	public static Map<String, XMLElement> load(String basepath){
		if(xmlmap.size()==0){
			logger.info("开始对xml资源进行扫描....");
			List<String> list = getXMLs(basepath);
			for(String path:list){
				XMLDocument xd = new XMLDocument(path);
				XMLElements xe = new XMLElements(xd);
				for(XMLElement xn :xe.getAllXMLElement()){
					SourceLoader.add(xn);
					xmlmap.put(xn.getId(),xn);
					logger.info("扫描收集了资源->"+xn.getId());
				}
			}
			//Log.Debug("查找到"+xmlmap.size()+"个元素！");
			logger.info("扫描XML资源完毕...");
		}else{
			return xmlmap;
		}
		return xmlmap;
	}
	
	public static XMLElement getXMLElement(String id){
		return xmlmap.get(id);
	}
	
	/**清空xml加载器的资源*/
	public static void clear(){
		xmlmap.clear();
	}
	
	public static Map<String, XMLElement> load(){
		if(xmlmap.size()==0){
			logger.info("开始对xml资源进行扫描....");
			List<String> list = getXMLs("xml");
			for(String path:list){
				XMLDocument xd = new XMLDocument(path);
				XMLElements xe = new XMLElements(xd);
				for(XMLElement xn :xe.getAllXMLElement()){
					SourceLoader.add(xn);
					xmlmap.put(xn.getId(),xn);
					
					logger.info("扫描收集了资源->"+xn.getId());
				}
			}
			//Log.Debug("查找到"+xmlmap.size()+"个元素！");
			logger.info("扫描XML资源完毕...");
		}else{
			return xmlmap;
		}
		return xmlmap;
	}
	
	
}
