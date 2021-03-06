package com.ir.sample.orm.hibernatesample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ir.sample.orm.hibernatesample.model.Product;
import com.ir.sample.orm.hibernatesample.model.Sale;
import com.ir.sample.orm.hibernatesample.model.User;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public class TestHibernate extends BaseTest{
	
	
	@After
	public void rollback()
	{
		execSqlScript("src/test/resources/sql/deleteAll.sql");	
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testInsert() {
		// We must open a new transaction before doing anything with the DB
		Transaction tx = session.beginTransaction();

		User john = new User("john");
		john.setAge(25);
		User mike = new User("Mike");

		session.persist(john);
		session.persist(mike);

		Product screen = new Product("Screen");
		screen.setDescription("1080p HD Screen");
		Product keyboard = new Product("Keyboard");

		session.persist(screen);
		session.persist(keyboard);

		Sale johnBoughtScreen = new Sale(john, screen, new Date());
		Sale johnBoughtKeyboard = new Sale(john, keyboard, new Date());
		Sale mikeBoughtKeyboard = new Sale(mike, keyboard, new Date());

		List<Sale> salesJohnList = new ArrayList<Sale>();
		salesJohnList.add(johnBoughtKeyboard);
		salesJohnList.add(johnBoughtScreen);

		session.persist(johnBoughtScreen);
		session.persist(johnBoughtKeyboard);
		session.persist(mikeBoughtKeyboard);

		List<User> users = session.createCriteria(User.class).list();
		for (User user : users) {
			System.out.println("User with id " + user.getId() + " and name "
					+ user.getName());
		}

		List<Product> products = session.createCriteria(Product.class).list();
		for (Product product : products) {
			System.out.println("Product with id " + product.getId()
					+ " and name " + product.getName());
		}

		List<Sale> sales = session.createCriteria(Sale.class).list();
		for (Sale sale : sales) {
			System.out.println("Sale with id " + sale.getId() + " user "
					+ sale.getUser().getName() + "(" + sale.getUser().getId()
					+ ")" + " product " + sale.getProduct().getName() + "("
					+ sale.getProduct().getId() + ")");
		}

		// We can now close the transaction and persist the changes
		tx.commit();
	}

	@Test
	public void testUnique() {
		
		//firstTransaction
		Transaction tx = session.beginTransaction();
		
		User john = new User("john");
		john.setAge(25);
		session.persist(john);
		tx.commit();

		session = sessionFactory.getCurrentSession();
		//second transaction, test the unique key
		tx = session.beginTransaction();	
		User john2 = new User("john");
		john2.setAge(28);
		
		thrown.expect(org.hibernate.exception.ConstraintViolationException.class);
		thrown.expectMessage("could not execute statement");

		session.persist(john2);
		
		tx.commit();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUpdate() {
		SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		// We must open a new transaction before doing anything with the DB
		Transaction tx = session.beginTransaction();

		
		User john = new User("john");
		john.setAge(25);
		User mike = new User("Mike");

		session.persist(john);
		session.persist(mike);

		Product screen = new Product("Screen");
		screen.setDescription("1080p HD Screen");
		Product keyboard = new Product("Keyboard");

		session.persist(screen);
		session.persist(keyboard);

		Sale johnBoughtScreen = new Sale(john, screen, new Date());
		Sale johnBoughtKeyboard = new Sale(john, keyboard, new Date());
		Sale mikeBoughtKeyboard = new Sale(mike, keyboard, new Date());

		List<Sale> salesJohnList = new ArrayList<Sale>();
		salesJohnList.add(johnBoughtKeyboard);
		salesJohnList.add(johnBoughtScreen);

		session.persist(johnBoughtScreen);
		session.persist(johnBoughtKeyboard);
		session.persist(mikeBoughtKeyboard);
		
		
		
		List<Sale> salesList = session.createCriteria(Sale.class).list();
		john = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("name", "john")).uniqueResult();

		john.setSales(salesList);

		session.update(john);

		List<User> users = session.createCriteria(User.class).list();
		for (User user : users) {
			System.out.println("User with id " + user.getId() + " and name "
					+ user.getName());
			if (user.getSales() != null) {
				System.out.println("Sales: " + user.getSales().toString());
			}
		}

		tx.commit();

	}
	
}
