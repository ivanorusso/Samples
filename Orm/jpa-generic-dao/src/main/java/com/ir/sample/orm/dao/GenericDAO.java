package com.ir.sample.orm.dao;

import java.util.Map;

public interface GenericDAO<T> {
	/**
	 * Method that returns the number of entries from a table that meet some
	 * criteria (where clause params)
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	long countAll(Map params);

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);
}