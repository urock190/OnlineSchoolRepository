<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Number of additional materials by resource type</title>
  </head>
  <body>
  <h1>Number of additional materials by resource type</h1>
  <table border="1px" style="font-size: 24px">
    <thead>
    <tr>
      <th> Resource type </th>
      <th> Materials number </th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="map" type="java.util.Map"--%>
    <c:forEach var="entrySet" items="${map}">
      <tr>
        <td>${entrySet.key}</td>
        <td>${entrySet.value}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/ad-materials">Back to Additional materials table</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
