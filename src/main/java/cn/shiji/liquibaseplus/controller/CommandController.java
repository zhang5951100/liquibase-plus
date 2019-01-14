package cn.shiji.liquibaseplus.controller;

import cn.shiji.liquibaseplus.model.WinCommand;
import cn.shiji.liquibaseplus.service.CommandService;
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
	private ObjectMapper objectMapper;

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

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity getDetail() {
		WinCommand instance = WinCommand.getInstance();
		return new ResponseEntity<>(instance, HttpStatus.OK);
	}

	@RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity execute(@RequestBody WinCommand winCommand) {
		String result = commandService.execute(winCommand);
		if (StringUtils.isNotBlank(result)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}