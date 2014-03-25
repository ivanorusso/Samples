package com.ir.timesheet.web.editors;

import java.beans.PropertyEditorSupport;

import com.ir.timesheet.model.Employee;
import com.ir.timesheet.service.dao.EmployeeDao;
 
/**
 * Will convert ID from combobox to employee's instance.
 */
public class EmployeeEditor extends PropertyEditorSupport {
 
    private EmployeeDao employeeDao;
 
    public EmployeeEditor(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
 
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        long id = Long.parseLong(text);
        Employee employee = employeeDao.find(id);
        setValue(employee);
    }
}