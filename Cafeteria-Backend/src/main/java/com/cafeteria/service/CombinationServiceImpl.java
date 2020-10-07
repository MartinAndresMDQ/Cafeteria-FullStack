package com.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafeteria.dao.CombinationDao;
import com.cafeteria.model.Combination;

@Service("CombinationService")
@Transactional(value = "transactionManager")
public class CombinationServiceImpl implements CombinationService {

//	@Resource(name = "RubroDao")
	@Autowired
	private CombinationDao dao;

	@Override
	public Combination saveOrUpdate(Combination dato) {
		return dao.saveOrUpdate(dato);
	}

	@Override
	public void delete(int id) {
		Combination dato = this.get(id);
		if (dato != null) {
			dao.delete(dato);
		}
	}

	@Override
	// @Transactional
	public Combination get(int id) {
		return dao.get(id);
	}

	@Override
	// @Transactional
	public List<Combination> getAlls() {
		return dao.getAlls();
	}

	@Override
	public int cant() {
		return dao.cant();
	}

	@Override
	public Combination getLast() {
		return dao.getLast();
	}

	@Override
	public Combination getFirst() {
		return dao.getFirst();
	}
}
