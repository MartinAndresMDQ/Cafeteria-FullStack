package com.cafeteria.dao;

import java.util.List;

import com.cafeteria.model.Message;

public interface MessageDao extends GenericDAO<Message> {

	List<Message> getMessagesNotView(int id);

}
