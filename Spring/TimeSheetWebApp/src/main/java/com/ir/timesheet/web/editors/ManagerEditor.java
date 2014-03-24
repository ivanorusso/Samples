package com.ir.timesheet.web.editors;

import java.beans.PropertyEditorSupport;

import com.ir.timesheet.model.Manager;
import com.ir.timesheet.service.dao.ManagerDao;

public class ManagerEditor extends PropertyEditorSupport {
	 
    private ManagerDao managerDao;
 
    public ManagerEditor(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
 
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        long id = Long.parseLong(text);
        Manager manager = managerDao.find(id);
        setValue(manager);
    }
}