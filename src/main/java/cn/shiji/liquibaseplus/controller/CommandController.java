package cn.shiji.liquibaseplus.controller;

import cn.shiji.liquibaseplus.model.WinCommand;
import cn.shiji.liquibaseplus.model.xml.ChangeSet;
import cn.shiji.liquibaseplus.model.xml.ChangeSetList;
import cn.shiji.liquibaseplus.service.CommandService;
import cn.shiji.liquibaseplus.service.ParsingXmlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author: Eric
 * @date: 2019-01-11 18:49
 **/
@Slf4j
@RestController
@RequestMapping("/command")
public class CommandController {

	@Autowired
	private CommandService commandService;

	@Autowired
	private ParsingXmlService parsingXmlService;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 初始化配置
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity init(@RequestBody WinCommand winCommand) {

		try {
			log.info("WinCommand request:{}", objectMapper.writeValueAsString(winCommand));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		commandService.init(winCommand);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 读取配置详情
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity getDetail() {
		WinCommand instance = WinCommand.getInstance();
		return new ResponseEntity<>(instance, HttpStatus.OK);
	}

	/**
	 * 执行命令
	 */
	@RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity execute(@RequestBody WinCommand winCommand) {
		String result = commandService.execute(winCommand);
		if (StringUtils.isNotBlank(result)) {
			try {
				return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.OK);
			} catch (JsonProcessingException ignored) {

			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 读取changelog信息
	 */
	@RequestMapping(value = "/changeLog", method = RequestMethod.GET)
	public ResponseEntity getChangelog() {
		ChangeSetList changeSetList = null;
		try {
			changeSetList = parsingXmlService.xml2Bean();
		} catch (IOException e) {
			log.error("读取changelog.xml信息失败:{}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(changeSetList, HttpStatus.OK);
	}

	/**
	 * 写入changeSet
	 */
	@RequestMapping(value = "/changeLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity addChangeSet(@RequestBody ChangeSet changeSet) {
		try {
			log.info(objectMapper.writeValueAsString(changeSet));
		} catch (JsonProcessingException ignored) {
		}
		ChangeSetList changeSetList = null;
		try {
			changeSetList = parsingXmlService.xml2Bean();
		} catch (IOException e) {
			log.error("读取changelog.xml失败:{}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (changeSetList != null) {
			changeSetList.getChangeSetList().add(changeSet);
		}
		try {
			parsingXmlService.bean2Xml(changeSetList);
		} catch (Exception e) {
			log.error("写入changelog失败:{}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}