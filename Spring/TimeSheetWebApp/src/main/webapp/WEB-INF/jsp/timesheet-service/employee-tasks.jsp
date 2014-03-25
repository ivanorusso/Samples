<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 
<%--@elvariable id="employee" type="com.ir.timesheet.model.Employee"--%>
<%--@elvariable id="tasks" type="java.util.List<com.ir.timesheet.model.Task>"--%>
 
<html>
<head>
    <title>Tasks for employee</title>
    <link rel="stylesheet" href="/TimeSheetSample/resources/styles.css" type="text/css">
</head>
<body>
    <h3>
        Current employee: <a href="/TimeSheetSample/employees/${employee.id}">${employee.name}</a>
    </h3>
    <div id="list">
        <c:forEach items="${tasks}" var="task">
            <li>
                <a href="/TimeSheetSample/tasks/${task.id}">${task.description}</a>
            </li>
        </c:forEach>
    </div>
 
    <br /><br />
    <a href="../">Go Back</a>
</body>
</html>