package com.ir.timesheet.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.ir.timesheet.DomainAwareBase;
import com.ir.timesheet.model.Employee;
import com.ir.timesheet.model.Manager;
import com.ir.timesheet.model.Task;
import com.ir.timesheet.model.Timesheet;
import com.ir.timesheet.service.dao.EmployeeDao;
import com.ir.timesheet.service.dao.ManagerDao;
import com.ir.timesheet.service.dao.TaskDao;
import com.ir.timesheet.service.dao.TimesheetDao;
import com.ir.timesheet.web.TimesheetController;
import com.ir.timesheet.web.commands.TimesheetCommand;
 
@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class TimesheetControllerTest extends DomainAwareBase {
     
    @Autowired
    private TimesheetDao timesheetDao;
 
    @Autowired
    private EmployeeDao employeeDao;
 
    @Autowired
    private ManagerDao managerDao;
 
    @Autowired
    private TaskDao taskDao;
 
    @Autowired
    private TimesheetController controller;
     
    private Model model; // used for controller
 
    @Before
    public void setUp() {
        model = new ExtendedModelMap();
    }
 
    @Test
    public void testShowTimesheets() {
        // prepare some data
        Timesheet timesheet = sampleTimesheet();
 
        // use controller
        String view = controller.showTimesheets(model);
        assertEquals("timesheets/list", view);
 
        List<Timesheet> listFromDao = timesheetDao.list();
        Collection<?> listFromModel = (Collection<?>) model.asMap().get("timesheets");
 
        assertTrue(listFromModel.contains(timesheet));
        assertTrue(listFromDao.containsAll(listFromModel));
    }
     
    @Test
    public void testDeleteTimesheet() {
        // prepare ID to delete
        Timesheet timesheet = sampleTimesheet();
        timesheetDao.add(timesheet);
        long id = timesheet.getId();
 
        // delete & assert
        String view = controller.deleteTimesheet(id);
        assertEquals("redirect:/timesheets", view);
        assertNull(timesheetDao.find(id));
    }
 
    @Test
    public void testGetTimesheet() {
        // prepare timesheet
        Timesheet timesheet = sampleTimesheet();
        timesheetDao.add(timesheet);
        long id = timesheet.getId();
        TimesheetCommand tsCommand = new TimesheetCommand(timesheet);
 
        // get & assert
        String view = controller.getTimesheet(id, model);
        assertEquals("timesheets/view", view);
        assertEquals(tsCommand, model.asMap().get("tsCommand"));
    }
 
    @Test
    public void testUpdateTimesheetValid() {
        // prepare ID to delete
        Timesheet timesheet = sampleTimesheet();
        timesheetDao.add(timesheet);
        long id = timesheet.getId();
        TimesheetCommand tsCommand = new TimesheetCommand(timesheet);
 
        // user alters Timesheet hours in HTML form with valid value
        tsCommand.setHours(1337);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
 
        // update & assert
        String view = controller.updateTimesheet(id, tsCommand, result);
        assertEquals("redirect:/timesheets", view);
        assertTrue(1337 == timesheetDao.find(id).getHours());
    }
 
    @Test
    public void testUpdateTimesheetInValid() {
        // prepare ID to delete
        Timesheet timesheet = sampleTimesheet();
        timesheetDao.add(timesheet);
        long id = timesheet.getId();
 
        TimesheetCommand tsCommand = new TimesheetCommand(timesheet);
        Integer originalHours = tsCommand.getHours();
 
        // user alters Timesheet hours in HTML form with valid value
        tsCommand.setHours(-1);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
 
        // update & assert
        String view = controller.updateTimesheet(id, tsCommand, result);
        assertEquals("timesheets/view", view);
        assertEquals(originalHours, timesheetDao.find(id).getHours());
    }
 
    @Test
    public void testAddTimesheet() {
        // prepare timesheet
        Timesheet timesheet = sampleTimesheet();
 
        // save but via controller
        String view = controller.addTimesheet(timesheet);
        assertEquals("redirect:/timesheets", view);
 
        // timesheet is stored in DB
        assertEquals(timesheet, timesheetDao.find(timesheet.getId()));
    }
 
    private Timesheet sampleTimesheet() {
        Employee marty = new Employee("Martin Brodeur", "NHL");
        employeeDao.add(marty);
 
        Manager jeremy = new Manager("Jeremy");
        managerDao.add(jeremy);
 
        Task winStanleyCup = new Task("NHL finals", jeremy, marty);
        taskDao.add(winStanleyCup);
 
        Timesheet stanelyCupSheet = new Timesheet(marty, winStanleyCup, 100);
        timesheetDao.add(stanelyCupSheet);
 
        return stanelyCupSheet;
    }
}