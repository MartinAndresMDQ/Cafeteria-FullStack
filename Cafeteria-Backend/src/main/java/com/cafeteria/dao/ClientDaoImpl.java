package com.cafeteria.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cafeteria.model.Client;
import com.cafeteria.model.Manager;
import com.cafeteria.model.Operator;
import com.cafeteria.model.Supervisor;

@Repository("ClientDao")
public class ClientDaoImpl extends GenericDAOImpl<Client> implements ClientDao {

	@Override
	public Operator getOperatorAvailable() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Operator> cq = cb.createQuery(Operator.class);
		Root<Operator> root = cq.from(Operator.class);
		cq.where(cb.and(cb.equal(root.get("online"), true), cb.equal(root.get("DTYPE"), "Operator")))
				.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<Operator> objects = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		Operator client = null;
		if (objects.size() != 0)
			client = objects.get(0);
		return client;
	}

	@Override
	public Supervisor getSupervisorAvailable() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Supervisor> cq = cb.createQuery(Supervisor.class);
		Root<Supervisor> root = cq.from(Supervisor.class);
		cq.where(cb.and(cb.equal(root.get("online"), true), cb.equal(root.get("DTYPE"), "Supervisor")))
				.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<Supervisor> objects = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		Supervisor client = null;
		if (objects.size() != 0)
			client = objects.get(0);
		return client;
	}

	@Override
	public Manager getManagerAvailable() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Manager> cq = cb.createQuery(Manager.class);
		Root<Manager> root = cq.from(Manager.class);
		cq.where(cb.and(cb.equal(root.get("online"), true), cb.equal(root.get("DTYPE"), "Manager")))
				.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<Manager> objects = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		Manager client = null;
		if (objects.size() != 0)
			client = objects.get(0);
		return client;
	}

	@Override
	public Operator saveOrUpdateOperator(Operator dato) {
		return getsessionFactory().merge(dato);
	}

	@Override
	public Supervisor saveOrUpdateSupervisor(Supervisor dato) {
		return getsessionFactory().merge(dato);
	}

	@Override
	public Manager saveOrUpdateManager(Manager dato) {
		return getsessionFactory().merge(dato);
	}

	@Override
	public Client getUser(String name) {
		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> root = cq.from(Client.class);
		cq.where(cb.equal(root.get("name"), name)).distinct(true);

		List<Client> objects = this.getsessionFactory().createQuery(cq).getResultList();
		Client client = null;
		if (objects.size() != 0)
			client = objects.get(0);
		return client;
	}

}
