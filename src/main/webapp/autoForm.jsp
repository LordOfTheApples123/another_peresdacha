<%@ page import="com.another_project.entity.Auto" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 03.03.2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update</h1>
<% Auto auto = (Auto) request.getAttribute("auto");
        long id = auto.getId();%>
<%=id%>
<form action="${pageContext.request.contextPath}/updateauto" method = "post">
    <input type="hidden" name="id" value= "<%=id%>"/>
    <label for="model">model:</label><br>
    <input type = "text" name = "model" id = "model" value = "<%=auto.getModel()%>">
    <label for="number">number:</label><br>
    <input type = "text" name = "number" id = "number" value= "<%=auto.getNumber()%>">

    <button type="submit">add</button>
</form>
</body>
</html>
