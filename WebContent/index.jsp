<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body{
background-image:url(image/bg.png);
background-position:center;
background-size:800px 450px;
} 
div{
text-align:center;
}
.select{
margin:50px 0 ;
}
</style> 

<title>草药管理系统首页</title>
</head>
<body>
<img alt="LOGO" src="image/logo2.png" style="width:200px;">
<div class="select">
<p>查询草药信息</p>


<form action="herbQueryById" method="post">
<input type="text" name="herbid" size="30" placeholder="请输入草药id">
<input type="submit" value="确定">
</form>
<p><a href="showListServlet">
<button style="width:290px;">查询所有草药</button>
</a></p>
</div>
<div>
<p>增加草药信息</p>

<form action="addHerbdo" method="post">
<table align="center">
<tr>
<td>编号</td>
<td><input type="text" name="herbid2" size="30" placeholder="请输入草药id"></td>
</tr>
<tr>
<td>名称</td>
<td><input type="text" name="title" size="30" placeholder="请输入草药名称"></td>
</tr>
<tr>
<td>单价</td>
<td><input type="text" name="price" size="30" placeholder="请输入草药单价"></td>
</tr>
<tr>
<td>库存</td>
<td><input type="text" name="stock" size="30" placeholder="请输入草药库存"></td>
</tr>
<tr>
<td>产地</td>
<td><input type="text" name="province" size="30" placeholder="请输入草药产地"></td>
</tr>
<tr>
<td><input type="submit" value="确定" ></td>
<td align="right"><input type="reset" value="重置" ></td>
</tr>
</table>
</form>

</div>
</body>
</html>