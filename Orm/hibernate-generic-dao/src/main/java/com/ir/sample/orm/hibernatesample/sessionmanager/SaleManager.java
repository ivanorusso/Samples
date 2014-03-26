package com.ir.sample.orm.hibernatesample.sessionmanager;

import java.math.BigDecimal;
import java.util.List;

import com.ir.sample.orm.hibernatesample.model.Sale;

public interface SaleManager {
	 
    public List<Sale> loadAllSales();
 
    public void saveNewSale(Sale Sale);
 
    public Sale findSaleById(BigDecimal id);
 
    public void deleteSale(Sale Sale);
}