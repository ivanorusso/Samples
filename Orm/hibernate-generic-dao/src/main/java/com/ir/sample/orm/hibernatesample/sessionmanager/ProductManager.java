package com.ir.sample.orm.hibernatesample.sessionmanager;

import java.math.BigDecimal;
import java.util.List;

import com.ir.sample.orm.hibernatesample.model.Product;

public interface ProductManager {
 
    public Product findByProductName(String name);
 
    public List<Product> loadAllProducts();
 
    public void saveNewProduct(Product Product);
 
    public Product findProductById(BigDecimal id);
 
    public void deleteProduct(Product Product);
}