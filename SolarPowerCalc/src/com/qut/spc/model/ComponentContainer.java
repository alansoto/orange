package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.qut.spc.EMF;
import com.qut.spc.api.ComponentFilterAPI;
import com.qut.spc.db.QueryBuilder;

/**
 * Common container for all Panel/Battery/Inverter
 * @author QuocViet
 */
public abstract class ComponentContainer implements ComponentFilterAPI {
	private double minPrice = 0.0;
	
	private double maxPrice = 0.0;
	
	private double minCapacity = 0.0;
	
	private double maxCapacity = 0.0;
	
	private String postcode = "";
	
	@Override
	public void setMinPrice(double minPrice) throws IllegalArgumentException {
		if (minPrice < 0.0) {
			throw new IllegalArgumentException(
					"The minimum price must not be negative");
		}
		this.minPrice = minPrice;
	}

	@Override
	public void setMaxPrice(double maxPrice) throws IllegalArgumentException {
		if (maxPrice < 0.0) {
			throw new IllegalArgumentException(
					"The maximum price must not be negative");
		}
		this.maxPrice = maxPrice;		
	}

	@Override
	public void setMinCapacity(double minCapacity)
			throws IllegalArgumentException {
		if (minCapacity < 0.0) {
			throw new IllegalArgumentException(
					"The minimum capacity must not be negative");
		}
		this.minCapacity = minCapacity;
	}

	@Override
	public void setMaxCapacity(double maxCapacity)
			throws IllegalArgumentException {
		if (maxCapacity < 0.0) {
			throw new IllegalArgumentException(
					"The maximum capacity must not be negative");
		}
		this.maxCapacity = maxCapacity;
	}

	@Override
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> fetchComponents(String table)
			throws IllegalArgumentException {
		if (maxPrice != 0.0 && maxPrice < minPrice) {
			throw new IllegalArgumentException(
					"The minimum price must be greater than or equal to the maximum price");
		}
		if (maxCapacity != 0.0 && maxCapacity < minCapacity) {
			throw new IllegalArgumentException(
					"The minimum capacity must be greater than or equal to the maximum capacity");
		}
		
		QueryBuilder qb = new QueryBuilder(table);
		
		qb.addRange("price", minPrice, maxPrice);
		qb.addRange("capacity", minCapacity, maxCapacity);
		// TODO: search by postcode
		
		EntityManager em = EMF.get().createEntityManager();
		Query query = qb.getQuery(em);
		
		List<T> result;
		try {
			result = new ArrayList<T>(query.getResultList());
		} finally {
			em.close();
		}
		return result;
	}
}