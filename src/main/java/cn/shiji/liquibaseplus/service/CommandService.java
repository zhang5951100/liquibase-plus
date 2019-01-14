package cn.shiji.liquibaseplus.service;

import cn.shiji.liquibaseplus.model.WinCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Eric
 * @date: 2019-01-11 19:03
 **/
@Slf4j
@Service
public class CommandService {

	public void init(WinCommand winCommand) {
		WinCommand instance = WinCommand.getInstance();
		BeanUtils.copyProperties(winCommand, instance);
		instance.setChangeLogFile("changelog.xml");
		switch (winCommand.getDriver()) {
			case "org.postgresql.Driver":
				instance.setClasspath("lib/postgresql.jar");
				break;
			case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
				instance.setClasspath("lib/mssql.jar");
				break;
			case "com.mysql.cj.jdbc.Driver":
				instance.setClasspath("lib/mysql.jar");
				break;
			case "org.h2.Driver":
				instance.setClasspath("lib/h2.jar");
				break;
			default:
				break;
		}

	}

	public String execute(WinCommand winCommand) {

		WinCommand instance = WinCommand.getInstance();

		if (StringUtils.equals(" generateChangeLog", winCommand.getCommand())) {
			instance.changeLogFile = winCommand.getParameter();
		}

		String command = "cmd /c liquibase "
				//changeLog xml文件路径
				+ "--changeLogFile=" + instance.changeLogFile
				//数据库驱动
				+ " --driver=" + instance.driver
				//驱动文件路径
				+ " --classpath=" + instance.classpath
				//数据库url
				+ " --url=" + instance.url
				//数据库username
				+ " --username=" + instance.username
				//数据库password
				+ " --password=" + instance.password
				//liquibase操作命令(update,rollbackTag等)
				+ winCommand.command;
		if (StringUtils.isNotBlank(winCommand.getParameter()) && !StringUtils.equals(" generateChangeLog", winCommand.getCommand())) {
			command = command + "=" + winCommand.getParameter();
		}

		String line;
		StringBuilder sb = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();

		try {
			log.info(command);
			Process process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append("\n");
				log.info("cmd response:{}" + line);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return sb.toString();
	}
}
