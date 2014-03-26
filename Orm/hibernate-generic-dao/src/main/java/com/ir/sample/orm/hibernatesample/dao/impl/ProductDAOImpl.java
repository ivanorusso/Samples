package com.ir.sample.orm.hibernatesample.dao.impl;

import org.hibernate.Query;

import com.ir.sample.orm.hibernatesample.dao.ProductDAO;
import com.ir.sample.orm.hibernatesample.model.Product;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements ProductDAO {
 
    public Product findByName(String name) {
    	Product product = null;
        String sql = "SELECT p FROM Product p WHERE p.name = :name";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name);
        product = findOne(query);
        return product;
    }
}