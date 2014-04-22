package com.ir.sample.orm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.ir.sample.orm.model.Todo;

public class JpaTest 
{
	private static final String PERSISTENCE_UNIT_NAME = "todos";
	  private static EntityManagerFactory factory;
	
	@Test
	public void testInsert()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    // read the existing entries and write to console
	    Query q = em.createQuery("select t from Todo t");
	    List<Todo> todoList = q.getResultList();
	    for (Todo todo : todoList) {
	      System.out.println(todo);
	    }
	    System.out.println("Size: " + todoList.size());

	    // create new todo
	    em.getTransaction().begin();
	    Todo todo = new Todo();
	    todo.setSummary("This is a test");
	    todo.setDescription("This is a test");
	    em.persist(todo);
	    em.getTransaction().commit();

	    todoList = q.getResultList();
	    for (Todo todo2 : todoList) {
	      System.out.println(todo2);
	    }
	    System.out.println("Size: " + todoList.size());
	    
	    
	    em.close();
	}

}
