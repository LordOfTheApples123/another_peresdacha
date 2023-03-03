<%@ page import="com.another_project.entity.Client" %>
<%@ page import="com.another_project.entity.Violation" %>
<%@ page import="com.another_project.entity.Auto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 03.03.2023
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Client client = (Client) request.getAttribute("client");
    List<Auto> rentedAutos = client.getRentedAutos();
    List<Violation> violations = client.getViolations();
%>

<form action="${pageContext.request.contextPath}/clientprofile" method="post">

    <div class="form-example">
        <%=client.getId()%>
        <label for="name">name: </label>
        <input type="hidden" name="id" id="id" required value = "<%=client.getId()%>">
        <input type = "hidden" name = "action" value="update">

    </div>
    <div class="form-example">
        <label for="name">name: </label>
        <input type="text" name="name" id="name" required value = "<%=client.getName()%>">
    </div>
    <div class="form-example">
        <input type="submit" value="Submit!">
    </div>
</form>
<h1>add violation</h1>
<form action="${pageContext.request.contextPath}/clientprofile" method="post">
    <input type="hidden" name="violation_client_id" id="violation_client_id" value = "<%=client.getId()%>">
    <label for="descr">name: </label>
    <input type="text" name = "descr" id="descr" required>
    <input type = "hidden" name = "action" value="add_violation">
    <input type = "submit" value="add">
</form>

<h1>add rent history</h1>
<form action="${pageContext.request.contextPath}/clientprofile" method="post">
<input type="hidden" name="rent_client_id" id="rent_client_id" value = "<%=client.getId()%>">
<label for="auto_id">id of auto: </label>
<input type="number" name = "auto_id" id="auto_id" required>
<input type = "hidden" name = "action" value="add_rent">
<input type = "submit" value = "add">
</form>
<h1>rent history</h1>
<table>
    <tr>violation</tr>
    <tr></tr>
    <% if(violations != null){for(Violation violation: violations){ %>
        <td><%=violation.getDescr()%></td>

    <td><form action="${pageContext.request.contextPath}/clientprofile" method="post">
        <input type = "hidden" name = "id" value = "<%=violation.getId()%>">
        <input type = "hidden" name = "action" value="delete_violation">
        <input type = "submit" value="delete">

    </form></td>
    <% }
    }%>
</table>
<h1>rent history</h1>
<table>
    <tr>rent history</tr>
    <tr></tr>
    <tr></tr>
    <% if(rentedAutos !=null){
        for(Auto rentedAuto: rentedAutos){ %>
    <td><%=rentedAuto.getModel()%></td>
    <td><%=rentedAuto.getNumber()%></td>

    <td><form action="${pageContext.request.contextPath}/clientprofile" method="post">
        <input type = "hidden" name = "auto_id" value = "<%=rentedAuto.getId()%>">
        <input type = "hidden" name = "client_id" value = "<%=client.getId()%>">
        <input type = "hidden" name = "action" value="delete_rent">
        <input type = "submit" value="delete">
    </form></td>
    <% }
    }
    %>

</table>
</body>
</html>
