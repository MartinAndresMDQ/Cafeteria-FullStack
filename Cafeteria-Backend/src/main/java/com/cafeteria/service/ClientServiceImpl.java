package com.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafeteria.dao.ClientDao;
import com.cafeteria.model.Client;
import com.cafeteria.model.Manager;
import com.cafeteria.model.Operator;
import com.cafeteria.model.Supervisor;

@Service("ClientService")
@Transactional(value = "transactionManager")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao dao;

	@Override
	public Client saveOrUpdate(Client dato) {
		return dao.saveOrUpdate(dato);
	}

	@Override
	public void delete(int id) {
		Client dato = this.get(id);
//		dato.setHidden(true);
		if (dato != null) {
			dao.delete(dato);
		}
	}

	@Override
	// @Transactional
	public Client get(int id) {
		return dao.get(id);
	}

	@Override
	// @Transactional
	public List<Client> getAlls() {
		return dao.getAlls();
	}

	@Override
	public int cant() {
		return dao.cant();
	}

	@Override
	public Client getLast() {
		return dao.getLast();
	}

	@Override
	public Client getFirst() {
		return dao.getFirst();
	}

	@Override
	public Operator getOperatorAvailable() {
		return dao.getOperatorAvailable();
	}

	@Override
	public Supervisor getSupervisorAvailable() {
		return dao.getSupervisorAvailable();
	}

	@Override
	public Manager getManagerAvailable() {
		return dao.getManagerAvailable();
	}

	@Override
	public Operator saveOrUpdateOperator(Operator dato) {
		return dao.saveOrUpdateOperator(dato);
	}

	@Override
	public Supervisor saveOrUpdateSupervisor(Supervisor dato) {
		return dao.saveOrUpdateSupervisor(dato);
	}

	@Override
	public Manager saveOrUpdateManager(Manager dato) {
		return dao.saveOrUpdateManager(dato);
	}

	@Override
	public Client getUser(String name) {
		// TODO Auto-generated method stub
		return dao.getUser(name);
	}
}
