<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Students By Courses Number And Sorted By Last Name</title>
  </head>
  <body>
  <h1>Students grouped by courses number and sorted by last name</h1>
  <table border="1px" style="font-size: 24px">
    <thead>
    <tr>
      <th> Last name </th>
      <th> Name </th>
      <th> Courses number </th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="map" type="java.util.Map"--%>
    <c:forEach var="entrySet" items="${map}">
      <tr>
        <td>${entrySet.key.lastName}</td>
        <td>${entrySet.key.name}</td>
        <td>${entrySet.value}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/students">Back to Students table</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
