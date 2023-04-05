<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" style="background-color: #c9b307">
<head>
  <meta charset="UTF-8">
  <title>Add new material</title>
</head>
<body>
<div>
  <h1>Create new additional material</h1>
  <form action="${pageContext.request.contextPath}/add-new-material" method="post" style="margin: 0 auto; width: 400px;">
    <div style="margin-top: 1em;">
      <label for="name" style="display: inline-block; width: 100px; text-align: right;">Name: </label>
      <input type="text" name="name" id="name" required>
    </div>
    <div style="margin-top: 1em;">
      <label for="ID" style="display: inline-block; width: 100px; text-align: right;">ID: </label>
      <input type="number" min="1" name="ID" id="ID" required>
    </div>
    <div style="margin-top: 1em;">
      <label for="lectureID" style="display: inline-block; width: 100px; text-align: right;">Lecture ID: </label>
      <input type="number" min="1" name="lectureID" id="lectureID" required>
    </div>
    <div style="margin-top: 1em;">
      <label style="display: inline-block; width: 100px; text-align: right;">Resource type:</label> <br>
      <label for="url" style="display: inline-block; width: 100px; text-align: right;">URL </label>
      <input type="radio" name="resourceType" id="url" value="URL">
      <label for="video" style="display: inline-block; width: 70px; text-align: right;">VIDEO </label>
      <input type="radio" name="resourceType" id="video" value="VIDEO">
      <label for="book" style="display: inline-block; width: 70px; text-align: right;">BOOK </label>
      <input type="radio" name="resourceType" id="book" value="BOOK" checked>
    </div>
    <div style="margin-top: 1em; padding-left: 100px;">
      <input type="submit" value="Insert" style="margin-left: 1em;">
    </div>
  </form>
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/ad-materials">Back to Additional materials table</a></li>
    </ul>
  </nav>
</div>
</body>
</html>