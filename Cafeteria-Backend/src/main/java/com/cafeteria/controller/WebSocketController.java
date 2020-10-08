package com.cafeteria.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.cafeteria.model.Message;
import com.cafeteria.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WebSocketController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private MessageService service;

	@Autowired
	WebSocketController(SimpMessagingTemplate template) {
		this.simpMessagingTemplate = template;
	}

//	@MessageMapping("/send/message")
//	public void sendMessage(Message message) {
//		try {
//			message = service.saveOrUpdate(message);
//			System.out.println(message);
//			this.template.convertAndSend("/message", message);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//
//
	@MessageMapping("/send/message")
	public void sendMessage(String cadena) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageConverted = null;
		Message message = null;
		try {
			messageConverted = mapper.readValue(cadena, Map.class);
			message = mapper.readValue(cadena, Message.class);
			if (message != null) {

//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date d = sdf.parse(message.getFecha().get("date"));
//				message = new Mensaje(0, output.parse(output.format(d)), messageConverted.get("message"),
//						serviceFranquicia.Traer(Integer.parseInt(messageConverted.get("fromId"))),
//						serviceFranquicia.Traer(Integer.parseInt(messageConverted.get("toId"))), false);

				if (message != null && message.getMessage() != null && !message.getMessage().equals("")) {

					List<Message> lista = service.getMessagesNotView(message.getEnvia().getId());

					System.out.println(message.toString());
					message = service.saveOrUpdate(message);

					for (Message mensa : lista) {
						if (mensa.getEnvia().getId() == message.getRecibe().getId()) {
							mensa.setVisto(true);
							mensa = service.saveOrUpdate(mensa);
						}
					}
				}

				this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + message.getRecibe().getId(), message);
				this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + message.getEnvia().getId(), message);

//				if (messageConverted.containsKey("toId") && message.getRecibe() != null
//						&& !messageConverted.get("toId").equals("")) {
//					this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("toId"),
//							messageConverted);
//				} else {
//					String messageConverted2 = mapper.writeValueAsString(message);
//					this.simpMessagingTemplate.convertAndSend("/socket-publisher", messageConverted);
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return messageConverted;
	}

}