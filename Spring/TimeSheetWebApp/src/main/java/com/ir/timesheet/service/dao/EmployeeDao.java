package com.ir.timesheet.service.dao;

import com.ir.timesheet.model.Employee;
import com.ir.timesheet.service.GenericDao;


 
/**
 * DAO of employee.
 */
public interface EmployeeDao extends GenericDao<Employee, Long> {
 
    /**
     * Tries to remove employee from the system.
     * @param employee Employee to remove
     * @return {@code true} if employee is not assigned to any task
     * or timesheet. Else {@code false}.
     */
    boolean removeEmployee(Employee employee);
 
}