<%@ page import="java.util.List" %>
<%@ page import="com.sylwesteroleszek.products.Product" %>
<%@ page import="com.sylwesteroleszek.cart.ProductInCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>

    <link rel="stylesheet" href="style/general.css" type="text/css" >
    <link rel="stylesheet" href="style/shopping-cart.css" type="text/css">

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
        List<ProductInCart> productsInCart = (List<ProductInCart>) request.getSession().getAttribute("productsInCart");
    %>

    <div id="logo">

        <h1>Your shopping cart</h1>

    </div>

    <table class="shopping-cart" cellpadding="2" cellspacing="2" border="1" align="center" width="100%">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>+</th>
            <th>Quantity</th>
            <th>-</th>
            <th>Remove product</th>
        </tr>

        <%

            for(ProductInCart productInCart : productsInCart) {
        %>
        <tr>
            <td><center><%=productInCart.getProductId()%></center></td>
            <td><%=productInCart.getName()%></td>
            <td><center><%=productInCart.getPrice()%></center></td>
            <td>
                <form class="plus-minus" action="AddPiece" method="get">
                    <input type="hidden" name="add" value="<%=productInCart.getProductId()%>"/>
                    <input type="submit" name="plus" value="+">
                </form></td>
            </td>
            <td><center><%=productInCart.getQuantity()%></center></td>
            <td>
                <form class="plus-minus" action="SubtractPiece" method="get">
                    <input type="hidden" name="subtract" value="<%=productInCart.getProductId()%>"/>
                    <input type="submit" name="minus" value="-">
                </form></td>
            </td>
            <td>
                <form class="plus-minus-remove" action="Remove" method="get">
                    <input type="hidden" name="remove" value="<%=productInCart.getProductId()%>"/>
                    <input type="submit" name="removeFromCart" value="Remove">
                </form></td>
        </tr>

        <%}%>

    </table>

    <%
        Double subtotal = 0.0;
        for(ProductInCart p : productsInCart){
            subtotal = subtotal + (p.getPrice() * p.getQuantity());
        }
    %>

    <table class="shopping-cart" cellpadding="2" cellspacing="2" border="1" align="center" width="50%">
        <th>Subtotal</th>
        <tr>
            <td><center><%=subtotal%></center></td>
        </tr>
    </table>

    <div id="buttons">

        <form action="Finalize" method="get">
            <input type="submit" name="button" value="Proceed to checkout">
        </form>

        <form action="ProductList" method="get">
            <input type="submit" name="button" value="Continue shopping...">
        </form>

        <form action="LogoutServlet" method="get">
            <input type="hidden" name="user" value="<%=userName%>"/>
            <input type="submit" name="button" value="Logout">
        </form>

    </div>

</div>

</body>
</html>
