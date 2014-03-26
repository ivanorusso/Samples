package com.ir.sample.orm.hibernatesample.dao;

import com.ir.sample.orm.hibernatesample.model.Product;
import com.ir.sample.orm.hibernatesample.model.User;



public interface ProductDAO extends GenericDAO<Product, Long> 
{
	public Product findByName(String name);
	
}

	  