package com.ir.sample.orm.hibernatesample.dao.impl;

import org.hibernate.Query;

import com.ir.sample.orm.hibernatesample.dao.UserDAO;
import com.ir.sample.orm.hibernatesample.model.User;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
 
    public User findByName(String name) {
        User user = null;
        String sql = "SELECT p FROM User p WHERE p.name = :name";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name);
        user = findOne(query);
        return user;
    }
}