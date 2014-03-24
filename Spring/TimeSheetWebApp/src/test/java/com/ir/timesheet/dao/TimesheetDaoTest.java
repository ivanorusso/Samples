package com.ir.timesheet.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.ir.timesheet.DomainAwareBase;
import com.ir.timesheet.model.Employee;
import com.ir.timesheet.model.Manager;
import com.ir.timesheet.model.Task;
import com.ir.timesheet.model.Timesheet;
import com.ir.timesheet.service.dao.EmployeeDao;
import com.ir.timesheet.service.dao.ManagerDao;
import com.ir.timesheet.service.dao.TaskDao;
import com.ir.timesheet.service.dao.TimesheetDao;
 
@ContextConfiguration(locations = "/persistence-beans.xml")
public class TimesheetDaoTest extends DomainAwareBase {
 
    @Autowired
    private TimesheetDao timesheetDao;
 
    // daos needed for integration test of timesheetDao
    @Autowired
    private TaskDao taskDao;
 
    @Autowired
    private EmployeeDao employeeDao;
 
    @Autowired
    private ManagerDao managerDao;
 
    // common fields for timesheet creation
    private Task task;
    private Employee employee;
 
    @Override
    public void deleteAllDomainEntities() {
        super.deleteAllDomainEntities();
        setUp();
    }
 
    public void setUp() {
        employee = new Employee("Steve", "Engineering");
        employeeDao.add(employee);
 
        Manager manager = new Manager("Bob");
        managerDao.add(manager);
 
        task = new Task("Learn Spring", manager, employee);
        taskDao.add(task);
    }
 
    @Test
    public void testAdd() {
        int size = timesheetDao.list().size();
 
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);
 
        assertTrue (size < timesheetDao.list().size());
    }
 
    @Test
    public void testUpdate() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);
 
        // update timesheet
        timesheet.setHours(6);
        taskDao.update(timesheet.getTask());
        timesheetDao.update(timesheet);
 
        Timesheet found = timesheetDao.find(timesheet.getId());
        assertTrue(6 == found.getHours());
    }
 
    @Test
    public void testFind() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);
 
        assertEquals(timesheet, timesheetDao.find(timesheet.getId()));
    }
 
    @Test
    public void testList() {
        assertEquals(0, timesheetDao.list().size());
        Timesheet templateTimesheet = newTimesheet();
         
        List<Timesheet> timesheets = Arrays.asList(
                newTimesheetFromTemplate(templateTimesheet, 4),
                newTimesheetFromTemplate(templateTimesheet, 7),
                newTimesheetFromTemplate(templateTimesheet, 10)
        );
        for (Timesheet timesheet : timesheets) {
            timesheetDao.add(timesheet);
        }
 
        List<Timesheet> found = timesheetDao.list();
        assertEquals(3, found.size());
        for (Timesheet timesheet : found) {
            assertTrue (timesheets.contains(timesheet));
        }
    }
 
    @Test
    public void testRemove() {
        Timesheet timesheet = newTimesheet();
        timesheetDao.add(timesheet);
         
        // successfully added
        assertEquals(timesheet, timesheetDao.find(timesheet.getId()));
         
        // try to remoce
        timesheetDao.remove(timesheet);
        assertNull (timesheetDao.find(timesheet.getId()));
    }
 
    /**
     * @return  Dummy timesheet for testing
     */
    private Timesheet newTimesheet() {
        return new Timesheet(employee, task, 5);
    }
 
    private Timesheet newTimesheetFromTemplate(Timesheet template,
            Integer hours) {
        return new Timesheet(
                template.getWho(),
                template.getTask(),
                hours
        );
    }
}