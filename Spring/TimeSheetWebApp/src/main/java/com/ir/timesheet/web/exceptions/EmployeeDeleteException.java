package com.ir.timesheet.web.exceptions;

import com.ir.timesheet.model.Employee;

/**
 * When employee cannot be deleted.
 */
public class EmployeeDeleteException extends Exception {
 
    private Employee employee;
 
    public EmployeeDeleteException(Employee employee) {
        this.employee = employee;
    }
 
    public Employee getEmployee() {
        return employee;
    }
}