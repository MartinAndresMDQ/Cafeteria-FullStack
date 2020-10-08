package com.cafeteria.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/socket")
@CrossOrigin("*")
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/send/message")
	public Map<String, String> sendMessage(String cadena) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageConverted = null;
//		Message message = null;
		try {
			messageConverted = mapper.readValue(cadena, Map.class);
			if (messageConverted != null) {
				this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("toId"),
						messageConverted);
//				this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("fromId"),
//						messageConverted);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageConverted;
	}

}