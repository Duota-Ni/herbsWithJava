package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.glass.ui.Application;

import dbtool.herbDB;
import model.Herb;

@WebServlet("/herbQueryById")
public class herbQueryById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;   
    public herbQueryById() {
        super();
    }
    public void init() {
    	conn=herbDB.getConnection();
    }


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
        
		int herbid = Integer.parseInt(request.getParameter("herbid"));
	
		try {
		String sql="SELECT*FROM herb WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,herbid);
		ResultSet rst = pstmt.executeQuery();
		if(rst.next()) {
			Herb herb = new Herb();
			herb.setId(rst.getInt("id"));
			herb.setTitle(rst.getString("title"));
			herb.setPrice(rst.getFloat("price"));
			herb.setStock(rst.getInt("stock"));
			herb.setProvince(rst.getString("province"));
			request.getSession().setAttribute("herb", herb);
			response.sendRedirect("herbDetail.jsp");
		}else {
			response.sendRedirect("error.jsp");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
