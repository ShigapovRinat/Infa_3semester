<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 24.09.2019
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form method="post">
    <h2>
        Login:
        <input placeholder="your login" type="text" name="login"/><br>
        Password:
        <input placeholder="your password" type="password" name="password"/><br>
        Remember me
        <input type="checkbox" name="remember" value="true"/><br>
        <input type="submit" value="Log in" checked>
    </h2>
</form>
</body>
</html>
