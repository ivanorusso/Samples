package com.ir.sample.orm.hibernatesample.sessionmanager;

import java.math.BigDecimal;
import java.util.List;

import com.ir.sample.orm.hibernatesample.model.User;

public interface UserManager {
	 
    public User findByUserName(String name, String surname);
 
    public List<User> loadAllUsers();
 
    public void saveNewUser(User User);
 
    public User findUserById(BigDecimal id);
 
    public void deleteUser(User User);
}