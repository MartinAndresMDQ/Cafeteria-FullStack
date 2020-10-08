package com.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafeteria.dao.MessageDao;
import com.cafeteria.model.Message;

@Service("MessageService")
@Transactional(value = "transactionManager")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao dao;

	@Override
	public Message saveOrUpdate(Message dato) {
		return dao.saveOrUpdate(dato);
	}

	@Override
	public void delete(int id) {
		Message dato = this.get(id);
		if (dato != null) {
			dao.delete(dato);
		}
	}

	@Override
	// @Transactional
	public Message get(int id) {
		return dao.get(id);
	}

	@Override
	// @Transactional
	public List<Message> getAlls() {
		return dao.getAlls();
	}

	@Override
	public int cant() {
		return dao.cant();
	}

	@Override
	public Message getLast() {
		return dao.getLast();
	}

	@Override
	public Message getFirst() {
		return dao.getFirst();
	}

	@Override
	public List<Message> getMessagesNotView(int id) {
		// TODO Auto-generated method stub
		return dao.getMessagesNotView(id);
	}
}
