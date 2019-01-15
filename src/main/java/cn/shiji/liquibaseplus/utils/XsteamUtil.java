package cn.shiji.liquibaseplus.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author: Eric
 * @date: 2019-01-15 09:24
 **/
public class XsteamUtil {
	public static Object toBean(Class<?> clazz, String xml) {
		Object xmlObject = null;
//		XStream xstream = new XStream();
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(clazz);
		xstream.autodetectAnnotations(true);
		xmlObject= xstream.fromXML(xml);
		return xmlObject;
	}
}
