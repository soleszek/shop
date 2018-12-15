<%@ page import="java.util.List" %>
<%@ page import="com.sylwesteroleszek.entity.Product" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shop</title>

    <link rel="stylesheet" href="style/general.css" type="text/css" >
    <link rel="stylesheet" href="style/product-list.css" type="text/css">
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

    <div id="logo">

        <% List<Product> productsInCart = new ArrayList<>(); %>

        <h1>Online grocery store</h1>

    </div>

<table class="carts-table" cellpadding="2" cellspacing="2" border="1" align="center" width="100%">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity in stock</th>
        <th>Action</th>
    </tr>

<%
    List<Product> productList = (List<Product>) request.getSession().getAttribute("productlist");

    for(Product product : productList) {
%>
    <tr>
        <td><center><%=product.getId()%></center></td>
        <td><%=product.getName()%></td>
        <td><%=product.getDescription()%></td>
        <td><center><%=product.getPrice()%></center></td>
        <td><center><%=product.getQuantity()%></center></td>
        <td>
            <form id="add-to-cart" action="AddToCart" method="get">
                <input type="hidden" name="productId" value="<%=product.getId()%>"/>
                <input type="submit" name="addToCart" value="Add to cart">
            </form></td>
    </tr>

    <%}%>

</table>

    <div id="buttons">

        <form action="ShoppingCart.jsp" method="get">
            <input type="submit" name="button" value="Go to your cart">
        </form>

        <form action="LogoutServlet" method="get">
            <input type="hidden" name="user" value="<%=userName%>"/>
            <input type="submit" name="button" value="Logout">
        </form>

    </div>

</div>

</body>
</html>
