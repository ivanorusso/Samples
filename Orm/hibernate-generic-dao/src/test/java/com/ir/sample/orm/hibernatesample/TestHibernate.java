package com.ir.sample.orm.hibernatesample;

import org.junit.Assert;
import org.junit.Test;

import com.ir.sample.orm.hibernatesample.model.Product;
import com.ir.sample.orm.hibernatesample.sessionmanager.ProductManager;
import com.ir.sample.orm.hibernatesample.sessionmanager.impl.ProductManagerImpl;

public class TestHibernate{
	
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() 
	{
		ProductManager productManager = new ProductManagerImpl();
		Product chuck = new Product("Keyboard"); 
		productManager.saveNewProduct(chuck);
		
		ProductManager productManager2 = new ProductManagerImpl();
		Product prodFound=productManager2.findByProductName("Keyboard");
		Assert.assertNotNull(prodFound);
		
		productManager.deleteProduct(prodFound);
		prodFound=productManager.findByProductName("Keyboard");
		Assert.assertNull(prodFound);
	}
	

}
