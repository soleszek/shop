<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">

<meta charset="utf-8"/>

<head>
    <title>Logowanie zakończone sukcesem</title>

    <link rel="stylesheet" href="style/general.css" type="text/css" >
    <link rel="stylesheet" href="style/login-success.css" type="text/css">

</head>
<body>

<div id="container">



<%
    String userName = (String)request.getSession().getAttribute("user");
    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
        for (Cookie cookie : cookies){
            if(cookie.getName().equals(userName)){
                userName = cookie.getValue();
            }
        }
    }

    if(userName == null) {
        response.sendRedirect("index.jsp");
    }
%>

    <%
        String totalCashSpend = String.valueOf(request.getAttribute("totalCashSpend"));
    %>

    <div id="logo">

        <h3>You are now logged in <%=userName%></h3><br>
        <p>You spent in our shop <%=totalCashSpend%> $ so far.</p>

    </div>

    <div id="continue-shopping">

        <form action="ProductList" method="get">
            <input type="submit" name="button" value="Start shopping...">
        </form>

    </div>

    <div id="logout">
        <form action="LogoutServlet" method="get">
            <input type="hidden" name="user" value="<%=userName%>"/>
            <input type="submit" name="button" value="logout">
        </form>
    </div>

</div>
</body>
</html>
