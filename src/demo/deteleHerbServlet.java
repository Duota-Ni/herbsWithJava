package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbtool.herbDB;

@WebServlet("/deteleHerbServlet")
public class deteleHerbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null; 
    public deteleHerbServlet() {
        super();
    }
    
    public void init() {
		conn = herbDB.getConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int herbid = Integer.parseInt(request.getParameter("herbid3"));	
		try {
			String sql = "DELETE FROM herb WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1,herbid);
			int n = pstmt.executeUpdate();
			System.out.println("成功删除"+n+"行数据！");
			out.println("<!DOCTYPE html>");
	        out.println("<html><body>");
			if (n==1) {
				out.println("<p>成功删除"+n+"行数据！</p>");
				out.println("<p><a href=\"index.jsp\">返回首页</a></p>");
			}else {
				out.println("删除数据失败！");
			}
			out.println("</body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
