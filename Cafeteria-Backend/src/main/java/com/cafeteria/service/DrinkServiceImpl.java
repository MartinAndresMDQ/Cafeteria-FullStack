package com.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafeteria.dao.DrinkDao;
import com.cafeteria.model.Drink;

@Service("DrinkService")
@Transactional(value = "transactionManager")
public class DrinkServiceImpl implements DrinkService {

//	@Resource(name = "RubroDao")
	@Autowired
	private DrinkDao dao;

	@Override
	public Drink saveOrUpdate(Drink dato) {
		return dao.saveOrUpdate(dato);
	}

	@Override
	public void delete(int id) {
		Drink dato = this.get(id);
		dato.setHidden(true);
		if (dato != null) {
			dao.saveOrUpdate(dato);
		}
	}

	@Override
	// @Transactional
	public Drink get(int id) {
		return dao.get(id);
	}

	@Override
	// @Transactional
	public List<Drink> getAlls() {
		return dao.getAlls();
	}

	@Override
	public int cant() {
		return dao.cant();
	}

	@Override
	public Drink getLast() {
		return dao.getLast();
	}

	@Override
	public Drink getFirst() {
		return dao.getFirst();
	}
}
