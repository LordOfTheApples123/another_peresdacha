<%@ page import="com.another_project.entity.Auto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.another_project.entity.Client" %>
<%@ page import="com.another_project.entity.Violation" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 03.03.2023
  Time: 18:14
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
<form action="${pageContext.request.contextPath}/auto" method = "post">
  <input type="hidden" name="action" value="create"/>
  <label for="model">model:</label><br>
  <input type = "text" name = "model" id = "model">
  <label for="number">number:</label><br>
  <input type = "text" name = "number" id = "number">

  <button type="submit">add</button>
</form>


<h1>find by model</h1>

<form action="${pageContext.request.contextPath}/auto" method = "get">
  <input type="hidden" name="action" value="find_model"/>
  <label for="find_model">model:</label><br>
  <input type = "text" name="find_model" id = "find_model">
  <button type="submit">find</button>
</form>

<h1>find by number</h1>

<form action="${pageContext.request.contextPath}/auto" method = "get">
  <input type="hidden" name="action" value="find_number"/>
  <label for="find_number">number:</label><br>
  <input type = "text" name="find_number" id = "find_number">
  <button type="submit">find</button>
</form>


<table>
  <thead>
  <tr>
    <td>id</td>
    <td>model</td>
    <td>number</td>
  </tr>
  </thead>
  <tbody>
  <% List<Auto> autos = (List<Auto>) request.getAttribute("autos");
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    int pageNo = (int) request.getAttribute("page");
    int recordsPerPage = (int) request.getAttribute("recordsPerPage");
    int noOfPages = (int) request.getAttribute("noOfPages");
    int start = (pageNo-1) * recordsPerPage;
    if(start != 0){
      start--;
    }%>
  <% for (int i = start; i < (pageNo * recordsPerPage) - 1 && i < autos.size(); i++ ) {%>
  <% Auto auto = autos.get(i);
  %>
  <tr>
    <td><%= auto.getId() %></td>
    <td><%= auto.getModel() %></td>
    <td><%= auto.getNumber() %></td>
    <td><form action="${pageContext.request.contextPath}/updateauto" method="get">
      <input type = "hidden" name = "update" value = <%=auto.getId()%>>
      <input type = "hidden" name = "class" value="client">
      <input type = "submit" value="update">
    </form></td>
    <td><form action="${pageContext.request.contextPath}/auto" method="post">
      <input type = "hidden" name = "delete" value = <%=auto.getId()%>>
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
    <td><a href="${pageContext.request.contextPath}/auto?page=<%=pageNo - 1%>">Previous</a></td>
    <%}%>
    <% for(int i = 1; i <= noOfPages; i++){
      if(i == pageNo){ %>
    <td><%=i%></td>
    <% }
    else{ %>
    <td><a href="${pageContext.request.contextPath}/auto?page=<%=i%>"><%=i%></a></td>
    <%}
    }%>
    <%--For displaying Next link --%>
    <% if(pageNo!=noOfPages){ %>
    <td><a href="${pageContext.request.contextPath}/auto?page=<%=pageNo + 1%>">Next</a></td>
    <%}%>
  </tr>
</table>
</body>
</html>
