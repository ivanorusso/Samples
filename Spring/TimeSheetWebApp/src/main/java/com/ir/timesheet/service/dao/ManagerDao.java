package com.ir.timesheet.service.dao;

import com.ir.timesheet.model.Manager;
import com.ir.timesheet.service.GenericDao;


 
/**
 * DAO of Manager.
 */
public interface ManagerDao extends GenericDao<Manager, Long> {
    /**
     * Tries to remove manager from the system.
     * @param manager Manager to remove
     * @return {@code true} if manager is not assigned to any task.
     * Else {@code false}.
     */
    boolean removeManager(Manager manager);
}