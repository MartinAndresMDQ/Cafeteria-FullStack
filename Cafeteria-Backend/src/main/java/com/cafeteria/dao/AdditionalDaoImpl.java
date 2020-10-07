package com.cafeteria.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cafeteria.model.Additional;

@Repository("AdditionalDao")
public class AdditionalDaoImpl extends GenericDAOImpl<Additional> implements AdditionalDao {

	@Override
	public List<Additional> getAlls() {

		CriteriaBuilder cb = getsessionFactory().getCriteriaBuilder();
		CriteriaQuery<Additional> cq = cb.createQuery(getEntityClass());
		Root<Additional> root = cq.from(getEntityClass());
		cq.where(cb.and(cb.equal(root.get("hidden"), false), cb.equal(root.get("DTYPE"), "Additional")))
				.orderBy(cb.asc(root.get("id"))).distinct(true);

		List<Additional> object = this.getsessionFactory().createQuery(cq)
				// .setFirstResult(desde) // offset
				// .setMaxResults(hasta) // limit
				.getResultList();
		return object;
	}
}
