<%@ page contentType="text/html;charset=UTF-8" %>

<html class="javascript">
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <script src="${pageContext.request.contextPath}/static/css/loginScript.js" defer></script>
</head>
<body style="width: auto;">
<h1 style="text-align: center; margin-bottom: 40px;">Login form</h1>
<form style="margin: 0 auto; width: 400px;" action="${pageContext.request.contextPath}/login" method="post">
    <div style="margin-top: 1em;">
        <label for="username" class="login-label">Username: </label>
        <br>
        <input type="text" name="username" id="username" required class="login-input">
        <span id="nameError" style="margin-left: 75px; color: yellow"> Username incorrect </span>
    </div>
    <div style="margin-top: 1em;">
        <label for="password" class="login-label">Password: </label>
        <br>
        <input type="password" name="password" id="password" required class="login-input">
        <span id="passwordError" style="margin-left: 75px; color: yellow"> Password incorrect </span>
    </div>
    <div style="margin-top: 1.2em;">
        <input type="submit" value="Login" class="login-submit">
    </div>
</form>
<nav class="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
    </ul>
</nav>
</body>
</html>