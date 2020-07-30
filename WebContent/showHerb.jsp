<%@page import="model.Herb"%>
<%@page import="java.util.*"%>
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
</style>
<title>所有草药列表</title>
</head>
<body>
<a href="index.jsp">
<img alt="LOGO" src="image/logo2.png" style="width:200px;">
</a>
	<table border=1 width="95%" align="center" style="background-color:#fff;">
		<caption>草药列表</caption>
		<tr>
		    <th>序号</th>
			<th>编号</th>
			<th>草药名称</th>
			<th>草药单价</th>
			<th>剩余库存</th>
			<th>草药产地</th>
			<th>查看信息</th>
		</tr>
		<%
		List<Herb> herbs=(List<Herb>)application.getAttribute("herbs");
	//	List<Herb> herbs=(List<Herb>)session.getAttribute("herbs");
    //List<Herb> herbs = (List<Herb>)request.getAttribute("herbs");
    int current = (Integer)request.getAttribute("current");
    int pageCount = (Integer)request.getAttribute("pageCount");
    int max =(Integer)request.getAttribute("max");
    if(herbs != null && herbs.size()>0){
    	for(int i =0;i<herbs.size();i++){
    		Herb herb = herbs.get(i);
    		int id =herb.getId();
    		String title = herb.getTitle();
    		float price = herb.getPrice();
    		int stock = herb.getStock();
    		String province = herb.getProvince();
    		out.println("<tr><td>"+(pageCount*(current-1)+i+1)+"</td><td>"
    		+id+"</td><td>"+title+"</td><td>"+price+"</td><td>"+stock+"</td><td>"
    				+province+"</td><td>"+"<a href='showHerbDetail?id="+id+"'>详细信息</a>"+"</td></tr>");
    	}
    }else{
    	out.println("<tr><td colspan='5'>暂无数据</td></tr>");
    }
    int prePage = 1;
    int nextPage = 1;
    prePage = current<=1?1:current-1;
    nextPage = current>=max?max:current+1;
    out.println("<tr><td colspan='5' align='right'>"+
    "<a href='showListServlet?currentPage=1'>返回第一页</a>&nbsp;&nbsp;&nbsp;"+
    "<a href='showListServlet?currentPage="+prePage+"'>上一页</a>&nbsp;&nbsp;&nbsp;"+
    "当前页："+current+"/"+max+
    "&nbsp;&nbsp;&nbsp; <a href='showListServlet?currentPage="+nextPage+"'>下一页</a> &nbsp;&nbsp;&nbsp;"+
    "<a href='showListServlet?currentPage="+max+"'>尾页</a></td></tr>");
    %>
	</table>
	
	<p align="right"><a href="showHerbCart.jsp">查看出库单</a></p>
	<p align="right"><a href="index.jsp">返回首页</a></p>
</body>
</html>