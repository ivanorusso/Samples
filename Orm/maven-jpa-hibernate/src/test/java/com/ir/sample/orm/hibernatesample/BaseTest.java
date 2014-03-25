package com.ir.sample.orm.hibernatesample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ir.sample.orm.hibernatesample.util.HibernateUtil;

public abstract class BaseTest {

	SessionFactory sessionFactory = HibernateUtil.configureSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public void flushConnection() {
		if (session.isOpen()) {
			session.close();
		}

		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
	}

	public void execSqlScript(String aSQLScriptFilePath) {

		this.flushConnection();

		try {
			// Create MySql Connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hibernatesample", "root",
					"ererer");

			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);

			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(
					aSQLScriptFilePath));

			// Exctute script
			sr.runScript(reader);

		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath
					+ " The error is " + e.getMessage());
		}
	}

}
