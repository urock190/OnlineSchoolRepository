<%@ page contentType="text/html;charset=UTF-8" %>

<html class="javascript">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body style="width: auto;">
<h1 style="text-align: center; margin-bottom: 40px; margin-top: 40px">Access Denied</h1>
<div style="text-align: center">
    <h2>You don't have permission to view this page.</h2>
    <br>
    <h3><a href="${pageContext.request.contextPath}/login">Click here to Login as Admin</a></h3>
    <h3><a href="${pageContext.request.contextPath}/">Back to Homepage</a></h3>
</div>
</body>
</html>