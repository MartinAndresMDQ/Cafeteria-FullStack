package com.cafeteria.service;

import java.util.List;

import com.cafeteria.model.Client;
import com.cafeteria.model.Manager;
import com.cafeteria.model.Operator;
import com.cafeteria.model.Supervisor;

public interface ClientService extends GenericService<Client> {

	Operator getOperatorAvailable();

	Supervisor getSupervisorAvailable();

	Manager getManagerAvailable();

	Operator saveOrUpdateOperator(Operator dato);

	Supervisor saveOrUpdateSupervisor(Supervisor dato);

	Manager saveOrUpdateManager(Manager dato);

	Client getUser(String name);

	Client getSupport(String name, String password);

	List<Client> getClientsOnline();

	Operator getOperator(int idR);

}
