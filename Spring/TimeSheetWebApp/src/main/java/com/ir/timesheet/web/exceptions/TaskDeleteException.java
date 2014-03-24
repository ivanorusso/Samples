package com.ir.timesheet.web.exceptions;

import com.ir.timesheet.model.Task;

/**
 * When task cannot be deleted.
 */
public class TaskDeleteException extends Exception {
 
    private Task task;
 
    public TaskDeleteException(Task task) {
        this.task = task;
    }
 
    public Task getTask() {
        return task;
    }
}