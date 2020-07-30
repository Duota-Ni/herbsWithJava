<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>草药出库单</title>
<style type="text/css">
body{
background-image:url(image/bg.png);
background-position:center;
background-size:800px 450px;
}
</style>
</head>
<body>
<a href="index.jsp">
<img alt="LOGO" src="image/logo2.png" style="width:200px;">
</a>
<%
//从会话对象中取出购物车对象cart
shoppingCart cart =(shoppingCart)session.getAttribute("cart");
//从购物车中取出每件商品并存储在ArrayList中，getItems()方法参见shoppingCart.java
ArrayList<HerbItem> items= new ArrayList<HerbItem>(cart.getItems());
//ArrayList<HerbItem> items = new ArrayList<HerbItem>(cart.getItems());
%>

<table align="center" style="background-color:#fff;" border=1 width="95%">
<caption style="margin:20px;font-weight:600;font-size=20px;">您的出库单信息</caption>
<tr>
   <td style='width:80px'>数量</td>
   <td style='width:80px'>编号</td>
   <td style='width:80px'>名称</td>
   <td style='width:80px'>单价</td>
   <td style='width:80px'>小计</td>
   <td style='width:80px'>是否删除</td>
</tr>
<%
//显示购物车中每件商品的信息
for(HerbItem herbItem : items){
	Herb herb = herbItem.getHerb() ;
%>
<tr>
   <td><%= herbItem.getQuantity() %></td>
   <td><%= herb.getId() %></td>
   <td><%= herb.getTitle() %></td>
   <td><%=herb.getPrice() %></td>
   <td><%= ((int)(herb.getPrice()*herbItem.getQuantity()*100+0.5))/100.0 %></td>
   <td><a href="deleteHerbItem?id=<%=herb.getId() %>">删除</a></td>
</tr>
<%} %>

<tr>
<td colspan='4' style='text-align:right'>
    总计：<%=cart.getTotal() %></td>
</tr>
</table>
<p align="right"><a href="showListServlet">返回草药列表</a></p>
<p align="right"><a href="index.jsp">返回首页</a></p>
</body>
</html>