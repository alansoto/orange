package com.qut.spc.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.qut.spc.EMF;
import com.qut.spc.model.SolarComponent;

public class Database {
	/**
	 * Get list of components from database which its property is in price
	 * range
	 *
	 * @param type Object type. E.g. Panel.class
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return List of panels
	 */
	public static <T> List<T> getComponentsInPrice(Class<T> type, 
			double min, double max) throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum price must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum price must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum price must be greater than or equal to the maximum price");
		}
		return fetchComponentsByRange(type.getName(),
				"price", min, max);
	}
	
	public static <T> List<T> getComponentsInCapacity(Class<T> type,
			double min, double max) throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum capacity must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum capacity must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum capacity must be greater than or equal to the maximum capacity");
		}
		return fetchComponentsByRange(type.getName(),
				"capacity", min, max);
	}
	
	/**
	 * Get panels from database which its property is in provided range
	 *
	 * @param table Table name. e.g. Panel.class.getName()
	 * @param field Property name. E.g. price
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return List of panels
	 */
	@SuppressWarnings("unchecked")
	protected static <T> List<T> fetchComponentsByRange(String table, String field,
			double min, double max) {
		String queryStr = buildQueryStringFromRange(table, field, min, max);
		
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = em.createQuery(queryStr);
		if (min > 0.0) {
			query.setParameter("min", min);
		}
		if (max > 0.0) {
			query.setParameter("max", max);
		}
		
		List<T> resultList;
		try {
			resultList = new ArrayList<T>(query.getResultList());
		} finally {
			em.close();
		}
		return resultList;
	}
	
	
	/**
	 * Build string to query min/max value
	 * 
	 * @param table Table name. e.g. Panel.class.getName()
	 * @param field Database field name
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Query string
	 */
	protected static String buildQueryStringFromRange(String table, String field,
			double min, double max) {
		String str = "SELECT FROM " + table;
		
		// Min/max is optional
		if (min > 0.0 || max > 0.0) {
			str += " WHERE";
			if (min > 0.0) {
				str += " " + field + " >= :min";
			}
			if (max > 0.0) {
				if (min > 0.0) {
					str += " AND";
				}
				str += " " + field + " <= :max";
			}
		}
		return str;
	}
	
	/**
	 * Store object to database
	 * 
	 * @param self Object to store
	 * @return Provided object
	 */
	public static <T> T saveComponent(T self) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			em.persist(self);
		} finally {
			em.close();
		}
		return self;
	}
	
	
	
	
	/**
	 * Get the object from database using its ID
	 * 
	 * @param id ID of the object, its type can be "long" or "Key"
	 * @param cls Class name of the object, e.g. "Panel.class"
	 * @return Object which full properties are retrieved from database 
	 */
	public static <T> T loadComponent(Object id, Class<T> cls) {
		EntityManager em = EMF.get().createEntityManager();
		T self;
		try {
			self = em.find(cls, id);
		} finally {
			em.close();
		}
		return self;
	}
}
