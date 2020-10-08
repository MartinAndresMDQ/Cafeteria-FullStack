package com.cafeteria.service;

import java.util.List;

import com.cafeteria.model.Message;

public interface MessageService extends GenericService<Message> {

	List<Message> getMessagesNotView(int id);

}
