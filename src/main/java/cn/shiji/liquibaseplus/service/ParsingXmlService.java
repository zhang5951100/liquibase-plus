package cn.shiji.liquibaseplus.service;

import cn.shiji.liquibaseplus.model.WinCommand;
import cn.shiji.liquibaseplus.model.xml.ChangeSetList;
import cn.shiji.liquibaseplus.utils.XsteamUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author: Eric
 * @date: 2019-01-15 13:24
 **/
@Slf4j
@Service
public class ParsingXmlService {

	/**
	 * xml转实体类
	 */
	public ChangeSetList xml2Bean() throws IOException {
		WinCommand instance = WinCommand.getInstance();
		// 引入changelog.xml
		Resource resource = new FileSystemResource(instance.changeLogFile);
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		br.close();

		return (ChangeSetList) XsteamUtil.toBean(ChangeSetList.class, buffer.toString());
	}

	/**
	 * 实体类转xml
	 */
	public void bean2Xml(ChangeSetList changSetList) throws Exception {
		WinCommand instance = WinCommand.getInstance();
		XStream xStream = new XStream(new XppDriver(new NoNameCoder()));
		PrintWriter writer = null;
		//覆盖已存在的changelog.xml
		writer = new PrintWriter(instance.changeLogFile, "utf-8");
		//自己写xml头部
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		//设置xStream解析注解
		xStream.autodetectAnnotations(true);
		//String类型字段解析为xml属性而为子节点
		xStream.useAttributeFor(String.class);
		//设置类的别名
		xStream.alias("databaseChangeLog", ChangeSetList.class);

		xStream.toXML(changSetList, writer);

	}
}
