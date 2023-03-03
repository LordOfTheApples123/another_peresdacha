<%@ page import="com.another_project.entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.another_project.entity.Rent" %>
<%@ page import="com.another_project.entity.Violation" %>
<%@ page import="com.another_project.entity.Auto" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 03.03.2023
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<h1>Create</h1>
<form action="${pageContext.request.contextPath}/client" method = "post">
    <input type="hidden" name="action" value="create"/>
    <label for="name">name:</label><br>
    <input type = "text" name="name" id = "name">

    <button type="submit">add</button>
</form>


<h1>find by id</h1>

<form action="${pageContext.request.contextPath}/client" method = "get">
    <input type="hidden" name="action" value="find"/>
    <label for="find_id">id:</label><br>
    <input type = "number" name="find_id" id = "find_id">
    <button type="submit">find</button>
</form>

<h1>find by name</h1>

<form action="${pageContext.request.contextPath}/client" method = "get">
    <input type="hidden" name="action" value="find_name"/>
    <label for="find_name">name:</label><br>
    <input type = "text" name="find_name" id = "find_name">
    <button type="submit">find</button>
</form>


<table>
    <thead>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>rent history</td>
        <td>violations</td>
    </tr>
    </thead>
    <tbody>
    <% List<Client> clients = (List<Client>) request.getAttribute("clients");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int pageNo = (int) request.getAttribute("page");
        int recordsPerPage = (int) request.getAttribute("recordsPerPage");
        int noOfPages = (int) request.getAttribute("noOfPages");
        int start = (pageNo-1) * recordsPerPage;
        if(start != 0){
            start--;
        }%>
    <% for (int i = start; i < (pageNo * recordsPerPage) - 1 && i < clients.size(); i++ ) {%>
    <% Client client = clients.get(i);
        List<Auto> rentedAutos = client.getRentedAutos();
        List<Violation> violations = client.getViolations();
    %>
    <tr>
        <td><%= client.getId() %></td>
        <td><%= client.getName() %></td>
        <td><% for(Auto auto: rentedAutos){ %>
            <div><%="model : " + auto.getModel() + ", number: " + auto.getNumber() + ";\n" %></div>
        <% } %></td>
        <td><% for(Violation violation: violations){ %>
            <%=violation.getDescr() + ";\n" %>
            <% } %></td>

        <td><form action="${pageContext.request.contextPath}/clientprofile" method="get">
            <input type = "hidden" name = "update" value = <%=client.getId()%>>
            <input type = "hidden" name = "class" value="client">
            <input type = "submit" value="update">
        </form></td>
        <td><form action="${pageContext.request.contextPath}/client" method="post">
            <input type = "hidden" name = "delete" value = <%=client.getId()%>>
            <input type = "hidden" name = "action" value="delete">
            <input type = "submit" value="delete">
        </form></td>
    </tr>
    <% } %>
    </tbody>
</table>

<table>
    <tr>

        <% if(pageNo!=1){ %>
        <td><a href="${pageContext.request.contextPath}/client?page=<%=pageNo - 1%>">Previous</a></td>
        <%}%>
        <% for(int i = 1; i <= noOfPages; i++){
            if(i == pageNo){ %>
        <td><%=i%></td>
        <% }
        else{ %>
        <td><a href="${pageContext.request.contextPath}/client?page=<%=i%>"><%=i%></a></td>
        <%}
        }%>
        <%--For displaying Next link --%>
        <% if(pageNo!=noOfPages){ %>
        <td><a href="${pageContext.request.contextPath}/client?page=<%=pageNo + 1%>">Next</a></td>
        <%}%>
    </tr>
</table>

</body>
</html>
