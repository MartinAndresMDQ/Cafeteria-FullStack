package com.cafeteria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cafeteria.config.Constants;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	@PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
	private EntityManager EntityManagerFactory;

	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Class<T> getEntityClass() {
		return clazz;
	}

	protected EntityManager getsessionFactory() {
		return EntityManagerFactory;
	}

	@Override
	public T saveOrUpdate(T dato) {
		return getsessionFactory().merge(dato);
	}

	@Override
	public void delete(T dato) {

		getsessionFactory().remove(dato);
	}

	@Override
	public T get(int id) {
		return (T) getsessionFactory().find(getEntityClass(), id);
	}

	@Override
	public List<T> getAlls() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<T> object = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		return object;
	}

	@Override
	public int cant() {
		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<T> object = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		return object.size();
	}

	@Override
	public T getLast() {
		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.orderBy(cb.desc(root.get("id"))).distinct(true);
		List<T> tipo = this.getsessionFactory().createQuery(cq).setMaxResults(1).getResultList();

		T newCall = null;
		if (!tipo.isEmpty())
			newCall = tipo.get(0);
		return newCall;
	}

	@Override
	public T getFirst() {
		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> root = cq.from(getEntityClass());
		cq.orderBy(cb.asc(root.get("id"))).distinct(true);
		List<T> tipo = this.getsessionFactory().createQuery(cq).setMaxResults(1).getResultList();

		T newCall = null;
		if (!tipo.isEmpty())
			newCall = tipo.get(0);
		return newCall;
	}

}
