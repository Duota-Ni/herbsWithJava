<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Herb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>草药详细信息</title>
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
Herb herb = (Herb)session.getAttribute("herb");
%>
<form action="deteleHerbServlet" method="post" name="myform2">
<!-- 隐藏表单传递参数id给addToHerbCart动作。在HerbControllerServlet.java -->
<input type='hidden' name='herbid3' value='<%=herb.getId() %>'/>
<table align="center" style="background-color:#fff;" border=1 width="95%">
  <caption style="margin:20px;font-weight:600;font-size=20px;">草药详细信息</caption>
  <tr>
      <td>编号</td>
      <td><%= herb.getId() %></td>
  </tr>
  <tr>
      <td>草药名称</td>
      <td><%= herb.getTitle() %></td>
  </tr>
  <tr>
      <td>草药单价</td>
      <td><%= herb.getPrice() %></td>
   <tr>
      <td>剩余库存</td>
      <td><%= herb.getStock() %></td>
  </tr>
  <tr>
      <td>草药产地</td>
      <td><%= herb.getProvince() %></td>
  </tr>
</table>
<div style="text-align:end;margin:12px 0;">
<input type="submit" value="删除该草药" style="margin:20px;">
</div>
</form>
<p align="right"><a href="index.jsp">返回首页</a></p>
</body>
</html>