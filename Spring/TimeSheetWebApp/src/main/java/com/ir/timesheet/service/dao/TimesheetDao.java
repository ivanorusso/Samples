package com.ir.timesheet.service.dao;

import com.ir.timesheet.model.Timesheet;
import com.ir.timesheet.service.GenericDao;


 
/**
 * DAO of Timesheet.
 */
public interface TimesheetDao extends GenericDao<Timesheet, Long> {
    // no additional business operations atm
}