package demo;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import dbtool.herbDB;
import model.Herb;

@WebServlet(name="addHerbServlet",urlPatterns = {"/addHerbdo"})
public class addHerbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;  
    public addHerbServlet() {
        super();
    }

	public void init() {
		conn = herbDB.getConnection();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int herbid = Integer.parseInt(request.getParameter("herbid2"));	
		String title = request.getParameter("title");
		float price = Float.parseFloat(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String province = request.getParameter("province");
		/*
		INSERT INTO herb(id, title, price, stock, province) 
		VALUES (51615, '鸡骨草', 40.00, 213, '广东 广西');
		*/		
		
		try {
			String sql = "INSERT INTO herb VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, herbid);
			pstmt.setString(2, title);
			pstmt.setFloat(3, price);
			pstmt.setInt(4, stock);
			pstmt.setString(5, province);
			int n = pstmt.executeUpdate();
			System.out.println("成功插入"+n+"行数据！");

 			if(n==1) { 				
 				try {
					String sql2 = "SELECT*FROM herb WHERE id = ?";
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);					
					pstmt2.setInt(1, herbid);
					ResultSet rst2 = pstmt2.executeQuery();
					if(rst2.next()) {
						Herb herb = new Herb();
						herb.setId(rst2.getInt("id"));
						herb.setTitle(rst2.getString("title"));
						herb.setPrice(rst2.getFloat("price"));
						herb.setStock(rst2.getInt("stock"));
						herb.setProvince(rst2.getString("province"));
						request.getSession().setAttribute("herb", herb);
						response.sendRedirect("herbDetail.jsp");
					}else {
						response.sendRedirect("error.jsp");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} 				
			}else {
				System.out.println("数据插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
