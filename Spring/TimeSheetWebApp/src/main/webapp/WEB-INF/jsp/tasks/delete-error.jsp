<%--@elvariable id="task" type="com.ir.timesheet.model.Task"--%>
 
<html>
<head>
    <title>Cannot delete task</title>
</head>
<body>
    Oops! Resource <a href="${task.id}">${task.description}</a> can not be deleted.
 
    <p>
        Make sure there are no timesheets assigned on task.
    </p>
 
    <br /><br /><br />
    <a href="../welcome">Back to main page.</a>
</body>
</html>