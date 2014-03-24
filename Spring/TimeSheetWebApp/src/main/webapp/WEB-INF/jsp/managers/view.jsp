<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Manager page</title>
    <link rel="stylesheet" href="/TimeSheetSample/resources/styles.css" type="text/css">
</head>
<body>
    <h2>Manager info</h2>
    <div id="list">
        <sf:form method="post">
            <ul>
                <li>
                    <label for="name">Name:</label>
                    <input name="name" id="name" value="${manager.name}" disabled="true"/>
                </li>
                <li>
                    <input type="button" value="Unlock" id="unlock" />
                    <input type="submit" value="Save" id="save" class="hidden" />
                </li>
            </ul>
        </sf:form>
    </div>
 
    <br /><br />
    <a href="../managers">Go Back</a>
 
    <script src="/TimeSheetSample/resources/jquery.js"></script>
    <script>
        (function() {
            $("#unlock").on("click", function() {
                $("#unlock").addClass("hidden");
 
                // enable stuff
                $("#name").removeAttr("disabled");
                $("#save").removeClass("hidden");
            });
        })();
    </script>
</body>
</html>