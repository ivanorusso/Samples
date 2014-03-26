package com.ir.sample.orm.hibernatesample.dao;

import com.ir.sample.orm.hibernatesample.model.User;

public interface UserDAO extends GenericDAO<User, Long> 
{
	public User findByName(String name);
}
