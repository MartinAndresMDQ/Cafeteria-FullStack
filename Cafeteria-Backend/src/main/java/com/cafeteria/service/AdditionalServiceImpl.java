package com.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafeteria.dao.AdditionalDao;
import com.cafeteria.model.Additional;

@Service("AdditionalService")
@Transactional(value = "transactionManager")
public class AdditionalServiceImpl implements AdditionalService {

	@Autowired
	private AdditionalDao dao;

	@Override
	public Additional saveOrUpdate(Additional dato) {
		return dao.saveOrUpdate(dato);
	}

	@Override
	public void delete(int id) {
		Additional dato = this.get(id);
		dato.setHidden(true);
		if (dato != null) {
			dao.saveOrUpdate(dato);
		}
	}

	@Override
	// @Transactional
	public Additional get(int id) {
		return dao.get(id);
	}

	@Override
	// @Transactional
	public List<Additional> getAlls() {
		return dao.getAlls();
	}

	@Override
	public int cant() {
		return dao.cant();
	}

	@Override
	public Additional getLast() {
		return dao.getLast();
	}

	@Override
	public Additional getFirst() {
		return dao.getFirst();
	}
}
