package com.ir.sample.orm.hibernatesample.sessionmanager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

import com.ir.sample.orm.hibernatesample.dao.UserDAO;
import com.ir.sample.orm.hibernatesample.dao.impl.UserDAOImpl;
import com.ir.sample.orm.hibernatesample.model.User;
import com.ir.sample.orm.hibernatesample.model.User;
import com.ir.sample.orm.hibernatesample.sessionmanager.UserManager;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class UserManagerImpl implements UserManager {

	private UserDAO UserDAO = new UserDAOImpl();

	public User findByUserName(String name, String surname) {
		User User = null;
		try {
			HibernateUtil.beginTransaction();
			User = UserDAO.findByName(name);
			HibernateUtil.commitTransaction();
		} catch (NonUniqueResultException ex) {
			System.out.println("Handle your error here");
			System.out.println("Query returned more than one results.");
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return User;
	}

	public List<User> loadAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		try {
			HibernateUtil.beginTransaction();
			allUsers = UserDAO.findAll(User.class);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return allUsers;
	}

	public void saveNewUser(User User) {
		try {
			HibernateUtil.beginTransaction();
			UserDAO.save(User);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	public User findUserById(BigDecimal id) {
		User User = null;
		try {
			HibernateUtil.beginTransaction();
			User = (User) UserDAO.findByID(User.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
		}
		return User;
	}

	public void deleteUser(User User) {
		try {
			HibernateUtil.beginTransaction();
			UserDAO.delete(User);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
}