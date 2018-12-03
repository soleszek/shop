
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.sylwesteroleszek.entity.NewUser" %>


<head>
    <title>Rejestracja</title>

    <link rel="stylesheet" href="style/general.css" type="text/css" >
    <link rel="stylesheet" href="style/rejestracja.css" type="text/css" >
</head>
<body>

<div id="container-create-user">
    <h1 class="panel-title">Create new user</h1>
    <form class="create-user" action="CreateUserServlet" method="post">
        <div class="field">
            <label>
                <input type="text" name="name" placeholder="User name" onfocus="this.placeholder=''" onblur="this.placeholder='Name'">
            </label>
        </div>

        <div class="field">
            <label>
                <input type="text" name="surname" placeholder="Surname" onfocus="this.placeholder=''" onblur="this.placeholder='Surname'">
            </label>
        </div>

        <div class="field">
            <label>
                <input type="text" name="username" placeholder="username" onfocus="this.placeholder=''" onblur="this.placeholder='Username'">
            </label>
        </div>

        <div class="field">
            <label>
                <input type="password" name="password" placeholder="password" onfocus="this.placeholder=''" onblur="this.placeholder='Password'">
            </label>
        </div>
        <div class="commands">
            <input type="submit" tabindex="5" value="Create">
        </div>
    </form>

    <div id="buttons">

        <form action="index.jsp" method="get">
            <input type="submit" name="button" value="Back to login">
        </form>

    </div>


</div>
</body>
</html>
