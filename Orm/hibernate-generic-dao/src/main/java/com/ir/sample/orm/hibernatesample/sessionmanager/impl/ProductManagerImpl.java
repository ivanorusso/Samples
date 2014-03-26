package com.ir.sample.orm.hibernatesample.sessionmanager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

import com.ir.sample.orm.hibernatesample.dao.ProductDAO;
import com.ir.sample.orm.hibernatesample.dao.impl.ProductDAOImpl;
import com.ir.sample.orm.hibernatesample.model.Product;
import com.ir.sample.orm.hibernatesample.sessionmanager.ProductManager;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class ProductManagerImpl implements ProductManager {

	private ProductDAO ProductDAO = new ProductDAOImpl();

	public Product findByProductName(String name) {
		Product Product = null;
		try {
			HibernateUtil.beginTransaction();
			Product = ProductDAO.findByName(name);
			HibernateUtil.commitTransaction();
		} catch (NonUniqueResultException ex) {
			System.out.println("Query returned more than one results.");
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return Product;
	}

	public List<Product> loadAllProducts() {
		List<Product> allProducts = new ArrayList<Product>();
		try {
			HibernateUtil.beginTransaction();
			allProducts = ProductDAO.findAll(Product.class);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return allProducts;
	}

	public void saveNewProduct(Product Product) {
		try {
			HibernateUtil.beginTransaction();
			ProductDAO.save(Product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	public Product findProductById(BigDecimal id) {
		Product Product = null;
		try {
			HibernateUtil.beginTransaction();
			Product = (Product) ProductDAO.findByID(Product.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return Product;
	}

	public void deleteProduct(Product Product) {
		try {
			HibernateUtil.beginTransaction();
			ProductDAO.delete(Product);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
}
