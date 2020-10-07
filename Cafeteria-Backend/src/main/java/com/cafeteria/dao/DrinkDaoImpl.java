package com.cafeteria.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cafeteria.model.Drink;

@Repository("DrinkDao")
public class DrinkDaoImpl extends GenericDAOImpl<Drink> implements DrinkDao {

	@Override
	public List<Drink> getAlls() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Drink> cq = cb.createQuery(getEntityClass());
		Root<Drink> root = cq.from(getEntityClass());
		cq.where(cb.and(cb.equal(root.get("hidden"), false), cb.equal(root.get("DTYPE"), "Drink")))
				.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<Drink> object = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		return object;
	}

}
