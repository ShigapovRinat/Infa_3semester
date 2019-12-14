<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <title>Registration</title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>

<div class="container">
    <form class="form-signin" method="post" action="/registration">

        <h2 class="form-signin-heading">Регистрация</h2>
        <br>

        <label for="login" class="sr-only">Логин</label>
        <input name="login" type="text" id="login" class="form-control" placeholder="login" required=""
               autofocus="">
        <br>

        <label for="password" class="sr-only">Пароль</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="пароль"
               required="">
        <br>

        <button class="btn btn-lg btn-success btn-block" type="submit">Отправить</button>

        <br>

        <%--ЕСЛИ БЫЛА ПОПЫТКА зарегестрироваться, выводит успешна ли она или нет--%>
        <c:if test="${registrationStatus!=null}">
        <div class="alert alert-danger loginStatus" role="alert">
            <c:out value="${registrationStatus}"/>
        </div>
        </c:if>

    </form>

</div>


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</body>
</html>