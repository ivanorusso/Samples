package com.ir.sample.orm.hibernatesample.sessionmanager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

import com.ir.sample.orm.hibernatesample.dao.SaleDAO;
import com.ir.sample.orm.hibernatesample.dao.impl.SaleDAOImpl;
import com.ir.sample.orm.hibernatesample.model.Sale;
import com.ir.sample.orm.hibernatesample.model.Sale;
import com.ir.sample.orm.hibernatesample.sessionmanager.SaleManager;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class SaleManagerImpl implements SaleManager {

	private SaleDAO SaleDAO = new SaleDAOImpl();


	public List<Sale> loadAllSales() {
		List<Sale> allSales = new ArrayList<Sale>();
		try {
			HibernateUtil.beginTransaction();
			allSales = SaleDAO.findAll(Sale.class);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return allSales;
	}

	public void saveNewSale(Sale Sale) {
		try {
			HibernateUtil.beginTransaction();
			SaleDAO.save(Sale);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	public Sale findSaleById(BigDecimal id) {
		Sale Sale = null;
		try {
			HibernateUtil.beginTransaction();
			Sale = (Sale) SaleDAO.findByID(Sale.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return Sale;
	}

	public void deleteSale(Sale Sale) {
		try {
			HibernateUtil.beginTransaction();
			SaleDAO.delete(Sale);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
}